/**
 * Yahtzee.java
 * 
 * Purpose: This class handles the in game loop and a majority of user interactions.
 * Author: Dustin Anderson
 * Date: 2024-09-30
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Yahtzee {
    private int numPlayers;
    private ArrayList<Player> players; // List to hold players
    private int rounds = 13;
    private boolean[][] scored; // To track which categories are scored
    private YahtzeeScoring scoring; // Instance of YahtzeeScoring
    Scanner scanner = new Scanner(System.in);

    /**
     * Method description: This method creates the public Yahtzee gameobject.
     * @param numPlayers An integer representing the number of players.
     */
    public Yahtzee(int numPlayers) {
        this.numPlayers = numPlayers;
        this.players = new ArrayList<>();
        this.scored = new boolean[numPlayers][13]; // 13 scoring categories
        this.scoring = new YahtzeeScoring(); // Initilize the scoring instance
        initializePlayers(); // Initialize player objects
    }

    /**
     * Method description: This method initilizes the players based on the previous numPlayers value.
     */
    private void initializePlayers() {
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
    }

    /**
     * Method description: This method allows the players to roll for their playing order and sorts them.
     */
    private void rollForOrder() {
        System.out.println("Please roll for playing order (Type 'r'):");
        for (Player player : players) {
            boolean validInput = false; // tracking if the input is correct

           while (!validInput) {
                System.out.println(player.getName() + " roll:");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("r")) {
                    int roll = Dice.roll();
                    player.setRollValue(roll);
                    System.out.println(player.getName() + " rolled a " + roll + "\n\n");
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Type 'r' to roll.");
                }
            }
        }
        sortPlayersByRollValue();
        System.out.println("Players sorted by roll:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getRollValue());
        }
    }

    /**
     * Method description: Simple sorting algorithm for sorting the players by their roll value and sets them in the array in order.
     */
    private void sortPlayersByRollValue() {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - 1 - i; j++) {
                if (players.get(j).getRollValue() < players.get(j + 1).getRollValue()) {
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Method description: This method starts the game
     */
    public void startGame() {
        System.out.println("\nStarting the game with " + numPlayers + (numPlayers > 1 ? " players" : " player"));
        rollForOrder(); // Roll dice for order
        playGame(); // Playing the game
    }

    /**
     * Method description: This method loops the turns for each player and when it is done determines the winner of the game
     */
    private void playGame() {
        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nRound " + round);
            for (int i = 0; i < players.size(); i++) {
                playTurn(players.get(i), i); // Pass player index for scoring
            }
        }
        // Determines the winner after the full round cycles are done
        determineWinner();
    }


    /**
     * Method description: This method handles all the logic behind rolling, rerolling, and locking dice so the player can play traditional Yahtzee.
     * @param player The Player object that is currently playing their turn.
     * @param playerIndex The index of the player within the current list of Player objects.
     */
    private void playTurn(Player player, int playerIndex) {
        System.out.println(player.getName() + "'s turn:");
        int[] dice = Dice.rollMultiple(5);
        boolean[] keptDice = new boolean[5];
        int reRolls = 2; // Maximum re-rolls
        int remainingReRolls = reRolls; // Track remaining re-rolls
    
        while (remainingReRolls >= 0) {
            displayDice(dice, keptDice);
    
            if (remainingReRolls > 0) {
                System.out.println("You have " + remainingReRolls + " re-roll(s) remaining.");
                System.out.println("Select dice to keep (e.g., 1 3), or type 'r' to re-roll all:");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
    
                if (input.equals("r")) {
                    // Re-roll all dice
                    dice = Dice.rollMultiple(5);
                    keptDice = new boolean[5]; // Reset kept dice
                } else {
                    // Update kept dice based on user input
                    String[] selections = input.split(" ");
                    for (String selection : selections) {
                        int index = Integer.parseInt(selection) - 1; // Convert to 0-based index
                        keptDice[index] = true;
                    }
                    // Re-roll the non-kept dice
                    dice = reRollDice(dice, keptDice);
                }
            } else {
                System.out.println("No more re-rolls remaining.");
            }
    
            remainingReRolls--; // Decrease the count of remaining re-rolls
        }
    
        // Scoring category selection
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Choose scoring category (1-13):");
            for (int i = 0; i < 13; i++) {
                if (!scored[playerIndex][i]) {
                    System.out.println((i + 1) + ". " + getCategoryName(i));
                }
            }
            
            Scanner scanner = new Scanner(System.in);
            int categoryChoice = scanner.nextInt() - 1; // Adjusting for 0-based index
            if (categoryChoice >= 0 && categoryChoice < 13 && !scored[playerIndex][categoryChoice]) {
                int score = scoring.scoreCategory(categoryChoice, dice);
                if (score != -1) {
                    player.addScore(score);
                    scored[playerIndex][categoryChoice] = true; // Mark category as scored
                    System.out.println(player.getName() + " scored " + score + " points in " + getCategoryName(categoryChoice));
                    validChoice = true; // Exit the loop
                }
            } else {
                System.out.println("Invalid choice or category already scored. Please select again.");
            }
        }
    }
    
    /**
     * Method description: This method displays the dice to the player with spaces for easy readability and shows which dice you decided to keep so it could help the player
     * more visually.
     * @param dice An array of the rolled dice.
     * @param keptDice An array of the dice that were kept since last turn.
     */
    private void displayDice(int[] dice, boolean[] keptDice) {
        System.out.print("You rolled: ");
        for (int i = 0; i < dice.length; i++) {
            if (keptDice[i]) {
                System.out.print("[" + dice[i] + "] ");
            } else {
                System.out.print(dice[i] + " ");
            }
        }
        System.out.println();
    }

    /**
     * Method description: This method rerolls the dice if the dice aren't within the keptDice array
     * @param dice An array of the rolled dice.
     * @param keptDice An array of the dice that were kept since last turn.
     * @return dice returns the dice array after it has been rerolled
     */
    private int[] reRollDice(int[] dice, boolean[] keptDice) {
        for (int i = 0; i < dice.length; i++) {
            if (!keptDice[i]) {
                dice[i] = Dice.roll();
            }
        }
        return dice;
    }

    /**
     * Method description: This method shows the winner by totalling up the scores and displaying the results
     * more visually.
     */
    private void determineWinner() {
        Player winner = players.get(0);
        for (Player player : players) {
            if (player.getTotalScore() > winner.getTotalScore()) {
                winner = player;
            }
        }
        System.out.println("The winner is " + winner.getName() + " with " + winner.getTotalScore() + " points!");
    }

    /**
     * Method description: This method gets the category name based on the input index for the predefined array that interacts within YahtzeeScoring.java
     * @param index An integer with the index
     * @return categories The string of the category at the given index. Ex: index = 5 => "Fives"
     */
    private String getCategoryName(int index) {
        String[] categories = {
            "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "Three of a Kind", "Four of a Kind", "Full House",
            "Small Straight", "Large Straight", "Yahtzee", "Chance"
        };
        return categories[index];
    }
}
