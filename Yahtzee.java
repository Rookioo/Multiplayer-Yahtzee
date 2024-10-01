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

    // Method to handle the rolling of dice for determining player order by number (Not traditional around the circle)
    private void rollForOrder() {
        System.out.println("Please roll for playing order (Type 'r'):");
        Scanner scanner = new Scanner(System.in);
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

    // Simple sorting algorithm to sort the roll values of starting players
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
        System.out.println("\nStarting the game with " + numPlayers + (numPlayers > 1 ? " players" : " player"));
        rollForOrder(); // Roll dice for order
        playGame(); // Playing the game
    }

    // Method for playing each round
    private void playGame() {
        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nRound " + round);
            for (Player player : players) {
                playTurn(player);
                displayScores();
            }
        }
        // Determines the winner after the full round cycles are done
        determineWinner();
    }

    // Method for players dice management
    private void playTurn(Player player) {
        System.out.println(player.getName() + "'s turn:");
        int[] dice = Dice.rollMultiple(5);
        boolean[] keptDice = new boolean[5];
        int reRolls = 2;

        for (int roll = 0; roll < 3; roll++) {
            displayDice(dice, keptDice);
            if (roll < reRolls) {
                System.out.println("Select dice to keep (e.g., 1 3), or type 'r' to re-roll all:");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equals("r")) {
                    break;
                }
                String[] selections = input.split(" ");
                for (String selection : selections) {
                    int index = Integer.parseInt(selection) - 1;
                    keptDice[index] = true;
                }
                dice = reRollDice(dice, keptDice);
            }
            
        }

        int score = scoreDice(dice);
        player.addScore(score);
        System.out.println(player.getName() + " scored " + score + " points.");
    }
    
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

    private int[] reRollDice(int[] dice, boolean[] keptDice) {
        for (int i = 0; i < dice.length; i++) {
            if (!keptDice[i]) {
                dice[i] = Dice.roll();
            }
        }
        return dice;
    }

    private int scoreDice(int[] dice) {
        int total = 0;
        for (int die : dice) {
            total += die;
        }
        return total;
    }

    private void displayScores() {
        System.out.println("Current Scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getTotalScore() + " points");
        }
    }

    private void determineWinner() {
        Player winner = players.get(0);
        for (Player player : players) {
            if (player.getTotalScore() > winner.getTotalScore()) {
                winner = player;
            }
        }
        System.out.println("The winner is " + winner.getName() + " with " + winner.getTotalScore() + " points!");
    }

    
}
