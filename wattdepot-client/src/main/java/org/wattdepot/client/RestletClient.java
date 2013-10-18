/**
 * UserClient.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.client;

import org.restlet.resource.ClientResource;
import org.wattdepot.core.datamodel.Location;
import org.wattdepot.core.restlet.LocationResource;

/**
 * UserClient foo.
 * 
 * @author Cam Moore
 * 
 */
public class RestletClient {
  /**
   * @param args
   *          command line arguments.
   * @throws Exception
   *           if there is a problem.
   */
  public static void main(String[] args) throws Exception {
    LocationResource locationRoot = ClientResource.create(
        "http://localhost:8111/wattdepot/location/ilima-6th", LocationResource.class);
    Location loc = locationRoot.retrieve();
    System.out.println("Got the following " + loc);
    System.out.println("calling store with null");
    Location loc1 = new Location("loc1", new Double(21.294642), new Double(-157.812727), new Double(
        30), "Hale Aloha Ilima residence hall 6th floor");

    locationRoot.store(loc1);
    System.out.println("calling remove with null");
    locationRoot.remove();
  }
}
