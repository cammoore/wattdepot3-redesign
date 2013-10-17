/**
 * SensorGroup.java created on Oct 3, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * SensorGroup represents a group of Sensors. Used for aggregating sensor
 * measurements.
 * 
 * @author Cam Moore
 * 
 */
public class SensorGroup extends BaseModel {
  /** The List of sensors the compose this group. */
  private List<Sensor> sensors;

  /**
   * Hide the default constructor.
   */
  protected SensorGroup() {

  }

  /**
   * Create a new SensorGroup with the given unique id.
   * 
   * @param uniqueId
   *          The unique id.
   */
  public SensorGroup(String uniqueId) {
    super(uniqueId);
    this.sensors = new ArrayList<Sensor>();
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
    result = prime * result + ((sensors == null) ? 0 : sensors.hashCode());
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SensorGroup other = (SensorGroup) obj;
    if (sensors == null) {
      if (other.sensors != null) {
        return false;
      }
    }
    else if (!sensors.equals(other.sensors)) {
      return false;
    }
    if (getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    }
    else if (!getId().equals(other.getId())) {
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
    return "SensorGroup [id=" + getId() + ", sensors=" + sensors + "]";
  }

  /**
   * @param e
   *          The sensor to add.
   * @return true if successful.
   * @see java.util.List#add(java.lang.Object)
   */
  public boolean add(Sensor e) {
    return sensors.add(e);
  }

  /**
   * @param o
   *          The Sensor to test.
   * @return true if the Sensor is in this group.
   * @see java.util.List#contains(java.lang.Object)
   */
  public boolean contains(Object o) {
    return sensors.contains(o);
  }

  /**
   * @param o
   *          The Sensor to remove.
   * @return true if successful.
   * @see java.util.List#remove(java.lang.Object)
   */
  public boolean remove(Object o) {
    return sensors.remove(o);
  }

  /**
   * @return The JSON version of this Sensor with IDs.
   */
  public String toShortJSON() {
    StringBuffer buf = new StringBuffer();
    buf.append("{id: \"");
    buf.append(this.getId());
    buf.append("\", \"sensorIds\": [");
    for (Sensor s : sensors) {
      buf.append(s.getId());
      buf.append(",");
    }
    if (sensors.size() > 0) {
      // remove trailing ,
      buf.deleteCharAt(buf.length() - 1);
    }
    buf.append("]}");
    return buf.toString();
  }

}
