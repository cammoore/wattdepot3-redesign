package org.wattdepot.core.util.tstamp;

import java.io.Serializable;
import java.util.Comparator;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Implements a comparator for XMLGregorianCalendar instances consistent with
 * the Tstamp operations.
 * 
 * @author Philip Johnson
 */
public class TstampComparator implements Serializable, Comparator<XMLGregorianCalendar> {

  /** For serialization. */
  private static final long serialVersionUID = 1L;

  /**
   * Compares the two instances.
   * 
   * @param tstamp1
   *          The first tstamp.
   * @param tstamp2
   *          The second tstamp.
   * @return -1, 0, or +1 depending.
   */
  @Override
  public int compare(XMLGregorianCalendar tstamp1, XMLGregorianCalendar tstamp2) {
    if (Tstamp.greaterThan(tstamp1, tstamp2)) {
      return 1;
    }
    else if (Tstamp.lessThan(tstamp1, tstamp2)) {
      return -1;
    }
    else {
      return 0;
    }
  }

  /**
   * Static implementation of Comparator interface.
   * 
   * @param tstamp1
   *          The first time stamp.
   * @param tstamp2
   *          The second time stamp.
   * @return -1 , 0, or +1 depdending on the comparison.
   */
  public static int compareTo(XMLGregorianCalendar tstamp1, XMLGregorianCalendar tstamp2) {
    if (Tstamp.greaterThan(tstamp1, tstamp2)) {
      return 1;
    }
    else if (Tstamp.lessThan(tstamp1, tstamp2)) {
      return -1;
    }
    else {
      return 0;
    }
  }
}
