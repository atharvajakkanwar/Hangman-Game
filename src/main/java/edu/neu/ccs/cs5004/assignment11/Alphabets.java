package edu.neu.ccs.cs5004.assignment11;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Deals with the display of the alphabets in the hangman game.
 * Created by Atharva Jakkanwar on 08-Apr-17.
 */
public class Alphabets extends JPanel implements Observer {

  private Game game;
  private HashSet<Character> usedAlpha;
  private ArrayList<JLabel> alphaDisplay;
  private static final Integer MAX_ALPHA = 26;
  private static final Color INACTIVE = Color.GRAY;
  private static final Color ACTIVE = Color.blue;
  private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);

  /**
   * Constructor for an Alphabets class.
   *
   * @param game the game which is observed by this alphabet class
   */
  public Alphabets(Game game) {
    this.game = game;
    this.usedAlpha = this.game.getUsedAlphabets();
    this.alphaDisplay = new ArrayList<>();
    this.setLayout(new FlowLayout());

    //Store labels for all alphabets in alphaDisplay
    for (Integer i = 1; i <= MAX_ALPHA; i++) {
      char val = (char) (i + 'a' - 1);
      JLabel label = new JLabel(String.valueOf(val));
      label.setForeground(Color.gray);
      label.setFont(FONT);
      alphaDisplay.add(label);
    }

    for (int i = 0; i < MAX_ALPHA; i++) {
      JLabel val = alphaDisplay.get(i);
      this.add(val);
    }
    this.game.addObserver(this);
  }

  /**
   * Changes the colour of all the labels in {@code alphaDisplay} to the given
   * {@code color}.
   */
  private void removeColour(Color color) {

    for (int i = 0; i < MAX_ALPHA; i++) {
      JLabel val = alphaDisplay.get(i);
      val.setForeground(color);
    }



  }

  /**
   * Updates the state of the alphabet class based on the changes in the
   * {@code arg} argument.
   *
   * @param obs the observable class that has this observer registered
   * @param arg the argument whose change triggers the update.
   */
  public void update(Observable obs, Object arg) {
    this.game = (Game) arg;
    this.usedAlpha = this.game.getUsedAlphabets();
    if (usedAlpha.isEmpty()) {
      removeColour(INACTIVE);
    }

    for (int i = 0; i < MAX_ALPHA; i++) {
      String val = alphaDisplay.get(i).getText();
      if (this.usedAlpha.contains(val.charAt(0))) {
        alphaDisplay.get(i).setForeground(ACTIVE);
      }
    }

    repaint();
  }
}
