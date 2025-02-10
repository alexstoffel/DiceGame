import java.util.Scanner;

public class DiceGame {
    private Scanner input;
    private int diceSum;
    private int targetRoll;
    private DiceGameView window;
    public int state;

    // Initilaize players
    private Player player1;
    private Player robot;

    // Dice
    private Die[] playerDie;
    private Die[] robotDie;

    // Constructor
    public DiceGame(){
        // Create my front end
        window = new DiceGameView(this);

        // Initialize all variable
        diceSum = 0;
        player1 = new Player("Alex");
        robot = new Player("Robot");
        playerDie = new Die[2];
        robotDie = new Die[2];
        state = 0;

        for (int i = 0; i < 2; i++){
            playerDie[i] = new Die(window, player1, i + 1);
            robotDie[i] = new Die(window, robot, i + 1);
        }
        input = new Scanner(System.in);
    }

    public static String getInstructions(){
        return ("The goal of Chicago is to roll every possible number" +
                " combination that can be made with two dice. There are 11" +
                " possible combinations, and you'll have 11 chances to roll." +
                " Each round has a target combination, which starts at 2 and" +
                " goes up to 12 in the same sequence. For example, if you are" +
                " in Round 8, the target score is 9. If a player rolls any" +
                " combination that adds up to 9, they will score that number." +
                " If they do not, they'll earn zero points for that round. type");
    }

    // This will check if the die roll was correct
    public boolean checkDie(){
        // Return true if sum equals round
        if (diceSum == targetRoll){
            return true;
        }else{
            return false;
        }
    }
    public void printTarget(){
        // print out the target Roll for each round
        System.out.println("The target roll is " + targetRoll + ". Type"
                + " 'roll' to roll the die!");
    }
    public void printResult(){
        // print out how the round went for the player
        System.out.println("You rolled a " + diceSum + ", Congrats!" +
                " You earn " + targetRoll + " points and your new total is " +
                player1.getScore());
    }
    public void printBad(){
        // Print out the bad result.
        System.out.println("You rolled a " + diceSum + " so you" +
                " earn no points! Your total is " + player1.getScore());
    }
    public void endGame(int playerScore, int robotScore){
        // Ending the game
        if (playerScore > robotScore){
            System.out.println("\nCongrats Alex Wins!");
        }else if (robotScore > playerScore){
            System.out.println("\n Robot Wins!");
        }else{
            System.out.println("\nTie!");
        }

    }
    public void play(){
        window.repaint();
        // For loop for each round
        for (int i = 2; i < 13; i++){
            targetRoll = i;
            window.repaint();
            // Player Turn
            this.printTarget();
            // Check if the dice is equal to the round
            if(input.nextLine().equals("roll")){
                state = 1;
                diceSum = playerDie[0].roll() + playerDie[1].roll();
                if (this.checkDie() == true){
                    // update player score
                    player1.updateScore(targetRoll);
                    this.printResult();
                }else{
                    this.printBad();
                }
            }
            // Play out the robots turn
            diceSum = robotDie[0].roll() + robotDie[1].roll();
            if (this.checkDie() == true){
                // update Robot score
                robot.updateScore(targetRoll);
            }
            // Print out Robot Score
            System.out.println("robot score: " + robot.getScore());
        }
        this.endGame(player1.getScore(), robot.getScore());
    }

    public static void main(String[] args){
        // Initialize game
        DiceGame main = new DiceGame();
        System.out.println(DiceGame.getInstructions());
        main.play();
        // This will reference the DiceGame class for each round
        // Now, check for the win
    }

    // Getters and setters
    public int getTargetRoll(){
        return targetRoll;
    }

    // Getting the players

    public Player getPlayer1() {
        return player1;
    }

    public Player getRobot() {
        return robot;
    }

    public Die[] getPlayerDie() {
        return playerDie;
    }

    public Die[] getRobotDie() {
        return robotDie;
    }
}