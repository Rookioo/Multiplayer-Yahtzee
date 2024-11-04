/**
 * YahtzeeScoring.java
 * 
 * Purpose: This class Handles all of the scoring based on the Yahtzee rules through a switch statement.
 * Author: Dustin Anderson
 * Date: 2024-09-30
 */

public class YahtzeeScoring {
    /**
     * Method description: This method takes the categoryIndex 0-12 and looks into that case along with the dice value and gives a score based on the users selected case.
     * @param categoryIndex An int that is the index the player selected as their Yahtzee category
     * @param dice An array of integers of the dice for scoring purposes
     */
    public int scoreCategory(int categoryIndex, int[] dice) {
        switch (categoryIndex) {
            case 0: return scoreOnes(dice);
            case 1: return scoreTwos(dice);
            case 2: return scoreThrees(dice);
            case 3: return scoreFours(dice);
            case 4: return scoreFives(dice);
            case 5: return scoreSixes(dice);
            case 6: return scoreSplit(dice);
            case 7: return scoreThreeOfAKind(dice);
            case 8: return scoreFourOfAKind(dice);
            case 9: return scoreFullHouse(dice);
            case 10: return scoreSmallStraight(dice);
            case 11: return scoreLargeStraight(dice);
            case 12: return scoreGiantStraight(dice);
            case 13: return scoreYahtzee(dice);
            case 14: return scoreSixOfAKind(dice);
            case 15: return scoreChance(dice);
            default: return -1; // Invalid category
        }
    }

    /**
     * METHOD DESCRIPTIONS: ALL of the bellow methods match up to a case within the scoreCategory() method and based on a dice value give a score to the user for that category
     * @param dice An array of dice to be compared to the value for the category
     * @return countDice(dice, 1) Linked to a method that then returns the value of the true score value of the dice
     */
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

    private int scoreSplit(int[] dice) {
        int[] counts = countDiceOccurrences(dice);
        boolean hasThreeOfFirst = false;
        boolean hasThreeOfSecond = false;

        for (int count : counts) {
            if (count == 3) {
                if (!hasThreeOfFirst) {
                    hasThreeOfFirst = true; // Found the first three
                } else if (!hasThreeOfSecond) {
                    hasThreeOfSecond = true; // FOund the second three
                }
            }
        }

        if (hasThreeOfFirst && hasThreeOfSecond) {
            return 50;
        } else {
            return 0;
        }
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

    private int scoreGiantStraight(int[] dice) {
        int[] uniqueDice = removeDuplicates(dice);
        boolean giantStraightFound = false;
        if (containsAll(uniqueDice, new int[]{1, 2, 3, 4, 5, 6})) {
            giantStraightFound = true;
        }
        return giantStraightFound ? 80 : 0; // Giant Straight score
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

    private int scoreSixOfAKind(int[] dice) {
        int[] counts = countDiceOccurrences(dice);
        for (int count : counts) {
            if (count == 6) {
                return 100; // Six of a kind score
            }
        }
        return 0; // Not a valid score
    }

    private int scoreChance(int[] dice) {
        return sumArray(dice); // Sum of all dice
    }
    

    /**
     * Method Description: This method is crutial for counting the number of dice of a specific value for categories like "Ones" or "Twos"
     * @param dice An array of dice to be compared to the desired value
     * @param value An integer value the dice is compared to for additional count (points)
     * @return the integer count of all the points gained from the comparison
     */
    private int countDice(int[] dice, int value) {
        int count = 0;
        for (int die : dice) {
            if (die == value) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method Description: This method rather than counting a single value like countDice counts multiple values at the same time
     * @param dice An array of dice to be compared to the desired values
     * @return the integer count of all the points gained from the comparison
     */
    private int[] countDiceOccurrences(int[] dice) {
        int[] counts = new int[7]; // Index 0 unused
        for (int die : dice) {
            counts[die]++;
        }
        return counts;
    }

    /**
     * Method Description: This method is used for just adding up the total score of all the categories for methods like chance
     * @param array An array of values which it adds to the total
     * @return total The total value added from all array items
     */
    private int sumArray(int[] array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }

    /**
     * Note: Uses a new part of java for me the .stream I discovered when searching up easy ways to remove duplicates
     * Method Description: Removes duplicates from the given array of dice
     * @param dice An array of integers that are dice getting deduplicated
     */
    private int[] removeDuplicates(int[] dice) {
        return java.util.Arrays.stream(dice).distinct().toArray();
    }

    /**
     * Method Description: This method allows for checking if an array is equal to a subarray which is used in a variety of the odd case categories
     * @param array One array of integers to be compared
     * @param array Another ray of integers to be compared too
     * @return bool returns true if if all elements match but if any elements from the subarray are missing in the array it returns false
     */
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
