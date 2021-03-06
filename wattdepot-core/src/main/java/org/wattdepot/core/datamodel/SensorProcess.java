/**
// * SensorProcess.java created on Oct 12, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import java.util.ArrayList;

/**
 * SensorProcess - Represents a process that queries a Sensor and produces
 * measurements.
 * 
 * @author Cam Moore
 * 
 */
public class SensorProcess extends BaseModel {
  /** The sensor making the measurements. */
  protected Sensor sensor;
  /** The number of seconds between polls. */
  protected Long pollingInterval;
  /** The id of the depository where the measurements are stored. */
  protected String depositoryId;
  /** Additional properties for the SensorProcess. */
  protected ArrayList<Property> properties;

  /**
   * Hide the default constructor.
   */
  protected SensorProcess() {

  }

  /**
   * @param id
   *          The unique id.
   * @param sensor
   *          The sensor that measures the environment.
   * @param poll
   *          The number of seconds between polls.
   * @param depositoryId
   *          The depository_id where measurements are stored.
   */
  public SensorProcess(String id, Sensor sensor, Long poll, String depositoryId) {
    super(id);
    this.sensor = sensor;
    this.pollingInterval = poll;
    this.depositoryId = depositoryId;
    this.properties = new ArrayList<Property>();
  }

  /**
   * @param e
   *          The Property to add.
   * @return true if added.
   * @see java.util.List#add(java.lang.Object)
   */
  public boolean addProperty(Property e) {
    return properties.add(e);
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
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SensorProcess other = (SensorProcess) obj;
    if (getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    }
    else if (!getId().equals(other.getId())) {
      return false;
    }
    if (pollingInterval == null) {
      if (other.pollingInterval != null) {
        return false;
      }
    }
    else if (!pollingInterval.equals(other.pollingInterval)) {
      return false;
    }
    if (properties == null) {
      if (other.properties != null) {
        return false;
      }
    }
    else if (!properties.equals(other.properties)) {
      return false;
    }
    if (sensor == null) {
      if (other.sensor != null) {
        return false;
      }
    }
    else if (!sensor.equals(other.sensor)) {
      return false;
    }
    return true;
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
   * @param key
   *          The key.
   * @return The value of associated with the key.
   */
  public Property getProperty(String key) {
    for (Property p : properties) {
      if (p.getKey().equals(key)) {
        return p;
      }
    }
    return null;
  }

  /**
   * @return the sensor
   */
  public Sensor getSensor() {
    return sensor;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((pollingInterval == null) ? 0 : pollingInterval.hashCode());
    result = prime * result + ((properties == null) ? 0 : properties.hashCode());
    result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
    return result;
  }

  /**
   * @param o
   *          The Property to remove.
   * @return true if removed.
   * @see java.util.List#remove(java.lang.Object)
   */
  public boolean removeProperty(Object o) {
    return properties.remove(o);
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

  /**
   * @param index
   *          The index of the property to set.
   * @param element
   *          The Property.
   * @return The new Property.
   * @see java.util.List#set(int, java.lang.Object)
   */
  public Property setProperty(int index, Property element) {
    return properties.set(index, element);
  }

  /**
   * @param sensor
   *          the sensor to set
   */
  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

  /**
   * @return The JSON version of this SensorProcess with IDs.
   */
  public String toShortJSON() {
    StringBuffer buf = new StringBuffer();
    buf.append("{\"id\": \"");
    buf.append(this.getId());
    buf.append("\", \"sensorId\": \"");
    buf.append(sensor.getId());
    buf.append("\", \"pollingInterval\": ");
    buf.append(pollingInterval);
    buf.append(", [");
    for (Property p : this.properties) {
      buf.append("{\"key\": \"" + p.getKey() + "\" \"value\": \"" + p.getValue() + "\"},");
    }
    if (properties.size() > 0) {
      // remove trailing ,
      buf.deleteCharAt(buf.length() - 1);
    }
    buf.append("]}");
    return buf.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SensorProcess [id=" + getId() + ", sensor=" + sensor + ", pollingInterval="
        + pollingInterval + ", properties=" + properties + "]";
  }

}
