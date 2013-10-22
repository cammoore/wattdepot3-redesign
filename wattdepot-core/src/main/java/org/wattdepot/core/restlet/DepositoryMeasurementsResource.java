/**
 * DepositoryValueResource.java created on Oct 18, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.Measurement;

/**
 * DepositorySensorsResource - HTTP Interface for getting the Measurements in
 * the Depository.
 * 
 * @author Cam Moore
 * 
 */
public interface DepositoryMeasurementsResource {

  /**
   * Defines GET /wattdepot/depository/{depository_id}/measurements/ API call.
   * 
   * @return An ArrayList of the Measurements that have sent measurements to the
   *         depository.
   */
  @Get("json")
  // Use JSON as transport encoding.
  public ArrayList<Measurement> retrieve();
}
