package edu.neu.ccs.cs5004.assignment11;

import java.io.File;

/**
 * Handles the command line arguments given to the program
 * Created by Atharva Jakkanwar on 30-Mar-17.
 */
public class CmdHandler implements CmdHandlerInterface {

  private String inputPath;

  /**
   * Constructor for a command line arguments handler.
   *
   * @param args the command line arguments
   */
  public CmdHandler(String[] args)  {
    if (args.length != 1) {
      throw new RuntimeException("More than one or no paths provided");
    } else {
      File file = new File(args[0]);
      if (file.canRead()) {
        this.inputPath = args[0];
      } else {
        throw new RuntimeException("File not found!");
      }
    }
  }


  /**
   * Gets the path for the input file frmo the command line arguments.
   *
   * @return the path of the input file
   */
  @Override
  public String getInputPath() {
    return inputPath;
  }
}

