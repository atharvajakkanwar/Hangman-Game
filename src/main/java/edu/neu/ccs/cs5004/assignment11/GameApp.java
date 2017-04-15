package edu.neu.ccs.cs5004.assignment11;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * A controller class for the hangman game.
 * <p>
 * Provide the word library for the game as a .txt file with one word in each line.
 * Pass this path of file as the command line argument for the program.
 * </p>
 * Created by Atharva Jakkanwar on 08-Apr-17.
 */
public class GameApp {

  private Game game;

  private GameApp(List<String> words) {
    this.game = new HangMan(words);

    //Creating GUI
    JFrame window = new JFrame("HangMan");


    //Setting layout
    BoxLayout layout = new BoxLayout(window, BoxLayout.PAGE_AXIS);
    //layout.setAlignment(FlowLayout.CENTER);
    window.setLayout(layout);
    window.setContentPane(Box.createVerticalBox());

    //Close the process
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    //Add all components to pane
    this.addToFrame(window);

    //Organizing GUI
    window.pack();

    //Setting keyListener
    window.setFocusable(true);
    window.addKeyListener(new KeyAdapter() {
      @Override

      public void keyTyped(KeyEvent keyEv) {
        Character chr = keyEv.getKeyChar();
        game.checkKey(chr);
      }
    });
    window.setFocusTraversalKeysEnabled(false);

    //Set visibility
    window.setVisible(true);
  }

  /**
   * Adds all the elements to the given JFrame.
   *
   * @param window the given JFrame
   */
  private void addToFrame(JFrame window) {
    //New Button
    JButton newButton = new JButton("NEW");
    newButton.setFocusable(false);

    newButton.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent event) {
        JButton src = (JButton) event.getSource();
        if (src.getActionCommand().equals("NEW")) {
          game.reset();
        }
      }
    });

    newButton.setActionCommand("NEW");
    newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    //Graphics
    Graphic mainScreen = new Graphic(this.game);
    mainScreen.setBackground(Color.white);

    //Text
    GuessedWord guessedWord = new GuessedWord(this.game);
    GuessCounter counter = new GuessCounter(this.game);
    Alphabets alpha = new Alphabets(this.game);

    //Add to main frame
    window.add(mainScreen);
    window.add(guessedWord);
    window.add(counter);
    window.add(alpha);
    window.add(newButton);
  }


  /**
   * Main method to run the hangman game.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    CmdHandler handler = new CmdHandler(args);
    InputReader reader = new InputReader(handler);
    final ArrayList<String> words = reader.getLines();

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new GameApp(words);
      }
    });
  }


}
