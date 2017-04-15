package edu.neu.ccs.cs5004.assignment11;

/**
 * Handles the command line arguments given to the program
 * Created by Atharva Jakkanwar on 30-Mar-17.
 */
public interface CmdHandlerInterface {
  /**
   * Gets the path for the input file frmo the command line arguments.
   *
   * @return the path of the input file
   */
  String getInputPath();
}
