package slotmachine.service;

import slotmachine.config.GameConfiguration;
import slotmachine.dto.BoardNumbers;
import slotmachine.dto.JackpotPrizes;
import slotmachine.dto.Spin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static slotmachine.util.GameUtility.printBingoTicket;

/**
 * Class handles free spins.
 */
public class FreeSpins {
    static Random rng = new Random();


    public static void main(String[] args) {
        GameConfiguration gameConfiguration = new GameConfiguration();
//        playFreeSpins(rng, 10, gameConfiguration, baseGameResponse.getScatterPositions());
    }

    public static Spin playFreeSpins(Random rng, int fsAwarded, GameConfiguration gameConfiguration, List<Integer> scatterPositions, int stake) {
        // initialize bingo ticket
        BingoGame bingoGame = new BingoGame(scatterPositions);

        // mark scatter positions as -1
        bingoGame.removeNumberFromScatterPos(scatterPositions, bingoGame.getBingoTicket(), gameConfiguration);
        //Sytem.out.println("Scatter Pos " + scatterPositions);

        // mark minor positions as MINOR
        bingoGame.markMinorJackpotPos();
        bingoGame.setNumberAsJackPotPos(gameConfiguration, bingoGame.getMinorJackpotPos(), "MINOR");

        // mark major positions as GRAND
        bingoGame.markMajorJackpotPos();
        bingoGame.setNumberAsJackPotPos(gameConfiguration, bingoGame.getMajorJackpotPos(), "MAJOR");

        // mark grand positions as GRAND

        bingoGame.markGrandJackpotPos();
        bingoGame.setNumberAsJackPotPos(gameConfiguration, bingoGame.getGrandJackpotPos(), "GRAND");

        //Sytem.out.println("Bingo ticket: ");


        Spin freeSpin = new Spin();
        BigDecimal totalWin = BigDecimal.ZERO;
        for (int i = fsAwarded; i > 0; i--) {

            // draw three random numbers for top

            int numToMatch = WeightedPrizeService.getPrizes(rng, gameConfiguration.numberToMatch);
            List<Integer> topNumbers = new ArrayList<>();

            if (numToMatch == 0) {
                topNumbers = getListOfZeroNumsToMatch(rng, bingoGame);
                //NO PRIZE.
            } else if (numToMatch == 1) {
                topNumbers.clear();
                int numType = WeightedPrizeService.getPrizes(rng, gameConfiguration.numOfTypeToMatch);
                getTopNumList(rng, numType, bingoGame, topNumbers);

                for (int j = 0; j < 2; j++) {
                    int nonMatchSingleNum = getSingleNonMatchingNumber(rng, bingoGame);
                    topNumbers.add(nonMatchSingleNum);
                }
            } else if (numToMatch == 2) {
                topNumbers.clear();
                // two times loop
                for (int j = 0; j < 2; j++) {
                    int numType = WeightedPrizeService.getPrizes(rng, gameConfiguration.numOfTypeToMatch);
                    getTopNumList(rng, numType, bingoGame, topNumbers);
                }
                int nonMatchSingleNum = getSingleNonMatchingNumber(rng, bingoGame);
                topNumbers.add(nonMatchSingleNum);


            } else if (numToMatch == 3) {
                topNumbers.clear();
                for (int j = 0; j < 3; j++) {
                    int numType = WeightedPrizeService.getPrizes(rng, gameConfiguration.numOfTypeToMatch);
                    getTopNumList(rng, numType, bingoGame, topNumbers);
                }
            }

            // mark the top numbers int the bingo ticket.

            markTopNumbersInTicket(topNumbers, bingoGame.getBingoTicket());
        }
        //printBingoTicket(bingoGame.getBingoTicket(), gameConfiguration);
        BigDecimal minorJpWin = calculateJPPrizes(bingoGame.getBingoTicket(), "MINOR", stake, 3);
        BigDecimal majorJpWin = calculateJPPrizes(bingoGame.getBingoTicket(), "MAJOR", stake, 4);
        BigDecimal grandJpWin = calculateJPPrizes(bingoGame.getBingoTicket(), "GRAND", stake, 5);

        //TODO add winning lines
        BigDecimal ticketPrize = calculate15XTicketPrize(false, gameConfiguration, stake);

        totalWin = totalWin.add(minorJpWin).add(majorJpWin).add(grandJpWin).add(ticketPrize);

        freeSpin.setTotalWin(totalWin);
        return freeSpin;
    }

    private static BigDecimal calculate15XTicketPrize(boolean isWon, GameConfiguration gameConfiguration, int stake) {
        if (!isWon) {
            return BigDecimal.ZERO;
        }
        int prize = WeightedPrizeService.getPrizes(rng, gameConfiguration.ticketPrizes);
        return BigDecimal.valueOf(prize).multiply(BigDecimal.valueOf(stake));
    }

    private static BigDecimal calculateJPPrizes(List<List<BoardNumbers>> bingoTicket, String jpType, int stake, int totalCount) {
        int prize = 0;
        int count = 0;
        for (List<BoardNumbers> boardNumbers : bingoTicket) {
            for (BoardNumbers boardNumber : boardNumbers) {
                if (boardNumber.isWin() && boardNumber.getNumType().equals(jpType)) {
                    count++;
                }
            }
        }
        if(count == totalCount){
            prize = JackpotPrizes.getPrizeBasedOnName(jpType).prize;
        }
        return BigDecimal.valueOf(prize).multiply(BigDecimal.valueOf(stake));
    }

    private static void markTopNumbersInTicket(List<Integer> topNumbers, List<List<BoardNumbers>> bingoTicket) {
        for (List<BoardNumbers> boardNumbers : bingoTicket) {
            for (BoardNumbers boardNumber : boardNumbers) {
                if (topNumbers.contains(boardNumber.getNumber())) {
                    boardNumber.setWin(true);
                }
            }
        }
    }

    private static void getTopNumList(Random rng, int numType, BingoGame bingoGame, List<Integer> topNumbers) {
        if (numType == 0) {
            int normalNumb = getSingleTopNumToMatch(rng, bingoGame, "normal", topNumbers);
            topNumbers.add(normalNumb);
        } else if (numType == 1) {
            int minorNumb = getSingleTopNumToMatch(rng, bingoGame, "MINOR", topNumbers);
            topNumbers.add(minorNumb);
        } else if (numType == 2) {
            int majorNumb = getSingleTopNumToMatch(rng, bingoGame, "MAJOR", topNumbers);
            topNumbers.add(majorNumb);
        } else if (numType == 3) {
            int grandNumb = getSingleTopNumToMatch(rng, bingoGame, "GRAND", topNumbers);
            topNumbers.add(grandNumb);
        }
    }

    private static int getSingleTopNumToMatch(Random rng, BingoGame bingoGame, String type, List<Integer> topNumbers) {
        int num;
        do {
            num = rng.nextInt(1, 76);
        } while (!bingoGame.getSingleNumToMatch(num, type) || topNumbers.contains(num));
        return num;
    }

    private static List<Integer> getListOfZeroNumsToMatch(Random rng, BingoGame bingoGame) {
        int num;
        List<Integer> threeTopNumbers = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            do {
                num = rng.nextInt(1, 76);
            } while (bingoGame.hasNumber(num) || threeTopNumbers.contains(num));
            threeTopNumbers.add(num);
        }
        return threeTopNumbers;
    }

    private static int getSingleNonMatchingNumber(Random rng, BingoGame bingoGame) {
        int num;
        do {
            num = rng.nextInt(1, 76);
        } while (bingoGame.hasNumber(num));
        return num;
    }

}
