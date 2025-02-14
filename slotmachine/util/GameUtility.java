package slotmachine.util;

import slotmachine.config.GameConfiguration;

import java.util.ArrayList;
import java.util.List;

public class GameUtility {
    public static List<Integer> getReelLength(List<String[]> bgReels){
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
}
