/**
 * UserServerApplication.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.server;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.wattdepot.server.restlet.LocationServerResource;
import org.wattdepot.server.restlet.LocationsServerResource;
import org.wattdepot.server.restlet.SensorServerResource;

/**
 * UserServerApplication Server app.
 * 
 * @author Cam Moore
 * 
 */
public class ServerRestletApplication extends Application {

  /**
   * @param args
   *          the command line arguments.
   * @throws Exception
   *           if there is a problem.
   */
  public static void main(String[] args) throws Exception {
    Component mailServer = new Component();
    mailServer.getServers().add(Protocol.HTTP, 8111);
    mailServer.getDefaultHost().attach(new ServerRestletApplication());
    mailServer.start();
  }

  /**
   * Creates a root Router to dispatch call to server resources.
   * 
   * @return the inbound root.
   */
  @Override
  public Restlet createInboundRoot() {
    Router router = new Router(getContext());
    router.attach("/wattdepot/location/", LocationServerResource.class);
    router.attach("/wattdepot/location/{location_id}", LocationServerResource.class);
    router.attach("/wattdepot/locations/", LocationsServerResource.class);
    router.attach("/wattdepot/sensor/", SensorServerResource.class);
    router.attach("/wattdepot/sensor/{sensor_id}", SensorServerResource.class);
    return router;
  }
}