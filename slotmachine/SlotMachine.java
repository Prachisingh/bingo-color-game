package slotmachine;

import slotmachine.config.GameConfiguration;
import slotmachine.config.SlotSymbolWaysPayConfig;
import slotmachine.dto.*;
import slotmachine.service.*;
import slotmachine.util.GameUtility;

import java.math.BigDecimal;
import java.util.*;

import static slotmachine.util.GameUtility.*;

/**
 * Main class of the game that starts the base game, so the spinning and performs cascading. It also triggers the free games.
 */
public class SlotMachine {

    public static void main(String[] args) {
        Random rng = new Random();
        int stake = 1;
        GameConfiguration configuration = new GameConfiguration();
        play(stake, rng, configuration);

    }

    // pass game configuration
    public static void play(int stake, Random rng, GameConfiguration gameConfiguration) {
        List<String[]> bgReelSet = gameConfiguration.reelSets.getFirst();
        Spin baseGameResponse = playBaseGame(stake, rng, false, bgReelSet, gameConfiguration);
        if (baseGameResponse.isFsTriggered) {
            FreeSpins.playFreeSpins(rng, baseGameResponse.getFsAwarded(), gameConfiguration, baseGameResponse.getScatterPositions(), stake);
        }
    }

    public static Spin playBaseGame(int stake, Random rng, boolean isFreeGame, List<String[]> bgReelsA, GameConfiguration gameConfiguration) {
        Spin baseSpin = new Spin();
        List<Integer> stopPosition = new ArrayList<>();

        BigDecimal totalWin = BigDecimal.ZERO;


        List<String[]> slotFace = new ArrayList<>();

        createGrid(rng, isFreeGame, bgReelsA, stopPosition, slotFace, gameConfiguration);

        //Syster.out.println("Stop Positions:" + stopPosition.stream().map(Object::toString).collect(Collectors.joining("-")));
        //Syster.out.println("Screen:");


        //printSlotFace(slotFace, gameConfiguration);

        List<List<WinData>> cascadeList = new ArrayList<>();
        int scatterCount;
        totalWin = cascade(stake, slotFace, totalWin, stopPosition, cascadeList, baseSpin, isFreeGame, gameConfiguration);
        scatterCount = checkForScatterSym(slotFace, gameConfiguration, baseSpin);

        if (scatterCount >= 3) {
            baseSpin.setFsAwarded(getFsAwarded(scatterCount));
            baseSpin.setFsTriggered(true);
            baseSpin.setScatterCount(scatterCount);
        }
        matchColours(baseSpin, rng, gameConfiguration);

        if (baseSpin.isWheelTriggered()) {
            Wheel wheel = new Wheel();
            int result = spinWheel(gameConfiguration, rng);
            if (result != 0) {
                // award bet multiplier
                wheel.setBetMultiplier(result);
                BigDecimal win = BigDecimal.valueOf(result).multiply(BigDecimal.valueOf(stake));
                wheel.setWheelWins(win);
//                System.out.println("Wheel wins: " + win);
//                System.out.println("Bet multiplier: " + result);
//                System.out.println();
            } else {
                // award jackpot
                wheel.setJackpotWheelTriggered(true);
                int index = spinJackpotWheel(gameConfiguration, rng);
                int prize = JackpotPrizes.getPrizeBasedOnIndex(index).prize;
                wheel.setBetMultiplier(prize);
                BigDecimal win = BigDecimal.valueOf(prize).multiply(BigDecimal.valueOf(stake));
                wheel.setWheelWins(win);
//                System.out.println("Jackpot win: " + win);
            }
            baseSpin.setWheel(wheel);
        }

        baseSpin.setTotalWin(totalWin);

        return baseSpin;
    }

    private static void matchColours(Spin spin, Random rng, GameConfiguration gameConfiguration) {
        int color1 = rng.nextInt(6);
        int color2 = rng.nextInt(6);
        int color3 = rng.nextInt(6);
        int colorAtBottom = rng.nextInt(6);
        Colours colours = new Colours();
        List<Integer> colorsAtTop = new ArrayList<>();
        colorsAtTop.add(color1);
        colorsAtTop.add(color2);
        colorsAtTop.add(color3);
        colours.setColourAtBottom(colorAtBottom);
        colours.setColoursAtTop(colorsAtTop);
        spin.setColours(colours);
        if (color1 == color2 && color2 == color3 && color3 == colorAtBottom) {
            spin.setWheelTriggered(true);
//            System.out.println("Wheel triggered");
//            System.out.println(spin.getColours());
        }


    }

    public static int spinJackpotWheel(GameConfiguration gameConfiguration, Random rng) {
        return WeightedPrizeService.getPrizes(rng, gameConfiguration.jackpotPrizes);
    }

    private static int spinWheel(GameConfiguration gameConfiguration, Random rng) {
        return WeightedPrizeService.getPrizes(rng, gameConfiguration.wheelPrizes);
    }

    public static void createGrid(Random rng, boolean isFreeGame, List<String[]> bgReelsA, List<Integer> stopPosition, List<String[]> slotFace, GameConfiguration gameConfiguration) {
        int stopPos;
        for (String[] reel : bgReelsA) {
            stopPos = rng.nextInt(reel.length); //
            String[] slotFaceReel = selectReels(gameConfiguration.getBoardHeight(), reel, stopPos);
            stopPosition.add(stopPos);
            slotFace.add(slotFaceReel);
        }
    }

    public static BigDecimal cascade(int stake, List<String[]> slotFace, BigDecimal totalWin, List<Integer> stopPosition, List<List<WinData>> cascadeList, Spin spin, boolean isFreeGame, GameConfiguration gameConfiguration) {
        List<WinData> winDataList;
        int cascadeCounter = 0;
        do {

            winDataList = calculateWin(slotFace, stake, gameConfiguration);
            totalWin = getTotalWin(winDataList, totalWin);
            if (!winDataList.isEmpty()) {
//                for (WinData win : winDataList) {
//
//                    //System.out.println("- Ways win " + win.getPosList().stream().map(Object::toString).collect(Collectors.joining("-")) + ", " + win.getSymbolName() + " X" + win.getSymCountOnEachCol().size() + ", " + win.getWinAmount() + ", Ways: " + win.getWays());
//                }
                cascadeCounter++;
                //Syster.out.println("Cascade: " + cascadeCounter);
                removeSymFromWinPos(winDataList, slotFace, gameConfiguration);
                //Syster.out.println("Screen after removing Winning Symbols");
                //printSlotFace(slotFace, gameConfiguration);
                shiftSymbolsDownwards(slotFace);

                //Syster.out.println("Shifted Symbols ");
                // printSlotFace(slotFace, gameConfiguration);
                fillEmptyPosition(slotFace, stopPosition, isFreeGame, gameConfiguration);

            } else {
                //Syster.out.println("No more wins");
            }
            cascadeList.add(winDataList);
            spin.setCascadeList(cascadeList);
        } while (!winDataList.isEmpty());
        return totalWin;
    }

    private static BigDecimal getTotalWin(List<WinData> winDataList, BigDecimal totalWin) {
        for (WinData win : winDataList) {
            if (win.getWinAmount() != null) {
                totalWin = totalWin.add(win.getWinAmount());
            }
        }
        return totalWin;
    }

    private static void fillEmptyPosition(List<String[]> slotFace, List<Integer> stopPositions, boolean isFreeGame, GameConfiguration gameConfiguration) {
        List<String[]> reels;
        if (isFreeGame) {
            reels = gameConfiguration.reelSets.get(2);
        } else {
            reels = gameConfiguration.reelSets.getFirst();
        }

        List<Integer> reelLengths = GameUtility.getReelLength(reels);
        int reelIdx = 0;
        for (int col = 0; col < gameConfiguration.boardWidth; col++) {
            String[] reel = slotFace.get(col);
            for (int i = reel.length - 1; i >= 0; i--) {
                if (reel[i].contains("-1")) {
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) + reelLengths.get(reelIdx) - 1);
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) % reelLengths.get(reelIdx));

                    reel[i] = reels.get(reelIdx)[stopPositions.get(reelIdx)];
                }
            }
            reelIdx++;
        }
        //Syster.out.println("Updated stop positions: " + stopPositions.stream().map(Object::toString).collect(Collectors.joining("-")));
        //Syster.out.println("updated screen ");
        // printSlotFace(slotFace, gameConfiguration);
    }


    private static void shiftSymbolsDownwards(List<String[]> slotFaceContainingRemovedSymbols) {
        boolean some;
        for (String[] reel : slotFaceContainingRemovedSymbols) {

            for (int i = reel.length - 1; i > 0; i--) {

                if (reel[i].contains("-1")) {
                    some = false;
                    for (int j = i; j > 0; j--) {
                        if (!reel[j - 1].contains("-1")) {
                            some = true;
                        }
                        reel[j] = reel[j - 1];

                    }
                    reel[0] = "-1";
                    if (some) {
                        i++;
                    }
                }
            }
        }
    }

    private static void removeSymFromWinPos(List<WinData> winDataList, List<String[]> slotFace, GameConfiguration gameConfiguration) {
        for (WinData win : winDataList) {
            for (Integer pos : win.getPosList()) {
                int row = pos / gameConfiguration.boardWidth;
                int reel = pos % gameConfiguration.boardWidth;
                slotFace.get(reel)[row] = "-1";
            }
        }
    }

    private static String[] selectReels(int boardHeight, String[] reel, int position) {
        String[] boardReel = new String[boardHeight];
        for (int i = 0; i < boardHeight; i++) {
            if ((position + i) % reel.length == -1) {
                throw new RuntimeException("Exception caught");
            }
            boardReel[i] = reel[(position + i) % reel.length];


        }
        return boardReel;
    }

    private static List<WinData> calculateWin(List<String[]> slotFace, int stake, GameConfiguration gameConfiguration) {
        BigDecimal totalWin = BigDecimal.ZERO;
        List<WinData> winDataList = new ArrayList<>();

        for (int row = 0; row < slotFace.getFirst().length; row++) {

            String symToCompare = slotFace.getFirst()[row]; // only first column elements need to be compared.
            boolean exists = winDataList.stream().anyMatch(sym -> sym.getSymbolName().equals(symToCompare)); // if the symbol is already compared
            if (!winDataList.isEmpty() && exists) {
                continue;
            }

            WinData winData = checkForWinCombination(symToCompare, slotFace, gameConfiguration);
            populateWin(winData, winDataList, stake, gameConfiguration);
            if (winData.getWinAmount() != null) {
                totalWin = totalWin.add(winData.getWinAmount());
            }
        }

        return winDataList;
    }

    private static int checkForScatterSym(List<String[]> slotFace, GameConfiguration gameConfiguration, Spin spin) {
        int counter = 0;
        List<Integer> posList = new ArrayList<>();

        for (int col = 0; col < gameConfiguration.boardWidth; col++) {
            int pos = col;
            for (int row = 0; row < slotFace.get(col).length; row++) {
                String sym = slotFace.get(col)[row];
                if (sym.contains(gameConfiguration.SCATTER)) {
                    posList.add(pos);
                    counter++;
                }
                pos += 5;
            }
        }
        spin.setScatterPositions(posList);
        return counter;
    }

    private static void populateWin(WinData winData, List<WinData> winDataList, int stake, GameConfiguration gameConfiguration) {
        SlotSymbolWaysPayConfig payOut = gameConfiguration.payout.get(winData.getSymbolName());
        BigDecimal symbolWin;
        int ways;
        if (payOut != null && winData.getSymCountOnEachCol().size() >= payOut.getMinimumMatch()) {
            symbolWin = payOut.getWinAmount(winData.getSymCountOnEachCol().size());

            ways = 1;
            for (Map.Entry<Integer, Integer> entry : winData.getSymCountOnEachCol().entrySet()) {
                ways *= entry.getValue();
            }
            BigDecimal finalWin = symbolWin.multiply(BigDecimal.valueOf(ways));
            winData.setWinAmount(finalWin.multiply(BigDecimal.valueOf(stake))); // multiply with stake
            winData.setWays(ways);
            winData.setBasePayout(symbolWin);
            winDataList.add(winData);
        }
    }

    private static WinData checkForWinCombination(String symToCompare, List<String[]> slotFace, GameConfiguration gameConfiguration) {
        SlotSymbolWaysPayConfig payOut = gameConfiguration.payout.get(symToCompare);
        WinData winData = new WinData();
        List<Integer> posList = new ArrayList<>();
        Map<Integer, Integer> symCountPerColMap = new HashMap<>();
        int currentCol = 0;

        for (int col = 0; col < gameConfiguration.boardWidth; col++) {
            int symCountPerColumn = 0;
            int pos = col;
            if (col - currentCol > 1)
                break;
            for (int row = 0; row < slotFace.get(col).length; row++) {

                String currentSym = slotFace.get(col)[row];

                if (payOut != null && (symToCompare.equals(currentSym) || payOut.getWilds().contains(currentSym))) {

                    symCountPerColumn++;
                    symCountPerColMap.put(col, symCountPerColumn);

                    posList.add(pos);

                    currentCol = col;
                }
                pos += 5;
            }
        }
        winData.setPosList(posList);
        winData.setSymCountOnEachCol(symCountPerColMap);
        winData.setSymbolName(symToCompare);
        return winData;
    }
}
