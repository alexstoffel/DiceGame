// Alex Stoffel
// This is my front-end class, where I will display all the image from my other classes in window

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiceGameView extends JFrame {
    private DiceGame game;
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;
    private Image background;
    private Image[] diceImages;

    // Constructor
    public DiceGameView(DiceGame game){
        this.game = game;

        // Initialize the images
        background = new ImageIcon("Resources/background.png").getImage();
        diceImages = new Image[6];
        diceImages[0] = new ImageIcon("Resources/1.png").getImage();
        diceImages[1] = new ImageIcon("Resources/2.png").getImage();
        diceImages[2] = new ImageIcon("Resources/3.png").getImage();
        diceImages[3] = new ImageIcon("Resources/4.png").getImage();
        diceImages[4] = new ImageIcon("Resources/5.png").getImage();
        diceImages[5] = new ImageIcon("Resources/6.png").getImage();



        // Setup the window and the buffer strategy.
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
        g.setColor(Color.RED);

        // Instructions page
        if (game.state == 0){
            g.drawString(DiceGame.getInstructions(), 50, 50);
        }else if (game.state == 1){
            // Target Roll
            g.drawString("Target Roll:" +  Integer.toString(game.getTargetRoll()), 300, 250);

            // Each of their points
            g.drawString(game.getPlayer1().getName() + ": " + Integer.toString(game.getPlayer1().getScore()), 400, 450);
            g.drawString("Robot: " + Integer.toString(game.getRobot().getScore()), 350, 50);

            // Drawing the dice
            for (int i = 0; i < 2; i++){
                game.getRobotDie()[i].draw(g);
                game.getPlayerDie()[i].draw(g);
            }
        }






    }

    // Returning the die
    public Image[] getDiceImages(){
        return diceImages;
    }
}
