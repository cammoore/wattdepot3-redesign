/**
 * Depository.java created on Oct 18, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.wattdepot.core.exception.MeasurementGapException;
import org.wattdepot.core.exception.MeasurementTypeException;
import org.wattdepot.core.exception.NoMeasurementException;

/**
 * Depository - Stores measurements from Sensors of the matching measurement
 * type.
 * 
 * @author Cam Moore
 * 
 */
public class Depository extends BaseModel {
  /** Name of the Depository. */
  private String name;
  /** Measurement type stored in the Depository. */
  private String measurementType;

  /**
   * Hide the default constructor.
   */
  protected Depository() {

  }

  /**
   * Create a new Depository.
   * 
   * @param uniqueId
   *          The unique id.
   * @param name
   *          The name of the Depository.
   * @param measurementType
   *          The type of the measurements this Depository accepts.
   */
  public Depository(String uniqueId, String name, String measurementType) {
    super(uniqueId);
    this.name = name;
    this.measurementType = measurementType;
  }

  /**
   * @return the name.
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the measurementType
   */
  public String getMeasurementType() {
    return measurementType;
  }

  /**
   * @param measurementType
   *          the measurementType to set
   */
  public void setMeasurementType(String measurementType) {
    this.measurementType = measurementType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((measurementType == null) ? 0 : measurementType.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Depository other = (Depository) obj;
    if (measurementType == null) {
      if (other.measurementType != null) {
        return false;
      }
    }
    else if (!measurementType.equals(other.measurementType)) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Depository [id=" + id + ", measurementType=" + measurementType + "]";
  }

  /**
   * @param sensor
   *          The sensor making the measurements.
   * @param start
   *          The start of the period.
   * @param end
   *          The end of the period.
   * @return A list of the Measurements for the given Sensor.
   */
  public List<Measurement> getMeasurements(Sensor sensor, XMLGregorianCalendar start,
      XMLGregorianCalendar end) {
    throw new RuntimeException("Not implemented.");
  }

  /**
   * @param sensor
   *          The Sensor making the measurements.
   * @param timestamp
   *          The time of the value.
   * @return The Value 'measured' at the given time, most likely an interpolated
   *         value.
   * @throws NoMeasurementException
   *           If there aren't any measurements around the time.
   */
  public Double getValue(Sensor sensor, Date timestamp) throws NoMeasurementException {
    throw new RuntimeException("Not implemented.");
  }

  /**
   * @param sensor
   *          The Sensor making the measurements.
   * @param start
   *          The start of the period.
   * @param end
   *          The end of the period.
   * @return The value measured the difference between the end value and the
   *         start value.
   * @throws NoMeasurementException
   *           if there are no measurements around the start or end time.
   */
  public Double getValue(Sensor sensor, Date start, Date end) throws NoMeasurementException {
    throw new RuntimeException("Not implemented.");
  }

  /**
   * @param sensor
   *          The Sensor making the measurements.
   * @param start
   *          The start of the interval.
   * @param end
   *          The end of the interval
   * @param gapSeconds
   *          The maximum number of seconds that measurements need to be within
   *          the start and end.
   * @return The value measured the difference between the end value and the
   *         start value.
   * @throws NoMeasurementException
   *           if there are no measurements around the start or end time.
   * @throws MeasurementGapException
   *           if the measurements around start or end are too far apart.
   */
  public Double getValue(Sensor sensor, Date start, Date end, Long gapSeconds)
      throws NoMeasurementException, MeasurementGapException {
    throw new RuntimeException("Not implemented.");
  }

  /**
   * @param sensor
   *          The Sensor making the measurements.
   * @param timestamp
   *          The time of the value.
   * @param gapSeconds
   *          The maximum number of seconds that measurements need to be within
   *          the start and end.
   * @return The Value 'measured' at the given time, most likely an interpolated
   *         value.
   * @throws NoMeasurementException
   *           If there aren't any measurements around the time.
   * @throws MeasurementGapException
   *           if the measurements around timestamp are too far apart.
   */
  public Double getValue(Sensor sensor, Date timestamp, Long gapSeconds)
      throws NoMeasurementException, MeasurementGapException {
    throw new RuntimeException("Not implemented.");
  }

  /**
   * @param meas
   *          The measurement to store.
   * @throws MeasurementTypeException
   *           if the type of the measurement doesn't match the Depository
   *           measurement type.
   */
  public void putMeasurement(Measurement meas) throws MeasurementTypeException {
    throw new RuntimeException("Not implemented.");
  }

}
