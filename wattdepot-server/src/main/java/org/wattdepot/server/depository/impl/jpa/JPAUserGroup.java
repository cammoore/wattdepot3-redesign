/**
 * JPAUserGroup.java created on Oct 24, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.wattdepot.core.datamodel.UserGroup;
import org.wattdepot.core.datamodel.UserInfo;

/**
 * JPAUserGroup
 * 
 * @author Cam Moore
 * 
 */
@Entity
public class JPAUserGroup extends UserGroup {
  /** The database id. */
  private Long dbId;

  /** Default constructor. */
  protected JPAUserGroup() {
    super();
  }

  /**
   * @param id
   *          The unique id for this group.
   */
  public JPAUserGroup(String id) {
    super(id);
  }

  /**
   * @param group
   *          The UserGroup to clone.
   */
  public JPAUserGroup(UserGroup group) {
    super(group.getId());
    for (UserInfo i : group.getUsers()) {
      add(new JPAUserInfo(i));
    }
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

  /**
   * @param id
   *          the id to set.
   */
  public void setDBId(Long id) {
    this.dbId = id;
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
}
