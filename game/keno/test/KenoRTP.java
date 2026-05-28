package game.keno.test;

import game.keno.KenoGame;
import game.keno.RtpResult;
import game.keno.SpinResponse;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KenoRTP {
    static int numberOfAvailableThreads = Runtime.getRuntime().availableProcessors();
    //static int numberOfAvailableThreads = 1;
    static int rounds = 1000_0000; // Number of rounds to simulate
    static int finishedThreadCount = 0;
    static int stake = 1; // Assuming a fixed stake of 1 unit per round

    static double totalWin = 0;
    static int totalSpot2Count = 0;
    static int eachThreadRounds = rounds / numberOfAvailableThreads;
    static long startingTime;
    static RtpResult rtpResult = new RtpResult();

    public static void main(String[] args) {
        playGame();

//        ExecutorService  executorService = Executors.newFixedThreadPool(numberOfAvailableThreads);
//        startingTime = System.currentTimeMillis();
//
//        for(int i = 0; i < numberOfAvailableThreads; i++){
//            executorService.submit(()-> simulateKenoRounds());
//
//        }
    }

    private static void simulateKenoRounds() {

        addToRtpResult(playGame());

    }

    private static double playGame() {

        KenoGame kenoGame = new KenoGame();
        Random random = new Random();

        double totalWin = 0;
        double totalWinSpot2 = 0;
        double totalWinSpot3 = 0;
        double totalWinSpot4 = 0;
        double totalWinSpot5 = 0;
        double totalWinSpot6 = 0;
        double totalWinSpot7 = 0;
        double totalWinSpot8 = 0;

        int totalSpot2Count = 0;
        int totalSpot2WinCount = 0;
        int totalSpot3Count = 0;
        int totalSpot3WinCount = 0;

        int totalSpot4Count = 0;
        int totalSpot4WinCount = 0;

        int totalSpot5Count = 0;
        int totalSpot5WinCount = 0;

        int totalSpot6Count = 0;
        int totalSpot6WinCount = 0;

        int totalSpot7Count = 0;
        int totalSpot7WinCount = 0;

        int totalSpot8Count = 0;
        int totalSpot8WinCount = 0;

        int rounds = 1000_0000; // Number of rounds to simulate

        int countWin = 0;


        for (int i = 0; i < rounds; i++) {

            SpinResponse spinResponse = kenoGame.playGame(stake, random);
            double winAmount = spinResponse.getWinAmount();
            int spotNumber = spinResponse.getNumberOfSpots();


            if (spotNumber == 2 && winAmount > 0) {
                totalSpot2WinCount++;
                totalWinSpot2 += winAmount;
            }

            if (spotNumber == 3 && winAmount > 0) {
                totalSpot3WinCount++;
                totalWinSpot3 += winAmount;
            }

            if (spotNumber == 4 && winAmount > 0) {
                totalSpot4WinCount++;
                totalWinSpot4 += winAmount;
            }
            if (spotNumber == 5 && winAmount > 0) {
                totalSpot5WinCount++;
                totalWinSpot5 += winAmount;
            }
            if (spotNumber == 6 && winAmount > 0) {
                totalSpot6WinCount++;
                totalWinSpot6 += winAmount;
            }
            if (spotNumber == 7 && winAmount > 0) {
                totalSpot7WinCount++;
                totalWinSpot7 += winAmount;
            }
            if (spotNumber == 8 && winAmount > 0) {
                totalSpot8WinCount++;
                totalWinSpot8 += winAmount;
            }



            if (winAmount > 0) {
                countWin++;
            }

            totalWin += winAmount;
        }
        int totalStake = stake * rounds;
        double rtp = (double) totalWin / totalStake * 100;
        System.out.println("Hit rate: " + ((double) countWin / rounds * 100) + "%");
        System.out.println("Total Stake: " + totalStake);
        System.out.println("Total Win: " + totalWin);
        System.out.println("RTP: " + rtp + "% ");


        System.out.println("Hit rate for spot 2 is " + ((double) totalSpot2WinCount / rounds * 100) * 10 + "%");
        System.out.println("RTP for spot 2 is " + (totalWinSpot2 / totalStake * 100) + "%");

        System.out.println();
        System.out.println("Hit rate for spot 3 is " + ((double) totalSpot3WinCount / rounds * 100) * 10+ "%");
        System.out.println("RTP for spot 3 is " + (totalWinSpot3 / totalStake * 100) + "%");
        System.out.println();
        System.out.println("Hit rate for spot 4 is " + ((double) totalSpot4WinCount / rounds * 100) * 10+ "%");
        System.out.println("RTP for spot 4 is " + (totalWinSpot4 / totalStake * 100) + "%");
        System.out.println();
        System.out.println("Hit rate for spot 5 is " + ((double) totalSpot5WinCount / rounds * 100) * 10+ "%");
        System.out.println("RTP for spot 5 is " + (totalWinSpot5 / totalStake * 100) + "%");
        System.out.println();
        System.out.println("Hit rate for spot 6 is " + ((double) totalSpot6WinCount / rounds * 100) * 10+ "%");
        System.out.println("RTP for spot 6 is " + (totalWinSpot6 / totalStake * 100) + "%");
        System.out.println();
        System.out.println("Hit rate for spot 7 is " + ((double) totalSpot7WinCount / rounds * 100) * 10+ "%");
        System.out.println("RTP for spot 7 is " + (totalWinSpot7 / totalStake * 100) + "%");
        System.out.println();
        System.out.println("Hit rate for spot 8 is " + ((double) totalSpot8WinCount / rounds * 100) * 10+ "%");
        System.out.println("RTP for spot 8 is " + (totalWinSpot8 / totalStake * 100) + "%");

        return totalWin;
    }

    private static synchronized void addToRtpResult(double result) {
        finishedThreadCount++;
        totalWin += result;

        if (finishedThreadCount == numberOfAvailableThreads) {
            int totalStake = stake * rounds;
            System.out.println("All threads finished. Total RTP result: " + (totalWin / totalStake * 100) + "%");

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startingTime) / 1000.0 + " seconds");
        }

    }


}
