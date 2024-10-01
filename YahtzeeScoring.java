public class YahtzeeScoring {
    // Method to score based on the chosen category
    public int scoreCategory(int categoryIndex, int[] dice) {
        switch (categoryIndex) {
            case 0: return scoreOnes(dice);
            case 1: return scoreTwos(dice);
            case 2: return scoreThrees(dice);
            case 3: return scoreFours(dice);
            case 4: return scoreFives(dice);
            case 5: return scoreSixes(dice);
            case 6: return scoreThreeOfAKind(dice);
            case 7: return scoreFourOfAKind(dice);
            case 8: return scoreFullHouse(dice);
            case 9: return scoreSmallStraight(dice);
            case 10: return scoreLargeStraight(dice);
            case 11: return scoreYahtzee(dice);
            case 12: return scoreChance(dice);
            default: return -1; // Invalid category
        }
    }

    // Scoring Methods
    private int scoreOnes(int[] dice) {
        return countDice(dice, 1);
    }

    private int scoreTwos(int[] dice) {
        return countDice(dice, 2) * 2;
    }

    private int scoreThrees(int[] dice) {
        return countDice(dice, 3) * 3;
    }

    private int scoreFours(int[] dice) {
        return countDice(dice, 4) * 4;
    }

    private int scoreFives(int[] dice) {
        return countDice(dice, 5) * 5;
    }

    private int scoreSixes(int[] dice) {
        return countDice(dice, 6) * 6;
    }

    private int scoreThreeOfAKind(int[] dice) {
        int[] counts = countDiceOccurrences(dice);
        for (int count : counts) {
            if (count >= 3) {
                return sumArray(dice);
            }
        }
        return 0; // Not a valid score
    }

    private int scoreFourOfAKind(int[] dice) {
        int[] counts = countDiceOccurrences(dice);
        for (int count : counts) {
            if (count >= 4) {
                return sumArray(dice);
            }
        }
        return 0; // Not a valid score
    }

    private int scoreFullHouse(int[] dice) {
        int[] counts = countDiceOccurrences(dice);
        boolean hasThree = false;
        boolean hasTwo = false;
        for (int count : counts) {
            if (count == 3) hasThree = true;
            if (count == 2) hasTwo = true;
        }
        return (hasThree && hasTwo) ? 25 : 0; // Full House score
    }

    private int scoreSmallStraight(int[] dice) {
        int[] uniqueDice = removeDuplicates(dice);
        boolean smallStraightFound = false;
        if (containsAll(uniqueDice, new int[]{1, 2, 3, 4}) || 
            containsAll(uniqueDice, new int[]{2, 3, 4, 5}) || 
            containsAll(uniqueDice, new int[]{3, 4, 5, 6})) {
            smallStraightFound = true;
        }
        return smallStraightFound ? 30 : 0; // Small Straight score
    }

    private int scoreLargeStraight(int[] dice) {
        int[] uniqueDice = removeDuplicates(dice);
        boolean largeStraightFound = false;
        if (containsAll(uniqueDice, new int[]{1, 2, 3, 4, 5}) || 
            containsAll(uniqueDice, new int[]{2, 3, 4, 5, 6})) {
            largeStraightFound = true;
        }
        return largeStraightFound ? 40 : 0; // Large Straight score
    }

    private int scoreYahtzee(int[] dice) {
        int[] counts = countDiceOccurrences(dice);
        for (int count : counts) {
            if (count == 5) {
                return 50; // Yahtzee score
            }
        }
        return 0; // Not a valid score
    }

    private int scoreChance(int[] dice) {
        return sumArray(dice); // Sum of all dice
    }

    // Helper Methods
    private int countDice(int[] dice, int value) {
        int count = 0;
        for (int die : dice) {
            if (die == value) {
                count++;
            }
        }
        return count;
    }

    private int[] countDiceOccurrences(int[] dice) {
        int[] counts = new int[7]; // Index 0 unused
        for (int die : dice) {
            counts[die]++;
        }
        return counts;
    }

    private int sumArray(int[] array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }

    private int[] removeDuplicates(int[] dice) {
        return java.util.Arrays.stream(dice).distinct().toArray();
    }

    private boolean containsAll(int[] array, int[] subArray) {
        for (int value : subArray) {
            boolean found = false;
            for (int num : array) {
                if (num == value) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}
