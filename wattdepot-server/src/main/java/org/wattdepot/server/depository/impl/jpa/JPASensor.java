/**
 * JPASensor.java created on Oct 4, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.wattdepot.core.datamodel.Location;
import org.wattdepot.core.datamodel.Sensor;
import org.wattdepot.core.datamodel.SensorModel;
import org.wattdepot.core.datamodel.Property;

/**
 * JPASensor persistent version of Sensor using JPA.
 * 
 * @author Cam Moore
 * 
 */
@Entity
public class JPASensor extends Sensor {
  /** The database id. */
  private Long dbId;

  /**
   * Hide the default constructor.
   */
  protected JPASensor() {
    super();
  }
  /**
   * @return the uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * @param uri
   *          the uri to set
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * @return the location
   */
  @ManyToOne
  public JPALocation getLocation() {
    return new JPALocation(location);
  }

  /**
   * @param location
   *          the location to set
   */
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * @return the model
   */
  @ManyToOne
  public JPASensorModel getModel() {
    return new JPASensorModel(model);
  }

  /**
   * @param model
   *          the model to set
   */
  public void setModel(SensorModel model) {
    this.model = model;
  }

  /**
   * @return the properties
   */
  public ArrayList<Property> getProperties() {
    return properties;
  }
  /**
   * @param properties the properties to set
   */
  public void setProperties(ArrayList<Property> properties) {
    this.properties = properties;
  }


  /**
   * @param id2
   *          The unique String id.
   * @param uri
   *          The URI to the sensor.
   * @param l
   *          The Location of the sensor.
   * @param sm
   *          The SensorModel of the sensor.
   */
  public JPASensor(String id2, String uri, Location l, SensorModel sm) {
    super(id2, uri, l, sm);
  }

  /**
   * @param s
   *          The Sensor to clone.
   */
  protected JPASensor(Sensor s) {
    super(s.getId(), s.getUri(), s.getLocation(), s.getModel());
    location = new JPALocation(s.getLocation());
    model = new JPASensorModel(s.getModel());
    for (Property p : s.getProperties()) {
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
}
