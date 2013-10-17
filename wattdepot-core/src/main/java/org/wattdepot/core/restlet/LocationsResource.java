/**
 * LocationsResource.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.Location;

/**
 * LocationsResource - The HTTP API for Locations.
 * 
 * @author Cam Moore
 * 
 */
public interface LocationsResource {

  /**
   * Defines the GET /wattdepot/locations/ API call.
   * 
   * @return a List of the defined Locations.
   */
  @Get
  public ArrayList<Location> retrieve();

}
