import java.util.ArrayList;
import java.util.Scanner;

public class Yahtzee {
    private int numPlayers;
    private ArrayList<Player> players; // List to hold players
    private int rounds = 13;

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
    private void rollForOrder() {
        System.out.println("Please roll for playing order (Type 'roll'):");
        Scanner scanner = new Scanner(System.in);
        for (Player player : players) {
            System.out.println(player.getName() + " roll:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("roll")) {
                int roll = Dice.roll();
                player.setRollValue(roll);
                System.out.println(player.getName() + " rolled a " + roll);
            } else {
                System.out.println("Invalid input. Type 'roll' to roll.");
            }
        }
        sortPlayersByRollValue();
        System.out.println("Players sorted by roll:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getRollValue());
        }
    }

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

    // Method to start the game
    public void startGame() {
        System.out.println("Starting the game with " + numPlayers + (numPlayers > 1 ? " players" : " player"));
        rollForOrder(); // Roll dice for order
        // Further game logic will be added here for next final product
    }

    
}
