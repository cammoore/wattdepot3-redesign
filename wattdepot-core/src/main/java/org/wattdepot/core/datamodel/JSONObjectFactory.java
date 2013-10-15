/**
 * JSONObjectFactory.java created on Oct 15, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSONObjectFactory - Factory class for building data model class instances
 * from JSON strings.
 * 
 * @author Cam Moore
 * 
 */
public class JSONObjectFactory {

  /**
   * Creates a new Location from a JSON representation of the Location.
   * 
   * @param json
   *          The JSON representation of a Location.
   * @return A new Location built from the JSON String.
   * @throws JSONException
   *           if there is a problem with the JSON string.
   */
  public static Location buildLocation(String json) throws JSONException {
    JSONObject obj = new JSONObject(json);
    Location loc = new Location(obj.getString("id"), obj.getDouble("latitude"),
        obj.getDouble("longitude"), obj.getDouble("altitude"), obj.getString("description"));
    return loc;
  }

  /**
   * Creates a new SensorModel from a JSON representation of the SensorModel.
   * 
   * @param json
   *          The JSON representation of a SensorModel.
   * @return A new SensorModel built from the JSON String.
   * @throws JSONException
   *           if there is a problem with the JSON string.
   */
  public static SensorModel buildSensorModel(String json) throws JSONException {
    JSONObject obj = new JSONObject(json);
    SensorModel model = new SensorModel(obj.getString("id"), obj.getString("protocol"),
        obj.getString("type"), obj.getString("version"));
    return model;
  }

  /**
   * Creates a new User from a JSON representation of the User.
   * 
   * @param json
   *          The JSON representation of a User.
   * @return A new User built from the JSON String.
   * @throws JSONException
   *           if there is a problem with the JSON string.
   */
  public static User buildUser(String json) throws JSONException {
    JSONObject obj = new JSONObject(json);
    User user = new User(obj.getString("id"), obj.getString("name"), obj.getString("password"),
        obj.getBoolean("admin"));
    return user;
  }
}
