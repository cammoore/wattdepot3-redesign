/**
 * JPAProperty.java created on Oct 4, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.wattdepot.core.datamodel.Property;

/**
 * JPAProperty persistent version of Property using JPA.
 * 
 * @author Cam Moore
 * 
 */
@Entity
public class JPAProperty extends Property {
  /** The database id. */
  private Long dbId;

  /** default constructor. */
  protected JPAProperty() {
    super();
  }

  /**
   * @param key
   *          The key.
   * @param value
   *          The value.
   */
  protected JPAProperty(String key, String value) {
    super(key, value);
  }

  /**
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param key
   *          the key to set
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @param p
   *          The Property to clone.
   */
  protected JPAProperty(Property p) {
    super(p.getKey(), p.getValue());
  }

  /**
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getDBId() {
    return dbId;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setDBId(Long id) {
    this.dbId = id;
  }

}
