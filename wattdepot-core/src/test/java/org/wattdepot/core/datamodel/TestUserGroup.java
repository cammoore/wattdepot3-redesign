/**
 * TestUserGroup.java created on Oct 15, 2013 by Cam Moore.
 */
package org.wattdepot.core.datamodel;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * TestUserGroup - Tests the UserGroup class.
 *
 * @author Cam Moore
 *
 */
public class TestUserGroup {

  /**
   * Test method for {@link org.wattdepot.core.datamodel.UserGroup#contains(java.lang.Object)}.
   */
  @Test
  public void testContains() {
    UserGroup admin = UserGroup.ADMIN_GROUP;
    assertTrue(admin.contains(User.ADMIN));
  }

  /**
   * Test method for {@link org.wattdepot.core.datamodel.UserGroup#add(org.wattdepot.core.datamodel.User)}.
   */
  @Test
  public void testAdd() {
//    fail("Not yet implemented");
  }

  /**
   * Test method for {@link org.wattdepot.core.datamodel.UserGroup#remove(java.lang.Object)}.
   */
  @Test
  public void testRemove() {
//    fail("Not yet implemented");
  }

  /**
   * Test method for {@link org.wattdepot.core.datamodel.UserGroup#toShortJSON()}.
   */
  @Test
  public void testToShortJSON() {
//    fail("Not yet implemented");
  }


}
