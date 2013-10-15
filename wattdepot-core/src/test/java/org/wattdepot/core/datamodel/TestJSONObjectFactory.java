/**
 * TestJSONObjectFactory.java created on Oct 15, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.json.JSONException;
import org.junit.Test;

/**
 * TestJSONObjectFactory - Test cases for the JSONObjectFactory class.
 * 
 * @author Cam Moore
 * 
 */
public class TestJSONObjectFactory {

  /**
   * Tests Building Locations.
   */
  @Test
  public void testBuildLocation() {
    Location loc1 = new Location("ilima-6th-floor", new Double(21.294642), new Double(-157.812727),
        new Double(30), "Hale Aloha Ilima residence hall 6th floor");
    String json = loc1.toJSON();
    System.out.println(json);
    try {
      Location loc2 = JSONObjectFactory.buildLocation(json);
      System.out.println(loc2);
      System.out.println(loc2.toJSON());
      assertNotNull(loc2);
      assertTrue(loc2.equals(loc1));
    }
    catch (JSONException e) {
      e.printStackTrace();
      fail("Should not throw JSONException for " + json);
    }
    
  }

}
