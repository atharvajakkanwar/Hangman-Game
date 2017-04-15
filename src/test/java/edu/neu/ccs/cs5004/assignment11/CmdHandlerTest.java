package edu.neu.ccs.cs5004.assignment11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Atharva Jakkanwar on 10-Apr-17.
 */
public class CmdHandlerTest {

  private String[] args = new String[1];
  private String[] noArgs = new String[0];
  private CmdHandler handler;
  private CmdHandler handlerInvalidFile;

  @Before
  public void setUp() throws Exception {
    args[0] = "D:\\CS5004\\Assignments\\assignment-11-atharvajakkanwar\\words.txt";
    handler = new CmdHandler(args);
  }


  @Test
  public void getInputPath() throws Exception {
    Assert.assertEquals("D:\\CS5004\\Assignments\\"
        + "assignment-11-atharvajakkanwar\\words.txt", handler.getInputPath());
  }

  @Test(expected = RuntimeException.class)
  public void exceptionInvalidPath() throws Exception {
    args[0] = "yolo.txt";
    handlerInvalidFile = new CmdHandler(args);
  }

  @Test(expected = RuntimeException.class)
  public void exceptionNoPath() throws Exception {
    handlerInvalidFile = new CmdHandler(noArgs);
  }

}