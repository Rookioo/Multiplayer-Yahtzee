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
