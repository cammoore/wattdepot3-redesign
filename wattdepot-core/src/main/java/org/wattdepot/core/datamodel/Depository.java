/**
 * Depository.java created on Oct 18, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

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
  protected String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set.
   */
  protected void setName(String name) {
    this.name = name;
  }

  /**
   * @return the measurementType
   */
  protected String getMeasurementType() {
    return measurementType;
  }

  /**
   * @param measurementType
   *          the measurementType to set
   */
  protected void setMeasurementType(String measurementType) {
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

}
