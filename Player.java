/**
 * Player.java
 * 
 * Purpose: This class handles the simple player class with basic values for the player object that the system needs.
 * Author: Dustin Anderson
 * Date: 2024-09-30
 */

public class Player {
    
    private String name;
    private int rollValue;
    private int totalScore;

    /**
     * Method Description: Creates a simple player object
     * @param name gives the player a name to be easily identified by the system and player
     */
    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
    }
    
    /**
     * Method Description: Getter method for name
     * @param name returns player name
     */
    public String getName() {
        return name;
    }

    /**
     * Method Description: Sets the players current rollValue
     * @param rollValue the integer role value to be set to the players
     */
    public void setRollValue(int rollValue) {
        this.rollValue = rollValue;
    }

    /**
     * Method Description: Getter method for rollValue
     * @param rollValue returns the rollValue
     */
    public int getRollValue() {
        return rollValue;
    }

    /**
     * Method Description: Sets the players current score
     * @param score the integer score value to be set to the players
     */
    public void addScore(int score) {
        this.totalScore += score;
    }

    /**
     * Method Description: Getter method for the total score
     * @param totalScore returns the totalScore
     */
    public int getTotalScore() {
        return totalScore;
    }
}
