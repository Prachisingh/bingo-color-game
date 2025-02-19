package slotmachine.dto;

import slotmachine.dto.Jackpot;

import java.math.BigDecimal;

public class Wheel {
    private boolean isBetMultiplier;
    private int betMultiplier;
    private boolean isJackpotWheelTriggered;
    private Jackpot jackpot;

    private BigDecimal wheelWins = BigDecimal.ZERO;

    public BigDecimal getWheelWins() {
        return wheelWins;
    }

    public void setWheelWins(BigDecimal wheelWins) {
        this.wheelWins = wheelWins;
    }

    public boolean isBetMultiplier() {
        return isBetMultiplier;
    }

    public Jackpot getJackpot() {
        return jackpot;
    }

    public void setJackpot(Jackpot jackpot) {
        this.jackpot = jackpot;
    }

    public int getBetMultiplier() {
        return betMultiplier;
    }

    public void setBetMultiplier(int betMultiplier) {
        this.betMultiplier = betMultiplier;
    }

    public void setBetMultiplier(boolean betMultiplier) {
        isBetMultiplier = betMultiplier;
    }

    public boolean isJackpotWheelTriggered() {
        return isJackpotWheelTriggered;
    }

    public void setJackpotWheelTriggered(boolean jackpotWheelTriggered) {
        isJackpotWheelTriggered = jackpotWheelTriggered;
    }
}
