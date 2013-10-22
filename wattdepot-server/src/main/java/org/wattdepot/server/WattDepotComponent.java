/**
 * WattDepotComponent.java created on Oct 21, 2013 by Cam Moore.
 */
package org.wattdepot.server;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

/**
 * WattDepotComponent
 * 
 * @author Cam Moore
 * 
 */
public class WattDepotComponent extends Component {

  /**
   * Default constructor. Sets up the WattDepotComponent.
   */
  public WattDepotComponent() {
    setName("WattDepot HTTP API Server");
    setDescription("WattDepot3 RESTful server.");
    setAuthor("Cam Moore");
    // Add a CLAP client connector
    getClients().add(Protocol.CLAP);

    // Adds a HTTP server connector
    Server server = getServers().add(Protocol.HTTP, 8119);
    server.getContext().getParameters().set("tracing", "true");

    WattDepotApplication app = new WattDepotApplication();
    getDefaultHost().attachDefault(app);

    // Configure the log service
    getLogService().setLoggerName("WattDepot3.AccessLog");
    getLogService().setLogPropertiesRef("clap://system/org/wattdepot/server/log.properties");
  }

  /**
   * @param args
   *          Command line arguments.
   * @throws Exception
   *           if there is a problem with starting the Component.
   */
  public static void main(String[] args) throws Exception {
    new WattDepotComponent().start();
  }
}
