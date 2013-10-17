/**
 * BaseModel.java created on Oct 14, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseModel - Base class for most data model classes.
 * 
 * @author Cam Moore
 * 
 */
public class BaseModel {
  protected String id;
  private List<UserGroup> owner;

  /**
   * Default constructor.
   */
  protected BaseModel() {
    owner = new ArrayList<UserGroup>();
    // The admin group owns all objects.
    owner.add(UserGroup.ADMIN_GROUP);    
  }
  
  /**
   * Creates a new instance with the given id.
   * 
   * @param id
   *          The unique id for this instance.
   */
  public BaseModel(String id) {
    this.id = id;
    owner = new ArrayList<UserGroup>();
    // The admin group owns all objects.
    owner.add(UserGroup.ADMIN_GROUP);
  }


  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @return the owner
   */
  public UserGroup owner() {
    if (owner.size() > 1) {
      return owner.get(1);
    }
    else {
      return owner.get(0);
    }
  }

  /**
   * @param owner
   *          the owner to set
   */
  public void setOwner(UserGroup owner) {
    if (!owner.equals(UserGroup.ADMIN_GROUP)) {
      this.owner.add(1, owner);
    }
  }

  /**
   * Tests to see if the given user is in the owner UserGroup.
   * 
   * @param user
   *          The user to test.
   * @return true if the user is in the owner's UserGroup.
   */
  public boolean isAllowed(User user) {
    if (owner != null) {
      return (owner.contains(user) ? true : false);
    }
    return false;
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
    result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
    BaseModel other = (BaseModel) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    }
    else if (!id.equals(other.id)) {
      return false;
    }
    if (owner == null) {
      if (other.owner != null) {
        return false;
      }
    }
    else if (!owner.equals(other.owner)) {
      return false;
    }
    return true;
  }

}
