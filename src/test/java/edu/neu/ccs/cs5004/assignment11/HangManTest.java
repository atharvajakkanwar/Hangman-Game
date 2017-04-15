package edu.neu.ccs.cs5004.assignment11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Created by Atharva Jakkanwar on 09-Apr-17.
 */
public class HangManTest {

  private ArrayList<String> words;
  private Game game;

  @Before
  public void setUp() throws Exception {
    words = new ArrayList<>();
    // words.add("modularity");
    words.add("encapsulation");
    //words.add("callbacks");
    game = new HangMan(words);
  }

  @Test
  public void allTests() throws Exception {

    //new word
    Assert.assertEquals(new Integer(6), game.getNumberOfGuessesLeft());

    //valid guess
    game.checkKey('a');
    Assert.assertEquals("???a????a????", game.getGuessedWord());

    //valid guess
    game.checkKey('l');
    Assert.assertEquals("???a???la????", game.getGuessedWord());

    //repeated guess
    game.checkKey('l');
    Assert.assertEquals("???a???la????", game.getGuessedWord());

    //invalid guess
    game.checkKey('A');
    Assert.assertEquals("???a???la????", game.getGuessedWord());

    //wrong guess 1
    game.checkKey('z');
    Assert.assertEquals("???a???la????", game.getGuessedWord());
    Assert.assertEquals(new Integer(5), game.getNumberOfGuessesLeft());

    //wrong guess 2
    game.checkKey('b');
    Assert.assertEquals("???a???la????", game.getGuessedWord());
    Assert.assertEquals(new Integer(4), game.getNumberOfGuessesLeft());

    //game reset
    game.reset();
    Assert.assertEquals(new Integer(6), game.getNumberOfGuessesLeft());

    //all correct guesses
    game.checkKey('e');
    game.checkKey('n');
    game.checkKey('c');
    game.checkKey('a');
    game.checkKey('p');
    game.checkKey('s');
    game.checkKey('u');
    game.checkKey('l');
    game.checkKey('t');
    game.checkKey('i');
    game.checkKey('o');
    Assert.assertEquals("encapsulation", game.getGuessedWord());

    //Used chars
    HashSet<Character> chars = new HashSet<>();
    chars.add('p');
    chars.add('a');
    chars.add('s');
    chars.add('c');
    chars.add('t');
    chars.add('e');
    chars.add('u');
    chars.add('i');
    chars.add('l');
    chars.add('o');
    chars.add('n');
    Assert.assertEquals(chars, game.getUsedAlphabets());

    //guess after game completion
    game.checkKey('j');
    Assert.assertEquals("encapsulation", game.getGuessedWord());

  }


}