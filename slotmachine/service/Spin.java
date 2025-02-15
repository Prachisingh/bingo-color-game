package slotmachine.service;

import slotmachine.dto.WinData;
import slotmachine.test.WinBand;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer class that contains Spin data.
 */
public class Spin {

   public BigDecimal totalWin;
   public boolean isFsTriggered;
   public boolean isWheelTriggered;
   private Wheel wheel;

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public boolean isWheelTriggered() {
        return isWheelTriggered;
    }

    public void setWheelTriggered(boolean wheelTriggered) {
        isWheelTriggered = wheelTriggered;
    }

    List<List<WinData>> cascadeList;

    private List<WinBand> winSummaryBands = new ArrayList<>();
   int fsAwarded;

   private Colours colours;

    public BigDecimal getTotalWin() {
        return totalWin;
    }

    public List<List<WinData>> getCascadeList() {
        return cascadeList;
    }

    public void setCascadeList(List<List<WinData>> cascadeList) {
        this.cascadeList = cascadeList;
    }

    public void setTotalWin(BigDecimal totalWin) {
        this.totalWin = totalWin;
    }

    public boolean isFsTriggered() {
        return isFsTriggered;
    }

    public void setFsTriggered(boolean fsTriggered) {
        isFsTriggered = fsTriggered;
    }

    public int getFsAwarded() {
        return fsAwarded;
    }

    public void setFsAwarded(int fsAwarded) {
        this.fsAwarded = fsAwarded;
    }

    public List<WinBand> getWinSummaryBands() {
        return winSummaryBands;
    }

    public void setWinSummaryBands(List<WinBand> winSummaryBands) {
        this.winSummaryBands = winSummaryBands;
    }

    public Colours getColours() {
        return colours;
    }

    public void setColours(Colours colours) {
        this.colours = colours;
    }

    @Override
    public String toString() {
        return "Spin{" +
                "totalWin=" + totalWin +
                ", isFsTriggered=" + isFsTriggered +
                ", isWheelTriggered=" + isWheelTriggered +
                ", cascadeList=" + cascadeList +
                ", winSummaryBands=" + winSummaryBands +
                ", fsAwarded=" + fsAwarded +
                ", colours=" + colours +
                '}';
    }
}

