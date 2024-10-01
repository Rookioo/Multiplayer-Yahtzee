/**
 * Main.java
 * 
 * Purpose: The Main class containing a small portion of starting up the Yahtzee game and getting the number of players.
 * Author: Dustin Anderson
 * Date: 2024-09-30
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nHi Welcome To Yahtzee!\n");
        System.out.println("Please type number of players (Accepted inputs: 1-6)");
        
        boolean validInput = false; // tracking if the input is correct
        int numPlayers;
        
        while (!validInput) {
            numPlayers = scanner.nextInt();
            if (numPlayers >= 1 && numPlayers <= 6) {
                
                // Create and start the game
                Yahtzee game = new Yahtzee(numPlayers);
                game.startGame();

                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }

        scanner.close();
    }
}
