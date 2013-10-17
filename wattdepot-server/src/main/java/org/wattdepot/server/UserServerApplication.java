/**
 * UserServerApplication.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.server;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * UserServerApplication Server app.
 * 
 * @author Cam Moore
 * 
 */
public class UserServerApplication extends Application {

  /**
   * @param args the command line arguments.
   * @throws Exception  if there is a problem.
   */
  public static void main(String[] args) throws Exception {
    Component mailServer = new Component();
    mailServer.getServers().add(Protocol.HTTP, 8111);
    mailServer.getDefaultHost().attach(new UserServerApplication());
    mailServer.start();
  }

  /**
   * Creates a root Router to dispatch call to server resources.
   * @return the inbound root.
   */
  @Override
  public Restlet createInboundRoot() {
      Router router = new Router(getContext());
      router.attach("/accounts/{accountId}/mails/{mailId}",
              LocationResource.class);
      return router;
  }
}
