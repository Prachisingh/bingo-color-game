package slotmachine.test;

import slotmachine.config.GameConfiguration;
import slotmachine.service.BingoGame;
import slotmachine.service.FreeSpins;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCode {
    public static void main(String[] args) {

        Random random =new Random();
        GameConfiguration gameConfiguration = new GameConfiguration();
        List<Integer> scatterPositions =  List.of(1, 10, 24);
        FreeSpins.playFreeSpins(random, 20, gameConfiguration, scatterPositions, 1 );

    }
}
