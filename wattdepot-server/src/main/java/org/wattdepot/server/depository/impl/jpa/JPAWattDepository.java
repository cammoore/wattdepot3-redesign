/**
 * JpaWattDepository.java created on Oct 4, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.datatype.XMLGregorianCalendar;

import org.wattdepot.core.datamodel.Depository;
import org.wattdepot.core.datamodel.Measurement;
import org.wattdepot.core.datamodel.Sensor;
import org.wattdepot.core.util.tstamp.TstampComparator;
import org.wattdepot.core.exception.MeasurementGapException;
import org.wattdepot.core.exception.MeasurementTypeException;
import org.wattdepot.core.exception.NoMeasurementException;

/**
 * JpaWattDepository JPA implementation of WattDepository uses hibernate.
 * 
 * @author Cam Moore
 * 
 */
public class JPAWattDepository extends Depository {

  /**
   * @param id
   *          The id.
   * @param name
   *          The name of the Depository.
   * @param measurementType
   *          The type of measurement this Depository handles.
   */
  protected JPAWattDepository(String id, String name, String measurementType) {
    super(id, name, measurementType);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot.server.depository.WattDepository#getMeasurements(org.wattdepot
   * .server.datamodel.Sensor, java.util.Date, java.util.Date)
   */
  @Override
  public List<Measurement> getMeasurements(Sensor sensor, XMLGregorianCalendar start,
      XMLGregorianCalendar end) {
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    List<JPAMeasurement> result = entityManager
        .createQuery(
            "FROM JPAMeasurement WHERE timestamp >= :start AND timestamp <= :end "
                + "AND measurementType = :measType" + " AND depository = :name",
            JPAMeasurement.class).setParameter("start", start).setParameter("end", end)
        .setParameter("measType", getMeasurementType()).setParameter("name", this.getName())
        .getResultList();
    entityManager.getTransaction().commit();
    ArrayList<Measurement> ret = new ArrayList<Measurement>();
    for (JPAMeasurement m : result) {
      ret.add(m);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot.server.depository.WattDepository#getValue(org.wattdepot.server
   * .datamodel.Sensor, java.util.Date)
   */
  @Override
  public Double getValue(Sensor sensor, Date timestamp) throws NoMeasurementException {
    Double ret = 0.0;
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    List<JPAMeasurement> result = entityManager
        .createQuery(
            "FROM JPAMeasurement WHERE timestamp = :time AND measurementType = :measType"
                + " AND depository = :name", JPAMeasurement.class).setParameter("time", timestamp)
        .setParameter("measType", getMeasurementType()).setParameter("name", getName())
        .getResultList();
    entityManager.getTransaction().commit();
    if (result.size() > 0) {
      for (JPAMeasurement p : result) {
        if (p.getSensor().equals(sensor)) {
          ret = p.getValue();
        }
      }
    }
    else {
      // need to get the stradle.
      entityManager.getTransaction().begin();
      List<JPAMeasurement> before = entityManager
          .createQuery(
              "FROM JPAMeasurement WHERE timestamp <= :time AND measurementType = :measType"
                  + " AND depository = :name", JPAMeasurement.class)
          .setParameter("time", timestamp).setParameter("measType", getMeasurementType())
          .setParameter("name", getName()).getResultList();
      List<JPAMeasurement> after = entityManager
          .createQuery(
              "FROM JPAMeasurement WHERE timestamp >= :time AND measurementType = :measType"
                  + " AND depository = :name", JPAMeasurement.class)
          .setParameter("time", timestamp).setParameter("measType", getMeasurementType())
          .setParameter("name", getName()).getResultList();
      entityManager.getTransaction().commit();
      JPAMeasurement justBefore = null;
      for (JPAMeasurement p : before) {
        if (p.getSensor().equals(sensor)) {
          if (justBefore == null) {
            justBefore = p;
          }
          else if (TstampComparator.compareTo(p.getTimestamp(), justBefore.getTimestamp()) > 0) {
            justBefore = p;
          }
        }
      }
      JPAMeasurement justAfter = null;
      for (JPAMeasurement p : after) {
        if (p.getSensor().equals(sensor)) {
          if (justAfter == null) {
            justAfter = p;
          }
          else if (TstampComparator.compareTo(p.getTimestamp(), justAfter.getTimestamp()) < 0) {
            justAfter = p;
          }
        }
      }
      if (justBefore != null && justAfter != null) {
        Double val1 = justBefore.getValue();
        Double val2 = justAfter.getValue();
        Double deltaV = val2 - val1;
        Long t1 = justBefore.getTimestamp().toGregorianCalendar().getTimeInMillis();
        Long t2 = justAfter.getTimestamp().toGregorianCalendar().getTimeInMillis();
        Long deltaT = t2 - t1;
        Long t3 = timestamp.getTime();
        Long toTimestamp = t3 - t1;
        Double slope = deltaV / deltaT;
        ret = val1 + (slope * toTimestamp);
      }
      else if (justBefore == null && justAfter == null) {
        throw new NoMeasurementException("Cannot find measurements before or after " + timestamp);
      }
      else if (justBefore == null) {
        throw new NoMeasurementException("Cannot find measurement before " + timestamp);
      }
      else if (justAfter == null) {
        throw new NoMeasurementException("Cannot find measurement after " + timestamp);
      }
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot.server.depository.WattDepository#getValue(org.wattdepot.server
   * .datamodel.Sensor, java.util.Date, java.util.Date)
   */
  @Override
  public Double getValue(Sensor sensor, Date start, Date end) throws NoMeasurementException {
    Double endVal = getValue(sensor, end);
    Double startVal = getValue(sensor, start);
    if (endVal != null && startVal != null) {
      return endVal - startVal;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot.server.depository.WattDepository#getValue(org.wattdepot.server
   * .datamodel.Sensor, java.util.Date, java.util.Date, java.lang.Long)
   */
  @Override
  public Double getValue(Sensor sensor, Date start, Date end, Long gapSeconds)
      throws NoMeasurementException, MeasurementGapException {
    Double endVal = getValue(sensor, end, gapSeconds);
    Double startVal = getValue(sensor, start, gapSeconds);
    if (endVal != null && startVal != null) {
      return endVal - startVal;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot.server.depository.WattDepository#getValue(org.wattdepot.server
   * .datamodel.Sensor, java.util.Date, java.lang.Long)
   */
  @Override
  public Double getValue(Sensor sensor, Date timestamp, Long gapSeconds)
      throws NoMeasurementException, MeasurementGapException {
    Double ret = 0.0;
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    List<JPAMeasurement> result = entityManager
        .createQuery(
            "FROM JPAMeasurement WHERE timestamp = :time AND measurementType = :measType"
                + " AND depository = :name", JPAMeasurement.class).setParameter("time", timestamp)
        .setParameter("measType", getMeasurementType()).setParameter("name", getName())
        .getResultList();
    entityManager.getTransaction().commit();
    if (result.size() > 0) {
      for (JPAMeasurement p : result) {
        if (p.getSensor().equals(sensor)) {
          ret = p.getValue();
        }
      }
    }
    else {
      // need to get the stradle.
      entityManager.getTransaction().begin();
      List<JPAMeasurement> before = entityManager
          .createQuery(
              "FROM JPAMeasurement WHERE timestamp <= :time AND measurementType = :measType"
                  + " AND depository = :name", JPAMeasurement.class)
          .setParameter("time", timestamp).setParameter("measType", getMeasurementType())
          .setParameter("name", getName()).getResultList();
      List<JPAMeasurement> after = entityManager
          .createQuery(
              "FROM JPAMeasurement WHERE timestamp >= :time AND measurementType = :measType"
                  + " AND depository = :name", JPAMeasurement.class)
          .setParameter("time", timestamp).setParameter("measType", getMeasurementType())
          .setParameter("name", getName()).getResultList();
      entityManager.getTransaction().commit();
      JPAMeasurement justBefore = null;
      for (JPAMeasurement p : before) {
        if (p.getSensor().equals(sensor)) {
          if (justBefore == null) {
            justBefore = p;
          }
          else if (TstampComparator.compareTo(p.getTimestamp(), justBefore.getTimestamp()) > 0) {
            justBefore = p;
          }
        }
      }
      JPAMeasurement justAfter = null;
      for (JPAMeasurement p : after) {
        if (p.getSensor().equals(sensor)) {
          if (justAfter == null) {
            justAfter = p;
          }
          else if (TstampComparator.compareTo(p.getTimestamp(), justAfter.getTimestamp()) < 0) {
            justAfter = p;
          }
        }
      }
      if (justBefore != null && justAfter != null) {
        Double val1 = justBefore.getValue();
        Double val2 = justAfter.getValue();
        Double deltaV = val2 - val1;
        Long t1 = justBefore.getTimestamp().toGregorianCalendar().getTimeInMillis();
        Long t2 = justAfter.getTimestamp().toGregorianCalendar().getTimeInMillis();
        Long deltaT = t2 - t1;
        if ((deltaT / 1000) > gapSeconds) {
          throw new MeasurementGapException("Gap of " + (deltaT / 1000) + "s is longer than "
              + gapSeconds);
        }
        Long t3 = timestamp.getTime();
        Long toTimestamp = t3 - t1;
        Double slope = deltaV / deltaT;
        ret = val1 + (slope * toTimestamp);
      }
      else if (justBefore == null && justAfter == null) {
        throw new NoMeasurementException("Cannot find measurements before or after " + timestamp);
      }
      else if (justBefore == null) {
        throw new NoMeasurementException("Cannot find measurement before " + timestamp);
      }
      else if (justAfter == null) {
        throw new NoMeasurementException("Cannot find measurement after " + timestamp);
      }
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot.server.depository.WattDepository#putMeasurement(org.wattdepot
   * .server.datamodel.Measurement)
   */
  @Override
  public void putMeasurement(Measurement meas) throws MeasurementTypeException {
    if (!meas.getMeasurementType().equals(getMeasurementType())) {
      throw new MeasurementTypeException("WattDepository " + getName() + " handles "
          + getMeasurementType() + " measurements it got a " + meas.getMeasurementType());
    }
    JPAMeasurement m = new JPAMeasurement(meas, getName());
    if (getMeasurements(meas.getSensor(), meas.getTimestamp(), meas.getTimestamp()).size() == 0) {
      EntityManager entityManager = JPAManager.getInstance().getEntityManager();
      entityManager.getTransaction().begin();
      entityManager.persist(m);
      entityManager.persist(m.getSensor());
      entityManager.persist(m.getSensor().getLocation());
      entityManager.persist(m.getSensor().getModel());
      entityManager.flush();
      entityManager.getTransaction().commit();
    }
  }
}
