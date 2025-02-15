package slotmachine.service;

import java.math.BigDecimal;

public enum JackpotPrizes {
    MINOR("MINOR", 50, 1, BigDecimal.valueOf(0.2)),
    MAJOR("MAJOR", 200, 2, BigDecimal.valueOf(0.2)),
    GRAND("GRAND", 1000, 3, BigDecimal.valueOf(0.1)),
    ;

    public final String jpName;
    public final int prize;
    public final int index;
    public final BigDecimal increment;

    JackpotPrizes(String name, int prize, int index, BigDecimal increment) {
        this.jpName = name;
        this.prize = prize;
        this.index = index;
        this.increment = increment;
    }

    public static JackpotPrizes getPrizeBasedOnIndex(int index) {
        for (JackpotPrizes jackpotPrizes : JackpotPrizes.values()) {

            if (jackpotPrizes.index == index) {
                return jackpotPrizes;
            }
        }
        return null;
    }
}
