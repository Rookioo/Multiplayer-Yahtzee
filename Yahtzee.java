import java.util.ArrayList;
import java.util.Scanner;

// Class representing the Yahtzee Game
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
                int roll = (int) (Math.random() * 6 + 1); // Simulate dice roll
                player.setRollValue(roll);
                System.out.println(player.getName() + " rolled a " + roll);
            } else {
                System.out.println("Invalid input. Type 'roll' to roll.");
                // I will handle re-rolling here
            }
        }
        // Here I will add sorting players by roll value
        scanner.close();
    }

    // Method to start the game
    public void startGame() {
        System.out.println("Starting the game with " + numPlayers + (numPlayers > 1 ? " players" : " player"));
        rollForOrder(); // Roll dice for order
        // Further game logic will be added here for next final product
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nHi Welcome To Yahtzee!\n");
        System.out.println("Please type number of players (Accepted inputs: 1-6)");

        int numPlayers;
        while (true) {
            numPlayers = scanner.nextInt();
            if (numPlayers >= 1 && numPlayers <= 6) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }

        // Create and start the game
        Yahtzee game = new Yahtzee(numPlayers);
        game.startGame();

        scanner.close();
    }
}

// Class representing each player in the game
class Player {
    private String name;
    private int rollValue;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRollValue(int rollValue) {
        this.rollValue = rollValue;
    }

    public int getRollValue() {
        return rollValue;
    }
}
