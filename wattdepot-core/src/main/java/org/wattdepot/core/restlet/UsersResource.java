/**
 * UsersResource.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.User;

/**
 * UsersResource - The HTTP API for Users.
 * 
 * @author Cam Moore
 * 
 */
public interface UsersResource {

  /**
   * Defines the GET /wattdepot/users/ API call.
   * 
   * @return a List of the defined Users.
   */
  @Get
  public ArrayList<User> retrieve();

}
