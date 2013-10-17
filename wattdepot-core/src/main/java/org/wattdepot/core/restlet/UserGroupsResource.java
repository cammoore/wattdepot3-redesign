/**
 * UserGroupsResource.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.UserGroup;

/**
 * UserGroupsResource - The HTTP API for UserGroups.
 * 
 * @author Cam Moore
 * 
 */
public interface UserGroupsResource {

  /**
   * Defines the GET /wattdepot/usergroups/ API call.
   * 
   * @return a List of the defined UserGroups.
   */
  @Get
  public ArrayList<UserGroup> retrieve();

}
