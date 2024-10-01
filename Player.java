public class Player {
    
    private String name;
    private int rollValue;
    private int totalScore;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
    }
    
    // Gets the player name
    public String getName() {
        return name;
    }

    // Sets the roll value
    public void setRollValue(int rollValue) {
        this.rollValue = rollValue;
    }

    // Gets the roll value
    public int getRollValue() {
        return rollValue;
    }

    public void addScore(int score) {
        this.totalScore += score;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
