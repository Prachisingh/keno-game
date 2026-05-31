package game.keno;

import java.util.*;

public class KenoGame {

    private Set<Integer> serverDrawnNumbers;
    private Set<Integer> playerNumbers;

    private Set<Integer> matchedNumbers;
    private  int lastServerNum;
    private  int multiplier = 1;


    public void getServerDrawnNumbers(Random random) {
        serverDrawnNumbers = new HashSet<>(); // Clear previous drawn numbers before generating new ones

        while (serverDrawnNumbers.size() < 20) {
            int drawnNumber = random.nextInt(80) + 1;
            serverDrawnNumbers.add(drawnNumber);
            lastServerNum = drawnNumber; // Update lastServerNum with the most recently drawn number

        }
        // check if server numbers is 20 numbers
        if(serverDrawnNumbers.size() != 20){
            System.out.println("Duplicate numbers generated for server draw, regenerating...");
            throw new RuntimeException("Duplicate numbers generated for server draw, regenerating...");
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

    public void checkIfMultiplierTriggered() {
       if(playerNumbers.contains(lastServerNum)){
           multiplier = 8;
       }
    }

    Set<Integer> getPlayerNumbers(Random random) {
        // This method should return the player's chosen numbers.
        // player may select between 2 and 10 numbers from a pool of 80 numbers

        int numberOfSpots = random.nextInt(2, 11) ;
        if(numberOfSpots == 1 || numberOfSpots > 10){
            throw new IllegalStateException("Number of spots must be between 2 and 10. Generated: " + numberOfSpots);
        }
//        numberOfSpots = 2; // For testing purposes, you can set this to a fixed value between 2 and 10

        playerNumbers = new HashSet<>(); // Clear previous player numbers before generating new ones


        while (playerNumbers.size() < numberOfSpots) {
            int chosenNumber = random.nextInt(80) + 1;
            playerNumbers.add(chosenNumber);

        }
        if(numberOfSpots != playerNumbers.size()){
            System.out.println("Duplicate numbers generated, regenerating...");
            throw new RuntimeException("Duplicate numbers generated, regenerating...");
        }

        return playerNumbers;
    }

    public SpinResponse playGame(int stake, Random random) {
        multiplier = 1; // Reset multiplier for each game;
        getServerDrawnNumbers(random);
        getPlayerNumbers(random);


        checkMatches();
        double winningAmount = 0;


        if (playerNumbers.size() >= 2) {
            checkIfMultiplierTriggered();

            // choose the payout based on the number of matches and the stake

            KenoPayout selectedPayTable =  getPayoutTable().get(playerNumbers.size());
            winningAmount = selectedPayTable.calculateWinningAmount(stake, matchedNumbers.size());



        } else {
            System.out.println("Sorry, you only matched " + matchedNumbers.size() + " numbers: " + matchedNumbers);
        }
        SpinResponse spinResponse = new SpinResponse();
        spinResponse.setWinAmount(winningAmount * multiplier);
        spinResponse.setNumberOfSpots(playerNumbers.size());

        return spinResponse;

    }



    private Map<Integer, KenoPayout> getPayoutTable() {

        return Map.of(
                2, new KenoPayout(Map.of(1, 0.0, 2, 8.7)),
                3, new KenoPayout(Map.of(1, 0.0, 2, 1.0, 3, 23.0)),
                4, new KenoPayout(Map.of(1, 0.0, 2, 1.0, 3, 3.0, 4, 36.0)),
                5, new KenoPayout(Map.of(1, 0.0, 2, 0.0, 3, 2.0, 4, 15.0, 5, 62.0)),
                6, new KenoPayout(Map.of(1, 0.0, 2, 0.0, 3, 1.0, 4, 6.0, 5, 18.0, 6, 150.0)),
                7, new KenoPayout(Map.of(1, 0.0, 2, 0.0, 3, 1.0, 4, 2.0, 5, 6.0, 6, 48.0, 7, 350.0)),
                8, new KenoPayout(Map.of(1, 0.0, 2, 0.0, 3, 0.0, 4, 2.0, 5, 5.0, 6, 18.0, 7, 185.0, 8, 700.0)),
                9, new KenoPayout(Map.of(1, 0.0, 2, 0.0, 3, 0.0, 4, 1.0, 5, 3.0, 6, 12.0, 7, 45.0, 8, 300.0, 9, 1000.0)),
                10, new KenoPayout(Map.of(1, 0.0, 2, 0.0, 3, 0.0, 4, 1.0, 5, 2.0, 6, 4.0, 7, 11.0, 8, 80.0, 9, 350.0, 10, 1500.0))

        );

    }

    public static void main(String[] args) {
        KenoGame game = new KenoGame();
        game.playGame(2, new Random());
    }


}
