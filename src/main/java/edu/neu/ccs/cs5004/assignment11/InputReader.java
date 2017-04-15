package edu.neu.ccs.cs5004.assignment11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads the input file.
 * Created by Atharva Jakkanwar on 30-Mar-17.
 */
public class InputReader implements InputReaderInterface {

  private ArrayList<String> lines;
  private String inputPath;


  /**
   * Constructor for the input reader.
   * @param handler the command line argument handler
   */
  public InputReader(CmdHandler handler) {
    this.lines = new ArrayList<>();
    this.inputPath = handler.getInputPath();
  }

  /**
   * Store the lines from the input file to a list of lines.
   * @return the list of all tge lines in the file.
   */
  private ArrayList<String> generateLines() {
    try (BufferedReader templateFile = new BufferedReader((new FileReader(inputPath)))) {
      String line;
      while ((line = templateFile.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return lines;
  }

  /**
   * Getter for the stored lines form the input file.
   * @return the list of all the lines form teh linput file
   */
  @Override
  public ArrayList<String> getLines() {
    this.generateLines();
    return lines;
  }
}
