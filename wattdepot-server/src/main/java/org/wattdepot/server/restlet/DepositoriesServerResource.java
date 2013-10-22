/**
 * DepositorysServerResource.java created on Oct 17, 2013 by Cam Moore.
 */
package org.wattdepot.server.restlet;

import java.util.ArrayList;

import org.restlet.resource.ServerResource;
import org.wattdepot.core.datamodel.Depository;
import org.wattdepot.core.restlet.DepositoriesResource;

/**
 * DepositorysServerResource
 *
 * @author Cam Moore
 *
 */
public class DepositoriesServerResource extends ServerResource implements DepositoriesResource {

  /* (non-Javadoc)
   * @see org.wattdepot.core.restlet.DepositorysResource#retrieve()
   */
  @Override
  public ArrayList<Depository> retrieve() {
    System.out.println("GET /wattdepot/locations/");
    ArrayList<Depository> ret = new ArrayList<Depository>();
    ret.add(new Depository("ilima-energy1", "Ilima energy consumption", "energy"));
    return ret;
  }

}
