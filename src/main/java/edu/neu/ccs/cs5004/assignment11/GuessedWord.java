package edu.neu.ccs.cs5004.assignment11;

import java.awt.Font;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Deals with the display of the guessed word in the hangman game.
 * Represents the word guessed so far using combination of ? and alphabets.
 * Created by Atharva Jakkanwar on 08-Apr-17.
 */
public class GuessedWord extends JPanel implements Observer {

  private Game game;
  private JLabel guessedDisplay;
  private String guessed;
  private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);


  /**
   * Constructor for an GuessedWord class.
   *
   * @param game the game which is observed by this guessed word class
   */
  public GuessedWord(Game game) {
    this.game = game;
    this.guessed = this.game.getGuessedWord();
    this.guessedDisplay = new JLabel(guessed);
    this.guessedDisplay.setFont(FONT);
    add(guessedDisplay);
    this.game.addObserver(this);
  }


  /**
   * Updates the state of the GuessedWord class based on the changes in the
   * {@code arg} argument.
   *
   * @param obs the observable class that has this observer registered
   * @param arg the argument whose change triggers the update.
   */
  @Override
  public void update(Observable obs, Object arg) {
    this.game = (Game) arg;
    this.guessed = this.game.getGuessedWord();
    this.guessedDisplay.setText(guessed);
  }
}
