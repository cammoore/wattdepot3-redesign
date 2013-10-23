/**
 * JPAUserInfo.java created on Oct 23, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

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
   * @return the id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getDBId() {
    return dbId;
  }

  /**
   * @param id
   *          the id to set.
   */
  public void setDBId(Long id) {
    this.dbId = id;
  }

}
