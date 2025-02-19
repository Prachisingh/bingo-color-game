package slotmachine.service;

import slotmachine.config.GameConfiguration;
import slotmachine.dto.BoardNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create a BingoGame class that has 5 columns, each column is a list of integers Bingo ticket comprise 5x5 matrix.
 */
public class BingoGame {

    private List<BoardNumbers> column1 = new ArrayList<>();
    private List<BoardNumbers> column2 = new ArrayList<>();
    private List<BoardNumbers> column3 = new ArrayList<>();
    private List<BoardNumbers> column4 = new ArrayList<>();
    private List<BoardNumbers> column5 = new ArrayList<>();

    private List<Integer> scatterPos = new ArrayList<>();
    private List<Integer> minorJackpotPos = new ArrayList<>();
    private List<Integer> majorJackpotPos = new ArrayList<>();
    private List<Integer> grandJackpotPos = new ArrayList<>();

    public List<Integer> getMinorJackpotPos() {
        return minorJackpotPos;
    }

    public void setMinorJackpotPos(List<Integer> minorJackpotPos) {
        this.minorJackpotPos = minorJackpotPos;
    }

    private List<List<BoardNumbers>> bingoTicket = new ArrayList<>();

    public BingoGame(List<Integer> scatterPos) {
        initializeTicket(column1, 1, 16);
        initializeTicket(column2, 16, 31);
        initializeTicket(column3, 31, 46);
        initializeTicket(column4, 46, 61);
        initializeTicket(column5, 61, 76);

        this.scatterPos = scatterPos;
        bingoTicket.add(column1);
        bingoTicket.add(column2);
        bingoTicket.add(column3);
        bingoTicket.add(column4);
        bingoTicket.add(column5);
    }

    public List<List<BoardNumbers>> getBingoTicket() {
        return bingoTicket;
    }

    public List<BoardNumbers> initializeTicket(List<BoardNumbers> column, int origin, int bound) {
        Random rnd = new Random();
        int rand;
        for (int i = 0; i < 5; i++) {
            BoardNumbers boardNumber = new BoardNumbers();

            do {
                rand = rnd.nextInt(origin, bound);
                boardNumber.setNumber(rand);
            } while (!column.isEmpty() && ifColumnContainsNum(column, rand));
            column.add(boardNumber);
        }
        return column;
    }

    private boolean ifColumnContainsNum(List<BoardNumbers> column, int number) {
        for (BoardNumbers boardNumber : column) {
            if (boardNumber.getNumber() == number) {
                return true;
            }
        }
        return false;

    }

    public void removeNumberFromScatterPos(List<Integer> scatterPos, List<List<BoardNumbers>> bingoTicket, GameConfiguration gameConfiguration) {
        this.setScatterPos(scatterPos);
        for (Integer pos : scatterPos) {
            int row = pos / gameConfiguration.boardWidth;
            int reel = pos % gameConfiguration.boardWidth;
            BoardNumbers boardNumber = bingoTicket.get(reel).get(row);
            boardNumber.setNumber(-1);
            boardNumber.setScatter(true);
            boardNumber.setNumType("scatter");
            bingoTicket.get(reel).set(row, boardNumber);
        }
    }

    public void setNumberAsJackPotPos(GameConfiguration gameConfiguration, List<Integer> jackpots, String jpName) {
        for (Integer pos : jackpots) {
            int row = pos / gameConfiguration.boardWidth;
            int reel = pos % gameConfiguration.boardWidth;
            BoardNumbers boardNumber = bingoTicket.get(reel).get(row);
            boardNumber.setJackPot(true);
            boardNumber.setNumType(jpName);
            bingoTicket.get(reel).set(row, boardNumber);
        }
    }

    public void setScatterPos(List<Integer> scatterPos) {
        this.scatterPos = scatterPos;
    }

    public void markMinorJackpotPos() {
        Random rnd = new Random();
        int rand;
        for (int i = 0; i < 3; i++) {
            do {
                rand = rnd.nextInt(0, 25);
            } while (scatterPos.contains(rand) || minorJackpotPos.contains(rand));
            minorJackpotPos.add(rand);
        }
        //System.out.println("Minor jackpot positions: " + minorJackpotPos);
    }

    public void markMajorJackpotPos() {
        Random rnd = new Random();
        int rand;
        for (int i = 0; i < 4; i++) {
            do {
                rand = rnd.nextInt(0, 25);
            } while (scatterPos.contains(rand) || minorJackpotPos.contains(rand) || majorJackpotPos.contains(rand));
            majorJackpotPos.add(rand);
        }
        //System.out.println("Major jackpot positions: " + majorJackpotPos);
    }

    public void markGrandJackpotPos() {
        Random rnd = new Random();
        int rand;
        for (int i = 0; i < 5; i++) {
            do {
                rand = rnd.nextInt(0, 25);
            } while (scatterPos.contains(rand) || minorJackpotPos.contains(rand) || majorJackpotPos.contains(rand) || grandJackpotPos.contains(rand));
            grandJackpotPos.add(rand);
        }
        //System.out.println("Grand jackpot positions: " + grandJackpotPos);
    }

    public List<Integer> getMajorJackpotPos() {
        return majorJackpotPos;
    }


    public List<Integer> getGrandJackpotPos() {
        return grandJackpotPos;
    }

    public boolean hasNumber(int topNumber) {

        for (List<BoardNumbers> column : bingoTicket) {
            if (column.stream().anyMatch(num -> num.getNumber() == topNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean getSingleNumToMatch(int topNumber, String type) {

        for (List<BoardNumbers> column : bingoTicket) {
            if (column.stream().anyMatch(num -> num.getNumber() == topNumber && num.getNumType().equals(type))) {
                return true;
            }
        }
        return false;
    }

    public boolean getOneMatchingNum(int topNumber) {
        int count = 0;
        for (List<BoardNumbers> column : bingoTicket) {
            if (column.stream().anyMatch(num -> num.getNumber() == topNumber)) {
                return true;
            }
        }
        return true;
    }
}
