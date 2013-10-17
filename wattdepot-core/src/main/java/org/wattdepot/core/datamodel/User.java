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
public class User {
  /** The admin user. */
  public static final User ADMIN = new User("admin", "admin", "admin", true);

  /** A unique id for the User. */
  protected String id;
  /** The User's name. */
  protected String name;
  /** The User's password. */
  protected String password;
  /** True if the user is an admin. */
  protected Boolean admin;
  /** Additional properties of the user. */
  protected List<Property> properties;

  static {
    String password = System.getenv("wattdepot-server.admin.password");
    if (password != null) {
      ADMIN.setPassword(password);
    }
  }

  /**
   * Hide the default constructor.
   */
  protected User() {

  }

  /**
   * Creates a new User with the given information.
   * 
   * @param id
   *          The unique id.
   * @param name
   *          The user's name.
   * @param password
   *          The user's password.
   * @param admin
   *          True if they are an admin.
   */
  public User(String id, String name, String password, Boolean admin) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.admin = admin;
    this.properties = new ArrayList<Property>();
  }

  /**
   * @return the unique id.
   */
  public String getId() {
    return id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   *          the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the admin
   */
  public Boolean getAdmin() {
    return admin;
  }

  /**
   * @param admin
   *          the admin to set
   */
  public void setAdmin(Boolean admin) {
    this.admin = admin;
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

  /**
   * @param e
   *          The Property to add.
   * @return true if added.
   * @see java.util.List#add(java.lang.Object)
   */
  public boolean addProperty(Property e) {
    return properties.add(e);
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
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
    User other = (User) obj;
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
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    }
    else if (!name.equals(other.name)) {
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", password=" + password + ", admin=" + admin
        + ", properties=" + properties + "]";
  }

  /**
   * @return The JSON version of this User.
   */
  public String toJSON() {
    StringBuffer buf = new StringBuffer();
    buf.append("{\"id\": \"");
    buf.append(this.id);
    buf.append("\", \"name\": \"");
    buf.append(this.name);
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
}
