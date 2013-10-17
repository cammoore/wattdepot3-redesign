/**
 * UserClient.java created on Oct 16, 2013 by Cam Moore.
 */
package org.wattdepot.foo;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/**
 * UserClient foo.
 *
 * @author Cam Moore
 *
 */
public class UserClient {
  /**
   * @param args command line arguments.
   * @throws Exception if there is a problem.
   */
  public static void main(String[] args) throws Exception {
    ClientResource mailClient = new ClientResource(
            "http://localhost:8111/accounts/chunkylover53/mails/123");

    Representation mailRepresentation = mailClient
            .get(MediaType.APPLICATION_XML);
    System.out.println(mailRepresentation.toString());
    mailClient.put(mailRepresentation);

    mailRepresentation = mailClient.get(MediaType.APPLICATION_JSON);
    System.out.println(mailRepresentation.toString());
    mailClient.put(mailRepresentation);

  }
}
