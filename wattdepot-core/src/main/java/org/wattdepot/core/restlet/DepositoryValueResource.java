/**
 * DepositoryValueResource.java created on Oct 18, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.MeasuredValue;

/**
 * DepositoryValueResource - HTTP Interface for getting the MeasuredValues. <br>
 * GET /wattdepot/depository/{depository_id}/value/?sensor={sensor_id},time
 * ={timestamp} or <br>
 * GET /wattdepot/depository/{depository_id}/value/?sensor={sensor_id
 * },start={timestamp},end={timestamp}
 * 
 * @author Cam Moore
 * 
 */
public interface DepositoryValueResource {

  /**
   * Defines GET
   * /wattdepot/depository/{depository_id}/value/?sensor={sensor_id},
   * time={timestamp} or <br>GET
   * /wattdepot/depository/{depository_id}/value/?sensor=
   * {sensor_id},start={timestamp},end={timestamp}.
   * 
   * @return The MeasuredValue.
   */
  @Get("json")
  // Use JSON ast transport encoding.
  public MeasuredValue retrieve();
}
