package edu.neu.ccs.cs5004.assignment11;

import java.util.HashSet;
import java.util.Observer;

/**
 * Created by Atharva Jakkanwar on 10-Apr-17.
 */
public interface Game {
  void addObserver(Observer obs);

  void notifyObservers();

  void checkKey(char chr);

  boolean gameNotEnded();

  void reset();

  String getSecretWord();

  String getGuessedWord();

  Integer getNumberOfGuessesLeft();

  HashSet<Character> getUsedAlphabets();
}
