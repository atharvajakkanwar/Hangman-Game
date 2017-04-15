package edu.neu.ccs.cs5004.assignment11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;


/**
 * Models a state of the Game HangMan.
 * Created by Atharva Jakkanwar on 08-Apr-17.
 */
public class HangMan extends Observable implements Game {

  private static final char QUESTION = '?';
  private static final int MAX_GUESSES = 6;

  private String secretWord;
  private String guessedWord;
  private Integer numberOfGuessesLeft;
  private List<String> possibleWords;
  private HashSet<Character> usedAlphabets;

  /**
   * Constructor for a new HangMan. Initialises all the state variables and assigns
   * a random word from the list of given {@code words} to the {@code secretWord}.
   */
  public HangMan(List<String> words) {
    this.numberOfGuessesLeft = MAX_GUESSES;
    this.possibleWords = words;
    this.secretWord = possibleWords.get(new Random().nextInt(possibleWords.size()));
    this.usedAlphabets = new HashSet<>();
    initializeGuessedWord();
  }


  /**
   * Initializes the {@code guessedWord} so far to "?" for each alphabet of the {@code secretWord}.
   */
  private void initializeGuessedWord() {
    int len = this.secretWord.length();
    char[] questionMarks = new char[len];
    Arrays.fill(questionMarks, QUESTION);
    this.guessedWord = new String(questionMarks);
  }


  /**
   * Sets the guessed word to "?" except for all the occurrences of
   * correctly guessed character {@code chr}.
   *
   * @param chr the character that is correctly guessed
   */
  private void setGuessedWord(char chr) {
    char[] word = this.guessedWord.toCharArray();
    changeAll(word, chr);
    this.guessedWord = new String(word);
  }


  /**
   * Changes all the values at indices in the given {@code array}
   * that matches the corresponding given {@code chr} in the secret word.
   */
  private void changeAll(char[] array, char chr) {
    for (int i = 0; i < secretWord.length(); i++) {
      if (secretWord.charAt(i) == chr) {
        array[i] = chr;
      }
    }
  }

  /**
   * Registers a new observer with the {@code HangMan}.
   *
   * @param obs a new observer object
   */
  @Override
  public synchronized void addObserver(Observer obs) {
    super.addObserver(obs);
  }


  @Override
  public void notifyObservers() {
    super.notifyObservers(this);
  }

  /**
   * Checks the {@code chr} character if it is contained in the secret word.
   * If the character is not present, reduce the number of guesses left by 1.
   *
   * @param chr the guessed character
   */
  @Override
  public void checkKey(char chr) {
    if (validCharacters(chr) && gameNotEnded()) {
      boolean existsInSecret = secretWord.indexOf(chr) >= 0;
      boolean notYetUsed = !this.usedAlphabets.contains(chr);
      if (existsInSecret && notYetUsed) {
        this.setGuessedWord(chr);
      } else {
        if (notYetUsed) {
          this.numberOfGuessesLeft = this.numberOfGuessesLeft - 1;
        }
      }

      this.usedAlphabets.add(chr);
      setChanged();
      notifyObservers(this);

    }
  }

  /**
   * Checks if the given character is a valid character according to the game rules.
   *
   * @param chr the input character
   * @return true if the character is valid, else false
   */
  private boolean validCharacters(char chr) {
    return chr >= 'a' && chr <= 'z';
  }

  /**
   * Checks if the game has ended of or not.
   *
   * @return return true if the game has not ended, false if it has ended.
   */
  @Override
  public boolean gameNotEnded() {
    boolean guessesStillLeft = this.numberOfGuessesLeft != 0;
    boolean notYetGuessed = !this.secretWord.equals(this.guessedWord);
    return guessesStillLeft && notYetGuessed;
  }


  /**
   * Resets the game to a new state with new secret word.
   */
  @Override
  public void reset() {
    this.usedAlphabets = new HashSet<>();
    this.numberOfGuessesLeft = MAX_GUESSES;
    this.secretWord = possibleWords.get(new Random().nextInt(possibleWords.size()));
    this.initializeGuessedWord();

    setChanged();
    notifyObservers(this);
  }


  @Override
  public String getSecretWord() {
    return secretWord;
  }

  @Override
  public String getGuessedWord() {
    return guessedWord;
  }

  @Override
  public Integer getNumberOfGuessesLeft() {
    return numberOfGuessesLeft;
  }

  @Override
  public HashSet<Character> getUsedAlphabets() {
    return usedAlphabets;
  }
}
