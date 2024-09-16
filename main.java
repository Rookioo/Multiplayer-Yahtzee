import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        //Boolean running = true;
        int numPlayers;
        ArrayList<String> words = new ArrayList<String>();

        // Code to be used for timings
        //long then = System.currentTimeMillis();
        //long now = System.currentTimeMillis();
        //long elapsedTime = System.currentTimeMillis() - startTime;
        //long elapsedSeconds = elapsedTime / 1000;
        //long secondsDisplay = elapsedSeconds % 60;
        //long elapsedMinutes = elapsedSeconds / 60;
        System.out.println("\n\nHi Welcome To Yahtzee!\n");
        System.out.println("Please type number of players (Accepted inputs: 1-6)");
        numPlayers = scanner.nextInt();
        if (numPlayers > 1) {
            System.out.println("You are playing as " + numPlayers + " Players\n");
        } else {
            System.out.println("You are playing as " + numPlayers + " Player\n");
        }


        System.out.println("Please roll for playing order (Accepted inputs: roll)");

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player one roll");
        }


        scanner.close();

    }
}
