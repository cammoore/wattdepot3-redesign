/**
 * BaseModel.java created on Oct 14, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

/**
 * BaseModel - Base class for most data model classes.
 * 
 * @author Cam Moore
 * 
 */
public class BaseModel {
  private String id;
  private UserGroup owner;

  /**
   * Creates a new instance with the given id.
   * 
   * @param id
   *          The unique id for this instance.
   */
  public BaseModel(String id) {
    this.id = id;
  }

  /**
   * @return The unique id for this instance.
   */
  public String id() {
    return this.id;
  }

  /**
   * @return the owner
   */
  public UserGroup getOwner() {
    return owner;
  }

  /**
   * @param owner
   *          the owner to set
   */
  public void setOwner(UserGroup owner) {
    this.owner = owner;
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
}
