/**
 * LocationResource.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.server;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.xstream.XstreamRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.wattdepot.core.datamodel.Location;
import org.wattdepot.core.datamodel.Property;
import org.wattdepot.core.datamodel.Sensor;
import org.wattdepot.core.datamodel.SensorModel;
import org.wattdepot.core.datamodel.User;

/**
 * LocationResource playing with different representations.
 * 
 * @author Cam Moore
 * 
 */
public class LocationResource extends ServerResource {

  @Override
  protected void doInit() throws ResourceException {
    // Declares the two variants supported
    getVariants().add(new Variant(MediaType.APPLICATION_XML));
    getVariants().add(new Variant(MediaType.APPLICATION_JSON));
  }

  @Override
  protected Representation get(Variant variant) throws ResourceException {
    Representation result = null;

    User user1 = new User("id1", "name1", "password1", false);
    user1.addProperty(new Property("UH", "yes"));
    Location loc1 = new Location("ilima-6th-floor", new Double(21.294642), new Double(-157.812727),
        new Double(30), "Hale Aloha Ilima residence hall 6th floor");
    SensorModel model1 = new SensorModel("model1", "protocol1", "type1", "version1");
    Sensor sensor1 = new Sensor("sensor1", "uri1", loc1, model1);
    if (MediaType.APPLICATION_XML.isCompatible(variant.getMediaType())) {
      // Wraps the bean with an XStream representation
      result = new XstreamRepresentation<Sensor>(sensor1);
    }
    else if (MediaType.APPLICATION_JSON.isCompatible(variant.getMediaType())) {
      // Wraps the bean with a Jackson representation
      result = new JacksonRepresentation<Sensor>(sensor1);
    }
    return result;
  }

  @Override
  protected Representation put(Representation representation, Variant variant)
      throws ResourceException {
    Sensor user = null;

    try {
      if (MediaType.APPLICATION_XML.isCompatible(representation.getMediaType())) {
        // Parse the XML representation to get the mail bean
        user = new XstreamRepresentation<Sensor>(representation, Sensor.class).getObject();
        System.out.println("XML representation received " + user);
      }
      else if (MediaType.APPLICATION_JSON.isCompatible(representation.getMediaType())) {
        // Parse the JSON representation to get the mail bean
        user = new JacksonRepresentation<Sensor>(representation, Sensor.class).getObject();
        System.out.println("JSON representation received " + user);
      }

      if (user != null) {
        // Output the mail bean
        System.out.println(user);
      }
    }
    catch (IOException e) {
      throw new ResourceException(e);
    }

    return null;
  }

}
