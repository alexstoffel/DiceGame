// Alex Stoffel
// This is my front-end class, where I will display all the image from my other classes in window

import javax.swing.*;
import java.awt.*;

public class DiceGameView extends JFrame {
    // Instance variables
    private DiceGame game;
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;
    private Image background;
    private Image[] diceImages;

    // Creating constants
    private final int TEXT_START_POS = 25;
    private final int Y_TEXT_START = 50;


    // Constructor
    public DiceGameView(DiceGame game){
        this.game = game;

        // Initialize the images
        background = new ImageIcon("Resources/background.png").getImage();
        diceImages = new Image[6];
        for(int i = 0; i < 6; i++){
            diceImages[i] = new ImageIcon("Resources/" + (i + 1) + ".png").getImage();

        }

        // Set up the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Chicago Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Paint Method
    public void paint(Graphics g){
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        // Printing out all the numbers
        g.setFont(new Font("arial", Font.PLAIN, 25));
        g.setColor(Color.WHITE);

        // Instructions page
        if (game.state == 0){
            // Use the instructions method
            this.paintInstructions(g);
        }else if (game.state == 1){
            // Method for playing the game
            this.paintGame(g);
        }else if(game.state == 2){
            // This is the result of a player win
            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.drawString(game.getPlayer1().getName() + " Wins!", 5, 250);
        }else if(game.state == 3){
            // This is the result of a computer win
            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.drawString("Robot Wins!", 5, 250);
        }else if(game.state == 4){
            // This is the result of a tie
            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.drawString("Tie!", 5, 250);
        }
    }

    // Method for printing instructions
    public void paintInstructions(Graphics g){
        // Initialize variables for printing
        String instructions = DiceGame.getInstructions();
        g.setColor(Color.white);

        for (int i = 0; i < instructions.length() / 39 + 1; i++){
            if (i <= 11){
                g.drawString(instructions.substring(i * 39,  (i + 1) * 39), TEXT_START_POS, Y_TEXT_START + 30 * i);
            }else{
                g.drawString(instructions.substring(i * 39), TEXT_START_POS, Y_TEXT_START + 30 * i);
            }
        }
    }

    // Method for playing the game
    public void paintGame(Graphics g){
        // Target roll method
        g.drawString("Target Roll:" +  game.getTargetRoll(), 300, 250);

        // Each of their points
        g.drawString(game.getPlayer1().getName() + ": " + game.getPlayer1().getScore(), 400, Y_TEXT_START + 350);
        g.drawString("Robot: " + (game.getRobot().getScore()), 400, Y_TEXT_START);

        // Last round summary box
        g.setColor(Color.white);
        g.drawRect(20, 40, 185, 50);
        g.drawRect(20, 340, 185, 50);
        g.setFont(new Font("arial", Font.PLAIN, 15));
        g.drawString("Last Round Target Roll: " + (game.getTargetRoll() - 1), TEXT_START_POS, 55);
        g.drawString("Last Round Target Roll: " + (game.getTargetRoll() - 1), TEXT_START_POS, 355);
        g.drawString("Last Round Roll: " + game.getRobotMostRecentRoll(), TEXT_START_POS, 85);
        g.drawString("Last Round Roll: " + game.getPlayerMostRecentRoll(), TEXT_START_POS, 385);

        // Drawing the dice
        for (int i = 0; i < 2; i++){
            game.getRobotDie()[i].draw(g);
            game.getPlayerDie()[i].draw(g);
        }
    }

    // Returning the die
    public Image[] getDiceImages(){
        return diceImages;
    }
}
