package edu.neu.ccs.cs5004.assignment11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Atharva Jakkanwar on 10-Apr-17.
 */
public class InputReaderTest {


  private String[] args = new String[1];
  private CmdHandler handler;
  private InputReader reader;
  private ArrayList<String> words;


  @Before
  public void setUp() throws Exception {
    args[0] = "words.txt";
    handler = new CmdHandler(args);
    reader = new InputReader(handler);
    words = new ArrayList<>();
    words.add("callback");
    words.add("cohesion");
    words.add("component");
    words.add("container");
    words.add("dependency");
    words.add("encapsulation");
    words.add("flexibility");
    words.add("listener");
    words.add("modularity");
    words.add("observer");
  }

  @Test
  public void getLines() throws Exception {
    Assert.assertEquals(words, reader.getLines());
  }

}