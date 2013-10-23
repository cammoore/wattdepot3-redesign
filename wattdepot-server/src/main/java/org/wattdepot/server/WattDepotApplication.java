/**
 * UserServerApplication.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.wattdepot.server.depository.impl.jpa.JPAWattDepot;
import org.wattdepot.server.restlet.DepositoriesServerResource;
import org.wattdepot.server.restlet.DepositoryMeasurementsServerResource;
import org.wattdepot.server.restlet.DepositoryServerResource;
import org.wattdepot.server.restlet.DepositoryValueServerResource;
import org.wattdepot.server.restlet.LocationServerResource;
import org.wattdepot.server.restlet.LocationsServerResource;
import org.wattdepot.server.restlet.SensorGroupServerResource;
import org.wattdepot.server.restlet.SensorGroupsServerResource;
import org.wattdepot.server.restlet.SensorModelServerResource;
import org.wattdepot.server.restlet.SensorModelsServerResource;
import org.wattdepot.server.restlet.SensorProcessServerResource;
import org.wattdepot.server.restlet.SensorProcessesServerResource;
import org.wattdepot.server.restlet.SensorServerResource;
import org.wattdepot.server.restlet.SensorsServerResource;

/**
 * UserServerApplication Server app.
 * 
 * @author Cam Moore
 * 
 */
public class WattDepotApplication extends Application {

  private WattDepot depot;
  
  public WattDepotApplication() {
    setName("WattDepot Application");
    setDescription("WattDepot HTTP API implementation");
    setAuthor("Cam Moore");
    // Use settings to instantiate the right WattDepot instance.
    depot = new JPAWattDepot();
  }
  
  
  /**
   * @return the depot
   */
  public WattDepot getDepot() {
    return depot;
  }


  /**
   * @param depot the depot to set
   */
  public void setDepot(WattDepot depot) {
    this.depot = depot;
  }


  /**
   * Creates a root Router to dispatch call to server resources.
   * 
   * @return the inbound root.
   */
  @Override
  public Restlet createInboundRoot() {
    Router router = new Router(getContext());
//    router.attach("/wattdepot/{group_id}/", GroupServerResource.class);
    router.attach("/wattdepot/{group_id}/depository/", DepositoryServerResource.class);
    router.attach("/wattdepot/{group_id}/depository/{depository_id}", DepositoryServerResource.class);
    router.attach("/wattdepot/{group_id}/depository/{depository_id}/measurements/",
        DepositoryMeasurementsServerResource.class);
    router.attach("/wattdepot/{group_id}/depository/{depository_id}/value/",
        DepositoryValueServerResource.class);
    router.attach("/wattdepot/{group_id}/depositories/", DepositoriesServerResource.class);
    router.attach("/wattdepot/{group_id}/location/", LocationServerResource.class);
    router.attach("/wattdepot/{group_id}/location/{location_id}", LocationServerResource.class);
    router.attach("/wattdepot/{group_id}/locations/", LocationsServerResource.class);
    router.attach("/wattdepot/{group_id}/sensorgroup/", SensorGroupServerResource.class);
    router.attach("/wattdepot/{group_id}/sensorgroup/{sensorgroup_id}", SensorGroupServerResource.class);
    router.attach("/wattdepot/{group_id}/sensorgroups/", SensorGroupsServerResource.class);
    router.attach("/wattdepot/{group_id}/sensormodel/", SensorModelServerResource.class);
    router.attach("/wattdepot/{group_id}/sensormodel/{sensormodel_id}", SensorModelServerResource.class);
    router.attach("/wattdepot/{group_id}/sensormodels/", SensorModelsServerResource.class);
    router.attach("/wattdepot/{group_id}/sensorprocess/", SensorProcessServerResource.class);
    router.attach("/wattdepot/{group_id}/sensorprocess/{sensorprocess_id}", SensorProcessServerResource.class);
    router.attach("/wattdepot/{group_id}/sensorprocesses/", SensorProcessesServerResource.class);
    router.attach("/wattdepot/{group_id}/sensor/", SensorServerResource.class);
    router.attach("/wattdepot/{group_id}/sensor/{sensor_id}", SensorServerResource.class);
    router.attach("/wattdepot/{group_id}/sensors/", SensorsServerResource.class);

    ChallengeAuthenticator authenticator = new ChallengeAuthenticator(getContext(),
        ChallengeScheme.HTTP_BASIC, "WattDepot Realm");
    authenticator.setNext(router);
    return authenticator;
    
//    return router;
  }
}