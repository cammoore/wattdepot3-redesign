/**
 * JPASensorModel.java created on Oct 4, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.wattdepot.core.datamodel.SensorModel;

/**
 * JPASensorModel persistent version of SensorModel using JPA.
 * 
 * @author Cam Moore
 * 
 */
@Entity
public class JPASensorModel extends SensorModel {
  /** The database id. */
  private Long dbId;

  /** Hide the default constructor. */
  protected JPASensorModel() {
    super();
  }

  /**
   * @param id2
   *          The unique String id.
   * @param protocol
   *          The protocol.
   * @param type
   *          The type.
   * @param version
   *          The version.
   */
  public JPASensorModel(String id2, String protocol, String type, String version) {
    super(id2, protocol, type, version);
  }

  /**
   * @param sm
   *          The SensorModel to clone.
   */
  protected JPASensorModel(SensorModel sm) {
    super(sm.getId(), sm.getProtocol(), sm.getType(), sm.getVersion());
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

  /**
   * @return the protocol
   */
  public String getProtocol() {
    return protocol;
  }

  /**
   * @param protocol
   *          the protocol to set
   */
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the version
   */
  public String getVersion() {
    return version;
  }

  /**
   * @param version
   *          the version to set
   */
  public void setVersion(String version) {
    this.version = version;
  }

}
