package game.keno;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class KenoGame {

    private Set<Integer> serverDrawnNumbers;
    private Set<Integer> playerNumbers;

    private Set<Integer> matchedNumbers;


    public void getServerDrawnNumbers(Random random) {
        serverDrawnNumbers = new HashSet<>(); // Clear previous drawn numbers before generating new ones

        while (serverDrawnNumbers.size() < 20) {
            int drawnNumber = random.nextInt(80) + 1;
                serverDrawnNumbers.add(drawnNumber);

        }
    }

    public void checkMatches() {
        matchedNumbers = new HashSet<>();
        for (Integer number : playerNumbers) {
            if (serverDrawnNumbers.contains(number)) {
                matchedNumbers.add(number);
            }
        }
    }

    Set<Integer> getPlayerNumbers(Random random) {
        // This method should return the player's chosen numbers.
        // let's assume the player chooses 10 random numbers.

        playerNumbers = new HashSet<>(); // Clear previous player numbers before generating new ones
        while (playerNumbers.size() < 10) {
            int chosenNumber = random.nextInt(80) + 1;
                playerNumbers.add(chosenNumber);

        }
        return playerNumbers;
    }

    public int playGame(int stake, Random random) {
        getServerDrawnNumbers(random);
        getPlayerNumbers(random);
        checkMatches();
        int winningAmount = 0;


        if (matchedNumbers.size() >= 4) {

            winningAmount = calculateWinningAmount(stake);
//                System.out.println("Congratulations! You have matched " + matchedNumbers.size() + " numbers: " + matchedNumbers + ". You win! " + winningAmount);

        } else {
            //System.out.println("Sorry, you only matched " + matchedNumbers.size() + " numbers: " + matchedNumbers);
        }
        return winningAmount;

    }

    private int calculateWinningAmount(int stake) {
        int matchedCount = matchedNumbers.size();
        int winningAmount = 0;

        switch (matchedCount) {
            case 4:
                winningAmount = stake * 1; // Example payout for 4 matches
                break;
            case 5:
                winningAmount = stake * 2; // Example payout for 5 matches
                break;
            case 6:
                winningAmount = stake * 12; // Example payout for 6 matches
                break;
            case 7:
                winningAmount = stake * 60; // Example payout for 7 matches
                break;
            case 8:
                winningAmount = stake * 375; // Example payout for 8 matches
                break;
            case 9:
                winningAmount = stake * 2500; // Example payout for 9 matches
                break;
            case 10:
                winningAmount = stake * 10000; // Example payout for 10 matches
                break;
        }

        return winningAmount;
    }

    public static void main(String[] args) {
        KenoGame game = new KenoGame();
        game.playGame(2, new Random());
    }


}
