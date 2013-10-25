/**
 * JPAUserInfo.java created on Oct 23, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.wattdepot.core.datamodel.Property;
import org.wattdepot.core.datamodel.UserInfo;

/**
 * JPAUserInfo
 * 
 * @author Cam Moore
 * 
 */
@Entity
public class JPAUserInfo extends UserInfo {
  /** The database id. */
  private Long dbId;

  /** Default constructor. */
  protected JPAUserInfo() {
    super();
  }

  /**
   * Creates a new JPAUserInfo with the given information.
   * 
   * @param id
   *          The unique id.
   * @param firstName
   *          The user's name.
   * @param lastName
   *          The user's last name.
   * @param email
   *          The user's email address.
   * @param password
   *          The user's password.
   * @param admin
   *          True if they are an admin.
   */
  public JPAUserInfo(String id, String firstName, String lastName, String email, String password,
      Boolean admin) {
    super(id, firstName, lastName, email, password, admin);
  }

  /**
   * Creates a new JPAUserInfo from the UserInfo.
   * 
   * @param info
   *          the UserInfo to clone.
   */
  protected JPAUserInfo(UserInfo info) {
    super(info.getId(), info.getFirstName(), info.getLastName(), info.getEmail(), info
        .getPassword(), info.getAdmin());
    for (Property p : info.getProperties()) {
      getProperties().add(new JPAProperty(p));
    }

  }

  /**
   * @return the admin
   */
  public Boolean getAdmin() {
    return admin;
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
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @return the unique id.
   */
  public String getId() {
    return id;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @return the properties
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
   * @param o
   *          The Property to remove.
   * @return true if removed.
   * @see java.util.List#remove(java.lang.Object)
   */
  public boolean removeProperty(Object o) {
    return properties.remove(o);
  }

  /**
   * @param admin
   *          the admin to set
   */
  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  /**
   * @param id
   *          the id to set.
   */
  public void setDBId(Long id) {
    this.dbId = id;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @param firstName
   *          the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @param lastName
   *          the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @param password
   *          the password to set
   */
  public void setPassword(String password) {
    this.password = password;
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
   * @param properties
   *          the properties to set
   */
  public void setProperties(ArrayList<Property> properties) {
    this.properties = properties;
  }

}
