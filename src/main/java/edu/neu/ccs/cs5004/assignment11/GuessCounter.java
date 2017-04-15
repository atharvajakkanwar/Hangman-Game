package edu.neu.ccs.cs5004.assignment11;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Deals with the display of the number of guesses in the hangman game and
 * also displays if the game is won or lost.
 * Created by Atharva Jakkanwar on 08-Apr-17.
 */
public class GuessCounter extends JPanel implements Observer {

  private Game game;
  private JLabel guessDisplay;
  private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);

  /**
   * Constructor for an GuessCounter class.
   *
   * @param game the game which is observed by this counter class
   */
  public GuessCounter(Game game) {
    this.game = game;
    this.guessDisplay = new JLabel(this.game.getNumberOfGuessesLeft() + " Guesses left.");
    this.guessDisplay.setFont(FONT);
    add(guessDisplay);
    this.game.addObserver(this);

  }

  /**
   * Updates the state of the GuessCounter class based on the changes in the
   * {@code arg} argument.
   *
   * @param obs the observable class that has this observer registered
   * @param arg the argument whose change triggers the update.
   */
  public void update(Observable obs, Object arg) {
    this.game = (Game) arg;
    Integer left = this.game.getNumberOfGuessesLeft();
    if (left == 0) {
      this.guessDisplay.setText("You lost! It was " + this.game.getSecretWord());
    } else {
      boolean wordMatched = this.game.getSecretWord().equals(this.game.getGuessedWord());
      if (wordMatched) {
        this.guessDisplay.setText("Whoa! You won with " + left + " guesses remaining.");
      } else {
        this.guessDisplay.setText(left.toString() + " Guesses left.");
      }
    }
  }
}
