/**
 * UserGroup.java created on Oct 14, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import java.util.ArrayList;

/**
 * UserGroup - A group of users.
 * 
 * @author Cam Moore
 * 
 */
public class UserGroup {
  /** The admin user group.  */
  public static final UserGroup ADMIN_GROUP = new UserGroup("adminGroup");

  /** A unique id. */
  protected String id;
  /** The users in this group. */
  protected ArrayList<UserInfo> users;

  static {
    ADMIN_GROUP.add(UserInfo.ADMIN);
  }
  
  /**
   * Hide the default constructor.
   */
  protected UserGroup() {

  }

  /**
   * @param id
   *          The unique id for this group. It must also be unique from any
   *          User's id.
   */
  public UserGroup(String id) {
    this.id = id;
    this.users = new ArrayList<UserInfo>();
  }

  /**
   * @param e
   *          The User to add.
   * @return true if successful.
   * @see java.util.List#add(java.lang.Object)
   */
  public boolean add(UserInfo e) {
    return users.add(e);
  }

  /**
   * @param o
   *          The user to test.
   * @return true if the user is in the group.
   * @see java.util.List#contains(java.lang.Object)
   */
  public boolean contains(Object o) {
    return users.contains(o);
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
    UserGroup other = (UserGroup) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    }
    else if (!id.equals(other.id)) {
      return false;
    }
    if (users == null) {
      if (other.users != null) {
        return false;
      }
    }
    else if (!users.equals(other.users)) {
      return false;
    }
    return true;
  }

  /**
   * @return The unique id for the UserGroup.
   */
  public String getId() {
    return id;
  }

  /**
   * @return the users.
   */
  public ArrayList<UserInfo> getUsers() {
    return users;
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
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((users == null) ? 0 : users.hashCode());
    return result;
  }

  /**
   * @param o
   *          The User to remove.
   * @return true if successful.
   * @see java.util.List#remove(java.lang.Object)
   */
  public boolean remove(Object o) {
    return users.remove(o);
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @param users the users to set
   */
  public void setUsers(ArrayList<UserInfo> users) {
    this.users = users;
  }
  
  

  /**
   * @return The JSON String representation of this SensorGroup.
   */
  public String toJSON() {
    StringBuffer buf = new StringBuffer();
    buf.append("{id :\"");
    buf.append(this.id);
    buf.append("\", \"users\": [");
    for (UserInfo u: users) {
      buf.append(u.toJSON());
      buf.append(",");
    }
    if (users.size() > 0) {
      // remove trailing ,
      buf.deleteCharAt(buf.length() - 1);
    }
    buf.append("]}");
    return buf.toString();
  }

  /**
   * @return The JSON version of this Sensor with IDs.
   */
  public String toShortJSON() {
    StringBuffer buf = new StringBuffer();
    buf.append("{id: \"");
    buf.append(this.id);
    buf.append("\", \"userIds\": [");
    for (UserInfo u: users) {
      buf.append(u.getId());
      buf.append(",");
    }
    if (users.size() > 0) {
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
    return "UserGroup [id=" + id + ", users=" + users + "]";
  }

}
