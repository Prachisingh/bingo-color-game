package slotmachine.util;

import slotmachine.config.GameConfiguration;
import slotmachine.dto.BoardNumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameUtility {
    private static Map<Integer, Integer> fsTriggeredMap = new HashMap();
    static {
        fsTriggeredMap.put(3, 10);
        fsTriggeredMap.put(4, 12);
    }

    public static List<Integer> getReelLength(List<String[]> bgReels) {
        List<Integer> reelLengthList = new ArrayList<>();
        for (String[] reel : bgReels) {
            reelLengthList.add(reel.length);
        }
        return reelLengthList;
    }


    public static void printSlotFace(List<String[]> slotFace, GameConfiguration gameConfiguration) {
        for (int row = 0; row < gameConfiguration.boardHeight; row++) {
            for (int col = 0; col < gameConfiguration.boardWidth; col++) {

                System.out.print(" " + slotFace.get(col)[row]);
            }
            System.out.println();
        }
    }

    public static void printBingoTicket(List<List<BoardNumbers>> bingoTicket, GameConfiguration gameConfiguration) {
        for (int row = 0; row < gameConfiguration.boardHeight; row++) {
            for (int col = 0; col < gameConfiguration.boardWidth; col++) {

                System.out.print(" " + bingoTicket.get(col).get(row));
            }
            System.out.println();
        }
    }

    public static int getFsAwarded(int scatterCount) {
        if (scatterCount >= 5) {
            return 15; // max fs awarded if scatter is more than 5
        }
        return fsTriggeredMap.get(scatterCount);
    }
}
