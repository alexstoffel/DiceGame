public class Player
{
    // Instance Variables
    private int score;
    private String name;

    public Player(String name){
        this.name = name;
        score = 0;
    }

    public String toString()
    {
        return name + "'s Score is " + score;
    }
    public void updateScore(int change)
    {
        score = score + change;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
}