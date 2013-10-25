/**
 * JPALocation.java created on Oct 4, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.wattdepot.core.datamodel.Location;

/**
 * JPALocation persistent version of Location using JPA.
 * 
 * @author Cam Moore
 * 
 */
@Entity
public class JPALocation extends Location {
  /** The database id. */
  private Long dbId;

  /** Default constructor. */
  protected JPALocation() {
    super();
  }

  /**
   * @param id2
   *          The unique string id.
   * @param latitude
   *          The latitude.
   * @param longitude
   *          The longitude.
   * @param altitude
   *          The altitude in meters MSL.
   * @param description
   *          The description.
   */
  public JPALocation(String id2, Double latitude, Double longitude, Double altitude,
      String description) {
    super(id2, latitude, longitude, altitude, description);
  }

  /**
   * @param location
   *          The Location to clone.
   */
  protected JPALocation(Location location) {
    super(location.getId(), location.getLatitude(), location.getLongitude(),
        location.getAltitude(), location.getDescription());
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
   * @return the latitude
   */
  public Double getLatitude() {
    return latitude;
  }

  /**
   * @param latitude
   *          the latitude to set
   */
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * @return the longitude
   */
  public Double getLongitude() {
    return longitude;
  }

  /**
   * @param longitude
   *          the longitude to set
   */
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * @return the altitude
   */
  public Double getAltitude() {
    return altitude;
  }

  /**
   * @param altitude
   *          the altitude to set
   */
  public void setAltitude(Double altitude) {
    this.altitude = altitude;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

}
