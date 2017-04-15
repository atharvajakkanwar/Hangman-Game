package edu.neu.ccs.cs5004.assignment11;

import java.util.ArrayList;

/**
 * Reads the input file.
 * Created by Atharva Jakkanwar on 30-Mar-17.
 */

public interface InputReaderInterface {

  /**
   * Getter for the stored lines form the input file.
   *
   * @return the list of all the lines form teh linput file
   */
  ArrayList<String> getLines();
}
