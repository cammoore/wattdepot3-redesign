/**
 * SensorModelsResouce.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.SensorModel;

/**
 * SensorModelsResouce - HTTP Interface for SensorModels.
 *
 * @author Cam Moore
 *
 */
public interface SensorModelsResouce {
  /**
   * Defines the GET /wattdepot/sensormodels/ API call.
   * 
   * @return a List of the defined SensorModels.
   */
  @Get
  public ArrayList<SensorModel> retrieve();


}
