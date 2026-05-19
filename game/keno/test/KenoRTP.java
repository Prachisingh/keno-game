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
        ExecutorService  executorService = Executors.newFixedThreadPool(numberOfAvailableThreads);
        startingTime = System.currentTimeMillis();

        for(int i = 0; i < numberOfAvailableThreads; i++){
            executorService.submit(()-> simulateKenoRounds());

        }
    }

    private static void simulateKenoRounds() {

        addToRtpResult(playGame());

    }

    private static double playGame() {

        KenoGame kenoGame = new KenoGame();
        Random random = new Random();
        int totalStake = 0;
        double totalWin = 0;
//        int rounds = 1000_000; // Number of rounds to simulate

        int countWin = 0;


        for (int i = 0; i < eachThreadRounds; i++) {

            double winAmount = kenoGame.playGame(stake, random);
            if (winAmount > 0) {
                countWin++;
            }

            totalWin += winAmount;
        }

//        double rtp = (double) totalWin / totalStake * 100;
//        System.out.println("Hit rate: " + ((double) countWin / rounds * 100) + "%");
//        System.out.println("Total Stake: " + totalStake);
//        System.out.println("Total Win: " + totalWin);
//        System.out.println("RTP: " + rtp + "% ");
        return totalWin;
    }

    private static synchronized void addToRtpResult(double result) {
        finishedThreadCount++;
        totalWin += result;

        if (finishedThreadCount == numberOfAvailableThreads) {
            int totalStake = stake * rounds;
            System.out.println("All threads finished. Total RTP result: " + (totalWin/totalStake * 100) + "%");

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startingTime) / 1000.0 + " seconds");
        }

    }


}
