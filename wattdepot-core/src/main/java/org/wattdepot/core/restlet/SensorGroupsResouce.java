/**
 * SensorGroupsResouce.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.core.restlet;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.wattdepot.core.datamodel.SensorGroup;

/**
 * SensorGroupsResouce - HTTP Interface for SensorGroups.
 * 
 * @author Cam Moore
 * 
 */
public interface SensorGroupsResouce {
  /**
   * Defines the GET /wattdepot/sensorgroups/ API call.
   * 
   * @return a List of the defined SensorGroups.
   */
  @Get
  public ArrayList<SensorGroup> retrieve();

}
