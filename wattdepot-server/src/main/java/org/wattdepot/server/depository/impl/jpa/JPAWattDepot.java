/**
 * JpaWattDepot.java created on Oct 4, 2013 by Cam Moore.
 */
package org.wattdepot.server.depository.impl.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.wattdepot.server.IdNotFoundException;
import org.wattdepot.server.UniqueIdException;
import org.wattdepot.server.WattDepot;
import org.wattdepot.core.datamodel.Location;
import org.wattdepot.core.datamodel.Sensor;
import org.wattdepot.core.datamodel.SensorGroup;
import org.wattdepot.core.datamodel.SensorModel;
import org.wattdepot.core.datamodel.SensorProcess;
import org.wattdepot.core.datamodel.Depository;
import org.wattdepot.core.datamodel.UserGroup;
import org.wattdepot.core.datamodel.UserInfo;

/**
 * JpaWattDepot is the JPA implementation of WattDepot.
 * 
 * @author Cam Moore
 * 
 */
public class JPAWattDepot extends WattDepot {
  private Map<String, JPAWattDepository> depositories;
  private Map<String, JPASensorGroup> sensorGroups;
  private Map<String, JPASensor> sensors;
  private Map<String, JPASensorModel> sensorModels;
  private Map<String, JPASensorProcess> sensorProcesses;
  private Map<String, JPALocation> locations;
  private Map<String, JPAUserInfo> users;
  private Map<String, JPAUserGroup> groups;

  /** Default constructor. */
  public JPAWattDepot() {
    this.depositories = new HashMap<String, JPAWattDepository>();
    this.locations = new HashMap<String, JPALocation>();
    this.sensorGroups = new HashMap<String, JPASensorGroup>();
    this.sensorModels = new HashMap<String, JPASensorModel>();
    this.sensorProcesses = new HashMap<String, JPASensorProcess>();
    this.sensors = new HashMap<String, JPASensor>();
    this.users = new HashMap<String, JPAUserInfo>();
    this.groups = new HashMap<String, JPAUserGroup>();

    // Initialize the hash tables from the persistent store.
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    // depositories
    entityManager.getTransaction().begin();
    List<JPAWattDepositoryDescription> wdds = entityManager.createQuery(
        "from JPAWattDepositoryDescription", JPAWattDepositoryDescription.class).getResultList();
    for (JPAWattDepositoryDescription wdd : wdds) {
      JPAWattDepository depo = new JPAWattDepository(wdd.getId(), wdd.getName(),
          wdd.getMeasurementType());
      depositories.put(depo.getName(), depo);
    }
    entityManager.getTransaction().commit();
    // sensor groups
    entityManager.getTransaction().begin();
    List<JPASensorGroup> sgs = entityManager.createQuery("from JPASensorGroup",
        JPASensorGroup.class).getResultList();
    for (JPASensorGroup sg : sgs) {
      sensorGroups.put(sg.getId(), sg);
    }
    entityManager.getTransaction().commit();
    // sensors
    entityManager.getTransaction().begin();
    List<JPASensor> ss = entityManager.createQuery("from JPASensor", JPASensor.class)
        .getResultList();
    for (JPASensor s : ss) {
      sensors.put(s.getId(), s);
    }
    entityManager.getTransaction().commit();
    // sensor models
    entityManager.getTransaction().begin();
    List<JPASensorModel> sms = entityManager.createQuery("from JPASensorModel",
        JPASensorModel.class).getResultList();
    for (JPASensorModel sm : sms) {
      sensorModels.put(sm.getId(), sm);
    }
    entityManager.getTransaction().commit();
    // sensor processes
    entityManager.getTransaction().begin();
    List<JPASensorProcess> sps = entityManager.createQuery("from JPASensorProcess",
        JPASensorProcess.class).getResultList();
    for (JPASensorProcess sp : sps) {
      sensorProcesses.put(sp.getId(), sp);
    }
    entityManager.getTransaction().commit();
    // locations
    entityManager.getTransaction().begin();
    List<JPALocation> locs = entityManager.createQuery("from JPALocation", JPALocation.class)
        .getResultList();
    for (JPALocation l : locs) {
      locations.put(l.getId(), l);
    }
    entityManager.getTransaction().commit();
    // userinfos
    entityManager.getTransaction().begin();
    List<JPAUserInfo> infos = entityManager.createQuery("from JPAUserInfo", JPAUserInfo.class)
        .getResultList();
    for (JPAUserInfo i : infos) {
      users.put(i.getId(), i);
    }
    users.put(UserInfo.ADMIN.getId(), new JPAUserInfo(UserInfo.ADMIN));
    entityManager.getTransaction().commit();
    // groups
    entityManager.getTransaction().begin();
    List<JPAUserGroup> groupList = entityManager.createQuery("from JPAUserGroup",
        JPAUserGroup.class).getResultList();
    for (JPAUserGroup g : groupList) {
      groups.put(g.getId(), g);
    }
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineLocation(java.lang.String,
   * java.lang.Double, java.lang.Double, java.lang.Double, java.lang.String)
   */
  @Override
  public Location defineLocation(String id, Double latitude, Double longitude, Double altitude,
      String description) throws UniqueIdException {
    if (locations.containsKey(id)) {
      throw new UniqueIdException(id + " is already a Location unique id.");
    }
    JPALocation loc = new JPALocation(id, latitude, longitude, altitude, description);
    locations.put(loc.getId(), loc);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(loc);
    entityManager.getTransaction().commit();
    return loc;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineSensor(java.lang.String,
   * java.lang.String, org.wattdepot.server.datamodel.Location,
   * org.wattdepot.server.datamodel.SensorModel)
   */
  @Override
  public Sensor defineSensor(String id, String uri, Location l, SensorModel sm)
      throws UniqueIdException {
    if (sensors.containsKey(id)) {
      throw new UniqueIdException(id + " is already a Sensor unique id.");
    }
    JPASensor s = new JPASensor(id, uri, new JPALocation(l), new JPASensorModel(sm));
    sensors.put(s.getId(), s);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(s);
    entityManager.getTransaction().commit();
    return s;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineSensorGroup(java.lang.String,
   * org.wattdepot.server.datamodel.Sensor[])
   */
  @Override
  public SensorGroup defineSensorGroup(String id, Sensor... ss) throws UniqueIdException {
    if (sensorGroups.containsKey(id)) {
      throw new UniqueIdException(id + " is aready a SensorGroup unique id.");
    }
    JPASensorGroup sg = new JPASensorGroup(id);
    for (Sensor s : ss) {
      sg.add(new JPASensor(s));
    }
    sensorGroups.put(sg.getId(), sg);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(sg);
    entityManager.getTransaction().commit();
    return sg;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineSensorModel(java.lang.String,
   * java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public SensorModel defineSensorModel(String id, String protocol, String type, String version)
      throws UniqueIdException {
    if (sensorModels.containsKey(id)) {
      throw new UniqueIdException(id + " is already a SensorModel unique id.");
    }
    JPASensorModel sm = new JPASensorModel(id, protocol, type, version);
    sensorModels.put(sm.getId(), sm);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(sm);
    entityManager.getTransaction().commit();
    return sm;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineSensorProcess(java.lang.String,
   * org.wattdepot.server.datamodel.Sensor, java.lang.Long)
   */
  @Override
  public SensorProcess defineSensorProcess(String id, Sensor sensor, Long pollingInterval,
      String depositoryId) throws UniqueIdException {
    if (sensorProcesses.containsKey(id)) {
      throw new UniqueIdException(id + " is already a SensorProcess unique id.");
    }
    JPASensorProcess sp = new JPASensorProcess(id, sensor, pollingInterval, depositoryId);
    sensorProcesses.put(sp.getId(), sp);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(sp);
    entityManager.getTransaction().commit();
    return sp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineUserGroup(java.lang.String,
   * org.wattdepot.core.datamodel.UserInfo[])
   */
  @Override
  public UserGroup defineUserGroup(String id, UserInfo... users) throws UniqueIdException {
    if (groups.containsKey(id)) {
      throw new UniqueIdException(id + " is already a UserGroup id.");
    }
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    JPAUserGroup g = new JPAUserGroup(id);
    for (UserInfo i : users) {
      JPAUserInfo ji = new JPAUserInfo(i);
      entityManager.persist(ji);
      g.add(ji);
    }
    entityManager.persist(g);
    entityManager.getTransaction().commit();    
    return g;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineUserInfo(java.lang.String,
   * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
   * java.lang.Boolean)
   */
  @Override
  public UserInfo defineUserInfo(String id, String firstName, String lastName, String email,
      String password, Boolean admin) throws UniqueIdException {
    if (users.containsKey(id)) {
      throw new UniqueIdException(id + " is already a UserInfo unique id.");
    }
    JPAUserInfo info = new JPAUserInfo(id, firstName, lastName, email, password, admin);
    users.put(info.getId(), info);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(info);
    entityManager.getTransaction().commit();
    return info;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#defineWattDepository(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  @Override
  public Depository defineWattDepository(String id, String name, String measurementType)
      throws UniqueIdException {
    if (depositories.containsKey(id)) {
      throw new UniqueIdException(id + " is already a WattDepository unique id.");
    }
    JPAWattDepositoryDescription wdd = new JPAWattDepositoryDescription(id, name, measurementType);
    JPAWattDepository wd = new JPAWattDepository(id, name, measurementType);
    depositories.put(wd.getId(), wd);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(wdd);
    entityManager.getTransaction().commit();
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteLocation(java.lang.String)
   */
  @Override
  public void deleteLocation(String id) throws IdNotFoundException {
    Location loc = locations.get(id);
    if (loc == null) {
      throw new IdNotFoundException(id + " is not a defined location id.");
    }
    locations.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(loc);
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteSensor(java.lang.String)
   */
  @Override
  public void deleteSensor(String id) throws IdNotFoundException {
    Sensor s = sensors.get(id);
    if (s == null) {
      throw new IdNotFoundException(id + " is not a defined sensor id.");
    }
    sensors.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(s);
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteSensorGroup(java.lang.String)
   */
  @Override
  public void deleteSensorGroup(String id) throws IdNotFoundException {
    SensorGroup s = sensorGroups.get(id);
    if (s == null) {
      throw new IdNotFoundException(id + " is not a defined sensor group id.");
    }
    sensorGroups.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(s);
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteSensorModel(java.lang.String)
   */
  @Override
  public void deleteSensorModel(String id) throws IdNotFoundException {
    SensorModel s = sensorModels.get(id);
    if (s == null) {
      throw new IdNotFoundException(id + " is not a defined sensor model id.");
    }
    sensorModels.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(s);
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteSensorProcess(java.lang.String)
   */
  @Override
  public void deleteSensorProcess(String id) throws IdNotFoundException {
    SensorProcess sp = sensorProcesses.get(id);
    if (sp == null) {
      throw new IdNotFoundException(id + " is not a defined sensor model id.");
    }
    sensorModels.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(sp);
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteUser(java.lang.String)
   */
  @Override
  public void deleteUser(String id) throws IdNotFoundException {
    UserInfo info = users.get(id);
    if (info == null) {
      throw new IdNotFoundException(id + " is not a defined user info id.");
    }
    users.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(info);
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteUserGroup(java.lang.String)
   */
  @Override
  public void deleteUserGroup(String id) throws IdNotFoundException {
    UserGroup group = groups.get(id);
    if (group == null) {
      throw new IdNotFoundException(id + " is not a defined user group id.");
    }
    groups.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(group);
    entityManager.getTransaction().commit();
    
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#deleteWattDepository(java.lang.String)
   */
  @Override
  public void deleteWattDepository(String id) throws IdNotFoundException {
    Depository wd = depositories.get(id);
    if (wd == null) {
      throw new IdNotFoundException(id + " is not a defined WattDepository id.");
    }
    depositories.remove(id);
    EntityManager entityManager = JPAManager.getInstance().getEntityManager();
    entityManager.getTransaction().begin();
    List<JPAWattDepositoryDescription> wdds = entityManager
        .createQuery("from JPAWattDepositoryDescription where idStr = :id",
            JPAWattDepositoryDescription.class).setParameter("idStr", id).getResultList();
    for (JPAWattDepositoryDescription wdd : wdds) {
      entityManager.remove(wdd);
    }
    entityManager.getTransaction().commit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getLocation(java.lang.String)
   */
  @Override
  public Location getLocation(String id) {
    return locations.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getLocations()
   */
  @Override
  public List<Location> getLocations() {
    ArrayList<Location> ret = new ArrayList<Location>();
    for (JPALocation l : locations.values()) {
      ret.add(l);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensor(java.lang.String)
   */
  @Override
  public Sensor getSensor(String id) {
    return sensors.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensorGroup(java.lang.String)
   */
  @Override
  public SensorGroup getSensorGroup(String id) {
    return sensorGroups.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensorGroups()
   */
  @Override
  public List<SensorGroup> getSensorGroups() {
    ArrayList<SensorGroup> ret = new ArrayList<SensorGroup>();
    for (JPASensorGroup s : sensorGroups.values()) {
      ret.add(s);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensorModel(java.lang.String)
   */
  @Override
  public SensorModel getSensorModel(String id) {
    return sensorModels.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensorModels()
   */
  @Override
  public List<SensorModel> getSensorModels() {
    ArrayList<SensorModel> ret = new ArrayList<SensorModel>();
    for (JPASensorModel s : sensorModels.values()) {
      ret.add(s);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensorProcess(java.lang.String)
   */
  @Override
  public SensorProcess getSensorProcess(String id) {
    return sensorProcesses.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensorProcesses()
   */
  @Override
  public List<SensorProcess> getSensorProcesses() {
    ArrayList<SensorProcess> ret = new ArrayList<SensorProcess>();
    for (JPASensorProcess s : sensorProcesses.values()) {
      ret.add(s);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getSensors()
   */
  @Override
  public List<Sensor> getSensors() {
    ArrayList<Sensor> ret = new ArrayList<Sensor>();
    for (JPASensor s : sensors.values()) {
      ret.add(s);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getUser(java.lang.String)
   */
  @Override
  public UserInfo getUser(String id) {
    return users.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getUsers()
   */
  @Override
  public List<UserInfo> getUsers() {
    ArrayList<UserInfo> ret = new ArrayList<UserInfo>();
    for (JPAUserInfo i : users.values()) {
      System.out.println(i);
      ret.add(i);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getWattDeposiory(java.lang.String)
   */
  @Override
  public Depository getWattDeposiory(String id) {
    return depositories.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot.server.WattDepot#getWattDepositories()
   */
  @Override
  public List<Depository> getWattDepositories() {
    ArrayList<Depository> ret = new ArrayList<Depository>();
    for (JPAWattDepository d : depositories.values()) {
      ret.add(d);
    }
    return ret;
  }

}
