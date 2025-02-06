import java.awt.*;

public class Die
{
    //Instance Variables
    private int numSides;
    private DiceGameView window;
    private Player player;
    private int whichDie;
    private Image[] diceImages;
    private int mostRecentRoll;

    // Contructors
    public Die(int numSides) {
        if (numSides < 2){
            this.numSides = 6;
        }else{
            this.numSides = numSides;
        }

    }
    // Default return
    public Die(DiceGameView window, Player p, int whichDie) {
        this.window = window;
        numSides = 6;
        player = p;
        this.whichDie = whichDie;
        diceImages = window.getDiceImages();
        mostRecentRoll = 1;
    }


    /** Methods **/

    //Returns the number of sides on the Die.
    public int getSides() {
        return numSides;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        mostRecentRoll = (int)(Math.random() * numSides) + 1;
        return mostRecentRoll;
    }

    /**
     * Rolls the dice the numRolls times
     * and returns the max value of the rolls
     */
    public int getMaxRoll(int numRolls) {
        // TODO: complete getMaxRoll()
        int max = 0;
        int roll = 0;
        for(int i = 0; i < numRolls; i++){
            roll = this.roll();
            max = Math.max(max, roll);
        }
        return max;
    }

    /**
     * Returns a String in the following form:
     * "This is a n-sided die."
     */

    // Drawing itself
    public void draw(Graphics g){
        if (player.getName().equals("Robot")){
            g.drawImage(diceImages[mostRecentRoll - 1], 150 * whichDie, 100, 50, 50, window);
        }else{
            g.drawImage(diceImages[mostRecentRoll - 1], 150 * whichDie, 100, 50, 50, window);
        }
    }

}