/**
 * User.java created on Oct 14, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * User - represents a user of WattDepot3.
 * 
 * @author Cam Moore
 * 
 */
public class UserInfo {
  /** The admin user. */
  public static final UserInfo ADMIN = new UserInfo("admin", "admin", null, null, "admin", true);

  /** A unique id for the User. */
  protected String id;
  /** The User's first name. */
  protected String firstName;
  /** The User's last name. */
  protected String lastName;
  /** The User's email address. */
  protected String email;
  /** The User's password. */
  protected String password;
  /** True if the user is an admin. */
  protected Boolean admin;
  /** Additional properties of the user. */
  protected ArrayList<Property> properties;

  static {
    String password = System.getenv("wattdepot-server.admin.password");
    if (password != null) {
      ADMIN.setPassword(password);
    }
  }

  /**
   * Hide the default constructor.
   */
  protected UserInfo() {

  }

  /**
   * Creates a new UserInfo with the given information.
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
  public UserInfo(String id, String firstName, String lastName, String email, String password,
      Boolean admin) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.admin = admin;
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
    UserInfo other = (UserInfo) obj;
    if (admin == null) {
      if (other.admin != null) {
        return false;
      }
    }
    else if (!admin.equals(other.admin)) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    }
    else if (!id.equals(other.id)) {
      return false;
    }
    if (firstName == null) {
      if (other.firstName != null) {
        return false;
      }
    }
    else if (!firstName.equals(other.firstName)) {
      return false;
    }
    if (password == null) {
      if (other.password != null) {
        return false;
      }
    }
    else if (!password.equals(other.password)) {
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
    return true;
  }

  /**
   * @return the admin
   */
  public Boolean getAdmin() {
    return admin;
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
  public List<Property> getProperties() {
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((admin == null) ? 0 : admin.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
   * @param admin
   *          the admin to set
   */
  public void setAdmin(Boolean admin) {
    this.admin = admin;
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
   * @param id the id to set
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
   * @param properties the properties to set
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
   * @return The JSON version of this User.
   */
  public String toJSON() {
    StringBuffer buf = new StringBuffer();
    buf.append("{\"id\": \"");
    buf.append(this.id);
    buf.append("\", \"name\": \"");
    buf.append(this.firstName);
    buf.append("\", \"password\": \"");
    buf.append(this.password);
    buf.append("\", \"admin\": ");
    buf.append(this.admin);

    buf.append(", \"properties\": [");
    for (Property p : this.properties) {
      buf.append("{\"key\": \"" + p.getKey() + "\", \"value\": \"" + p.getValue() + "\"},");
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
    return "User [id=" + id + ", first name=" + firstName + ", password=" + password + ", admin="
        + admin + ", properties=" + properties + "]";
  }
}
