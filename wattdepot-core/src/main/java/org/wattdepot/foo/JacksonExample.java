/**
 * JacksonExample.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.foo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.wattdepot.core.datamodel.Location;
import org.wattdepot.core.datamodel.Property;
import org.wattdepot.core.datamodel.Sensor;
import org.wattdepot.core.datamodel.SensorModel;
import org.wattdepot.core.datamodel.User;

/**
 * JacksonExample trying to output JSON.
 * 
 * @author Cam Moore
 * 
 */
public class JacksonExample {

  /**
   * @param args
   *          command line arguments.
   * @throws IOException
   *           if problem.
   * @throws JsonMappingException
   *           if problem.
   * @throws JsonGenerationException
   *           if problem.
   */
  public static void main(String[] args) throws JsonGenerationException, JsonMappingException,
      IOException {

    User user1 = new User("id1", "name1", "password1", false);
    user1.addProperty(new Property("UH", "yes"));

    ObjectMapper mapper = new ObjectMapper();
//    mapper.writeValue(System.out, user1);
    System.out.println();
    Location loc1 = new Location("ilima-6th-floor", new Double(21.294642), new Double(-157.812727),
        new Double(30), "Hale Aloha Ilima residence hall 6th floor");
    SensorModel model1 = new SensorModel("model1", "protocol1", "type1", "version1");
    Sensor sensor1 = new Sensor("sensor1", "uri1", loc1, model1);
//    mapper = new ObjectMapper();
    mapper.writeValue(System.out, sensor1);
    System.out.println();
  }

}
