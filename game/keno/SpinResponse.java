package game.keno;

public class SpinResponse {
    private int matchedNumbersCount;
    private double winAmount;
    private boolean multiplierTriggered;
    private int lastServerNum;
    private int multiplier;
    private int numberOfSpots;

    public int getMatchedNumbersCount() {
        return matchedNumbersCount;
    }

    public void setMatchedNumbersCount(int matchedNumbersCount) {
        this.matchedNumbersCount = matchedNumbersCount;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    public boolean isMultiplierTriggered() {
        return multiplierTriggered;
    }

    public void setMultiplierTriggered(boolean multiplierTriggered) {
        this.multiplierTriggered = multiplierTriggered;
    }

    public int getLastServerNum() {
        return lastServerNum;
    }

    public void setLastServerNum(int lastServerNum) {
        this.lastServerNum = lastServerNum;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getNumberOfSpots() {
        return numberOfSpots;
    }

    public void setNumberOfSpots(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }
}
