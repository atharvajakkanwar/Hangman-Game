package edu.neu.ccs.cs5004.assignment11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Deals with the display of the graphics in the Hangman game.
 * <p>
 * The images should be named as {@code IMAGEPREFIX} + i + {@code IMAGEFORMAT}
 * Where i is the number of guesses left, and max i should be the maximum number of guesses.
 * These should be stored in a {@code image} folder in the path {@code imagePath}.
 * </p>
 * Created by Atharva Jakkanwar on 08-Apr-17.
 */
public class Graphic extends JPanel implements Observer {

  private static final int MAXTRIES = 6;
  private static final String IMAGEPREFIX = "hang";
  private static final String IMAGEFORMAT = ".png";
  private String imagePath = System.getProperty("user.dir")
      + System.getProperty("file.separator")
      + "images" + System.getProperty("file.separator");

  private Image image;
  private Game game;


  /**
   * Constructor for an Graphic class.
   *
   * @param game the game which is observed by this alphabet class
   */
  public Graphic(Game game) {
    this.image = new ImageIcon(imagePath + IMAGEPREFIX + MAXTRIES + IMAGEFORMAT).getImage();
    Dimension dim = new Dimension(image.getWidth(this), image.getHeight(this));
    setPreferredSize(dim);
    this.game = game;
    this.game.addObserver(this);
    this.setAlignmentX(CENTER_ALIGNMENT);
    setBackground(Color.white);
  }

  /**
   * Updates the state of the grpahic class based on the changes in the
   * {@code arg} argument.
   *
   * @param obs the observable class that has this observer registered
   * @param arg the argument whose change triggers the update.
   */
  public void update(Observable obs, Object arg) {
    this.game = (Game) arg;
    if (!this.game.getSecretWord().equals(this.game.getGuessedWord())) {
      int left = this.game.getNumberOfGuessesLeft();
      this.image = new ImageIcon(imagePath + IMAGEPREFIX + left + IMAGEFORMAT).getImage();
      repaint();
    }
  }

  @Override
  public void paintComponent(Graphics graphic) {
    super.paintComponent(graphic);
    graphic.drawImage(image, 0, 10, this.getWidth(), this.getHeight(), this);
  }
}

