/**
 * Yahtzee.java
 * 
 * Purpose: This class handles the dice and their simple feature of rolling for a random number 1-6.
 * Author: Dustin Anderson
 * Date: 2024-09-30
 */

public class Dice {
    /**
     * Method Description: Method to simulate a dice roll
     * @return int returns the integer 1-6 at random
     */
    public static int roll() {
        return (int) (Math.random() * 6 + 1); // Simulate dice roll
    }

    /**
     * Method Description: Rolls multiple dice at once for convinience sake
     * @paramater numDice int for the number of dice to be rolled
     * @return rolls array of integers of the different roll values
     */
    public static int[] rollMultiple(int numDice) {
        int[] rolls = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            rolls[i] = roll();
        }
        return rolls;
    }
}
