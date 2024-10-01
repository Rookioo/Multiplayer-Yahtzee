import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Yahtzee {
    private int numPlayers;
    private ArrayList<Player> players; // List to hold players

    // Constructor to initialize the game with a specific number of players
    public Yahtzee(int numPlayers) {
        this.numPlayers = numPlayers;
        this.players = new ArrayList<>();
        initializePlayers(); // Initialize player objects
    }

    // Method to initialize players
    private void initializePlayers() {
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
    }

    // Method to handle the rolling of dice for determining player order
    public void rollForOrder() {
        System.out.println("Please roll for playing order (Type 'roll'):");
        Scanner scanner = new Scanner(System.in);
        for (Player player : players) {
            System.out.println(player.getName() + " roll:");
            String input = scanner.nextLine();  // User types 'roll'
            if (input.equalsIgnoreCase("roll")) {
                int roll = Dice.roll(); // Use Dice class to roll
                player.setRollValue(roll);
                System.out.println(player.getName() + " rolled a " + roll);
            } else {
                System.out.println("Invalid input. Type 'roll' to roll.");
            }
        }
        Collections.sort(players, Comparator.comparingInt(Player::getRollValue).reversed());
        System.out.println("Players sorted by roll:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getRollValue());
        }
        scanner.close();
    }

    // Method to start the game
    public void startGame() {
        System.out.println("Starting the game with " + numPlayers + (numPlayers > 1 ? " players" : " player"));
        rollForOrder(); // Roll dice for order
        // Further game logic will be added here for next final product
    }

    
}
