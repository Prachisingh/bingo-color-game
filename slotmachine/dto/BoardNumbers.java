package slotmachine.dto;

public class BoardNumbers {
    int number;
    String numType = "normal";
    boolean isJackPot;
    boolean isWin;
    boolean isScatter;
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public boolean isScatter() {
        return isScatter;
    }

    public void setScatter(boolean scatter) {
        isScatter = scatter;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNumType() {
        return numType;
    }

    public void setNumType(String numType) {
        this.numType = numType;
    }

    public boolean isJackPot() {
        return isJackPot;
    }

    public void setJackPot(boolean jackPot) {
        isJackPot = jackPot;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    @Override
    public String toString() {
        return "BoardNumbers{" +
                "number=" + number +
                ", NumberType='" + numType + '\'' +
                ", isJackPot=" + isJackPot +
                ", isWin=" + isWin +
                ", isScatter=" + isScatter +
                '}';
    }
}
