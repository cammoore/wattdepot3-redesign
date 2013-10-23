/**
 * UserResource.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.wattdepot.core.datamodel.UserInfo;

/**
 * UserResource - HTTP Interface for data model User.
 * 
 * @author Cam Moore
 * 
 */
public interface UserInfoResource {

  /**
   * Defines GET /wattdepot/user/{user_id} API call.
   * 
   * @return The User with the given id. The id is sent in the request.
   */
  @Get("json") // Use JSON as transport encoding.
  public UserInfo retrieve();

  /**
   * Defines the PUT /wattdepot/user/ API call.
   * 
   * @param user
   *          The User to store.
   */
  @Put
  public void store(UserInfo user);

  /**
   * Defined the DEL /wattdepot/user/{user_id} API call. The id is sent in the
   * request.
   */
  @Delete
  public void remove();

}
