package game.keno.test;

import game.keno.KenoGame;

public class KenoRTP {
        public static void main(String[] args) {
            KenoGame kenoGame = new KenoGame();
            int totalStake = 0;
            int totalWin = 0;
            int rounds = 1000_000; // Number of rounds to simulate
            int countWin = 0;

            for (int i = 0; i < rounds; i++) {
                int stake = 1; // Assuming a fixed stake of 1 unit per round
                int winAmount  = kenoGame.playGame(stake);
                if(winAmount >0) {
                    countWin++;
                }
                totalStake += stake;
                totalWin += winAmount;
            }

            double rtp = (double) totalWin / totalStake * 100;
            System.out.println("Hit rate: " + ((double) countWin / rounds * 100) + "%");
            System.out.println("Total Stake: " + totalStake);
            System.out.println("Total Win: " + totalWin);
            System.out.println("RTP: " + rtp + "% ");
        }


}
