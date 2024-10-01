import java.util.Scanner;

public class Main {
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
