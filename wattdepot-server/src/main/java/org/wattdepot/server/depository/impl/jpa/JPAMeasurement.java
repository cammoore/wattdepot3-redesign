/**
 * JPAMeasurement.java created on Oct 4, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.wattdepot.core.datamodel.Measurement;

/**
 * JPAMeasurement persistent version of Measurement using JPA.
 *
 * @author Cam Moore
 *
 */
@Entity
@Table(name = "Measurement")
public class JPAMeasurement extends Measurement {
  /** The database id. */
  private Long id;
  private String depository;

  /**
   * Hide the default constructor.
   */
  protected JPAMeasurement() {
    
  }
  /**
   * @param meas the Measurement to clone.
   * @param name The name of the depository storing the measurement.
   */
  public JPAMeasurement(Measurement meas, String name) {
    super(new JPASensor(meas.getSensor()), meas.getTimestamp(), meas.getValue(), meas.getMeasurementType());
    this.depository = name;
  }
  /**
   * @return the sensor
   */
  @ManyToOne
  public JPASensor getSensor() {
    return new JPASensor(sensor);
  }

  /**
   * @return the timestamp
   */
  public Timestamp getTimestamp() {
    return timestamp;
  }

  /**
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * @return the measurementType
   */
  public String getMeasurementType() {
    return measurementType;
  }

  /**
   * @param sensor the sensor to set
   */
  public void setSensor(JPASensor sensor) {
    this.sensor = sensor;
  }

  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * @param value the value to set
   */
  public void setValue(Double value) {
    this.value = value;
  }

  /**
   * @param measurementType the measurementType to set
   */
  public void setMeasurementType(String measurementType) {
    this.measurementType = measurementType;
  }


  /**
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the depository
   */
  public String getDepository() {
    return depository;
  }

  /**
   * @param depository the depository to set
   */
  public void setDepository(String depository) {
    this.depository = depository;
  }
}
