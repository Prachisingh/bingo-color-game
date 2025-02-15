package slotmachine.test;

import slotmachine.service.OfAKindWins;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RtpResult {
    private BigDecimal totalWins = BigDecimal.ZERO;
    private BigDecimal totalFreeSpinsWins = BigDecimal.ZERO;
    private BigDecimal totalBaseGameWins = BigDecimal.ZERO;
    private BigDecimal totalWheelWins = BigDecimal.ZERO;
    private BigDecimal highestWinMultiplier = BigDecimal.ZERO;
    private BigDecimal totalMinorJpIncWins = BigDecimal.ZERO;
    private BigDecimal totalMajorJpIncWins = BigDecimal.ZERO;
    private BigDecimal totalGrandJpIncWins = BigDecimal.ZERO;

    private BigDecimal highestWin = BigDecimal.ZERO;

    public BigDecimal getTotalMajorJpIncWins() {
        return totalMajorJpIncWins;
    }

    public void setTotalMajorJpIncWins(BigDecimal totalMajorJpIncWins) {
        this.totalMajorJpIncWins = totalMajorJpIncWins;
    }

    public BigDecimal getTotalGrandJpIncWins() {
        return totalGrandJpIncWins;
    }

    public void setTotalGrandJpIncWins(BigDecimal totalGrandJpIncWins) {
        this.totalGrandJpIncWins = totalGrandJpIncWins;
    }

    public BigDecimal getTotalMinorJpIncWins() {
        return totalMinorJpIncWins;
    }

    public void setTotalMinorJpIncWins(BigDecimal totalMinorJpIncWins) {
        this.totalMinorJpIncWins = totalMinorJpIncWins;
    }

    private int numOfTimesFsTriggered;
    private int totalRuns;

    private Map<String, OfAKindWins> winningMap = new HashMap<>();

    public void merge(RtpResult newRtpResult) {
        this.totalWins = this.totalWins.add(newRtpResult.getTotalWins());
        this.totalFreeSpinsWins = this.totalFreeSpinsWins.add(newRtpResult.getTotalFreeSpinsWins());
        this.totalBaseGameWins = this.totalBaseGameWins.add(newRtpResult.getTotalBaseGameWins());
        this.totalWheelWins = this.totalWheelWins.add(newRtpResult.getTotalWheelWins());
        this.highestWinMultiplier = this.highestWinMultiplier.add(newRtpResult.getHighestWinMultiplier());
        this.highestWin = this.highestWin.add(newRtpResult.getHighestWin());

        this.numOfTimesFsTriggered += newRtpResult.getNumOfTimesFsTriggered();
        this.totalRuns += newRtpResult.getTotalRuns();
        this.totalMinorJpIncWins = this.totalMinorJpIncWins.add(newRtpResult.getTotalMinorJpIncWins());
        this.totalMajorJpIncWins = this.totalMajorJpIncWins.add(newRtpResult.getTotalMajorJpIncWins());
        this.totalGrandJpIncWins = this.totalGrandJpIncWins.add(newRtpResult.getTotalGrandJpIncWins());


        for (var entry : newRtpResult.getWinningMap().entrySet()) {

            if (this.winningMap.containsKey(entry.getKey())) {
                this.winningMap.get(entry.getKey()).merge(entry.getValue());
            } else {
                this.winningMap.put(entry.getKey(), entry.getValue());
            }
        }

    }
    public BigDecimal getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(BigDecimal totalWins) {
        this.totalWins = totalWins;
    }

    public BigDecimal getTotalFreeSpinsWins() {
        return totalFreeSpinsWins;
    }

    public void setTotalFreeSpinsWins(BigDecimal totalFreeSpinsWins) {
        this.totalFreeSpinsWins = totalFreeSpinsWins;
    }

    public BigDecimal getTotalBaseGameWins() {
        return totalBaseGameWins;
    }

    public void setTotalBaseGameWins(BigDecimal totalBaseGameWins) {
        this.totalBaseGameWins = totalBaseGameWins;
    }

    public BigDecimal getHighestWinMultiplier() {
        return highestWinMultiplier;
    }

    public BigDecimal getTotalWheelWins() {
        return totalWheelWins;
    }

    public void setTotalWheelWins(BigDecimal totalWheelWins) {
        this.totalWheelWins = totalWheelWins;
    }

    public void setHighestWinMultiplier(BigDecimal highestWinMultiplier) {
        this.highestWinMultiplier = highestWinMultiplier;
    }

    public BigDecimal getHighestWin() {
        return highestWin;
    }

    public void setHighestWin(BigDecimal highestWin) {
        this.highestWin = highestWin;
    }

    public int getNumOfTimesFsTriggered() {
        return numOfTimesFsTriggered;
    }

    public void setNumOfTimesFsTriggered(int numOfTimesFsTriggered) {
        this.numOfTimesFsTriggered = numOfTimesFsTriggered;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public Map<String, OfAKindWins> getWinningMap() {
        return winningMap;
    }

    public void setWinningMap(Map<String, OfAKindWins> winningMap) {
        this.winningMap = winningMap;
    }
}
