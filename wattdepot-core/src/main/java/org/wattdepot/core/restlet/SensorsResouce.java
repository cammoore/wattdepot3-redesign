/**
 * SensorsResouce.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.Sensor;

/**
 * SensorsResouce - HTTP Interface for Sensors.
 * 
 * @author Cam Moore
 * 
 */
public interface SensorsResouce {
  /**
   * Defines the GET /wattdepot/sensors/ API call.
   * 
   * @return a List of the defined Sensors.
   */
  @Get
  public ArrayList<Sensor> retrieve();

}
