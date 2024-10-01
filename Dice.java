/**
 * Yahtzee.java
 * 
 * Purpose: This class handles the dice and their simple feature of rolling for a random number 1-6.
 * Author: Dustin Anderson
 * Date: 2024-09-30
 */

public class Dice {
    // Method to roll a dice and return the value
    public static int roll() {
        return (int) (Math.random() * 6 + 1); // Simulate dice roll
    }

    public static int[] rollMultiple(int numDice) {
        int[] rolls = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            rolls[i] = roll();
        }
        return rolls;
    }
}
