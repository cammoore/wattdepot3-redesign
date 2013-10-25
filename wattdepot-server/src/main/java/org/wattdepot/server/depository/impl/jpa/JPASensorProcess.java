/**
 * JPASensorProcess.java created on Oct 12, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.wattdepot.core.datamodel.Property;
import org.wattdepot.core.datamodel.Sensor;
import org.wattdepot.core.datamodel.SensorProcess;

/**
 * JPASensorProcess - Persistent version of the SensorProcess.
 * 
 * @author Cam Moore
 * 
 */
@Entity
public class JPASensorProcess extends SensorProcess {
  /** The database id. */
  private Long dbId;

  /**
   * Hide the default constructor.
   */
  protected JPASensorProcess() {

  }

  /**
   * @param id2
   *          The unique String id.
   * @param sensor
   *          The Sensor to poll.
   * @param pollingInterval
   *          The polling interval in seconds.
   */
  public JPASensorProcess(String id2, Sensor sensor, Long pollingInterval, String depositoryId) {
    super(id2, sensor, pollingInterval, depositoryId);
  }

  /**
   * @param sp
   *          The SensorProcess to clone.
   */
  public JPASensorProcess(SensorProcess sp) {
    super(sp.getId(), sp.getSensor(), sp.getPollingInterval(), sp.getDepositoryId());
    this.sensor = new JPASensor(sp.getSensor());
    this.pollingInterval = sp.getPollingInterval();
    for (Property p : sp.getProperties()) {
      properties.add(new JPAProperty(p));
    }
  }

  /**
   * @return the id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getDBId() {
    return dbId;
  }

  /**
   * @param id
   *          the id to set.
   */
  public void setDBId(Long id) {
    this.dbId = id;
  }

  /**
   * @return the depositoryId
   */
  public String getDepositoryId() {
    return depositoryId;
  }

  /**
   * @return the pollingInterval.
   */
  public Long getPollingInterval() {
    return pollingInterval;
  }

  /**
   * @return the properties.
   */
  public ArrayList<Property> getProperties() {
    return properties;
  }

  /**
   * @param depositoryId
   *          the depositoryId to set
   */
  public void setDepositoryId(String depositoryId) {
    this.depositoryId = depositoryId;
  }

  /**
   * @param pollingInterval
   *          the pollingInterval to set
   */
  public void setPollingInterval(Long pollingInterval) {
    this.pollingInterval = pollingInterval;
  }

  /**
   * @param properties
   *          the properties to set
   */
  public void setProperties(ArrayList<Property> properties) {
    this.properties = properties;
  }

}
