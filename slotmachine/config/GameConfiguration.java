package slotmachine.config;

import slotmachine.service.WeightedPrizeData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class GameConfiguration {
    public int boardHeight = 5;
    public final int boardWidth = 5;
    public final String AA = "AA";
    public final String BB = "BB";
    public final String CC = "CC";
    public final String DD = "DD";
    public final String EE = "EE";
    public final String FF = "FF";
    public final String GG = "GG";
    public final String HH = "HH";
    public final String JJ = "JJ";
    public final String KK = "KK";
    public final String LL = "LL";
    public final String SC = "SC";
    public final String WC = "WC";
    public final String SCATTER = "SC";

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public Map<String, SlotSymbolWaysPayConfig> payout = createPayout();

    public List<List<String[]>> reelSets = createReelSets();
    public WeightedPrizeData wheelPrizes = createWheelPrizes();
    public WeightedPrizeData jackpotPrizes = createJackpotPrizes();
    public WeightedPrizeData numberToMatch = getNumbersToMatch();
    public WeightedPrizeData numOfTypeToMatch = getNumberTypeToMatch();
    public WeightedPrizeData ticketPrizes = createTicketPrizes();
    public List<List<Integer>> winLines = getWinLines();

    public Map createPayout() {

        return Map.ofEntries(
                entry(AA, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.5), BigDecimal.valueOf(1.25), BigDecimal.valueOf(2.5))).addWild(WC)),
                entry(BB, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.4), BigDecimal.valueOf(1), BigDecimal.valueOf(2))).addWild(WC)),
                entry(CC, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.35), BigDecimal.valueOf(0.75), BigDecimal.valueOf(1.5))).addWild(WC)),
                entry(DD, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.3), BigDecimal.valueOf(0.6), BigDecimal.valueOf(1.25))).addWild(WC)),

                entry(EE, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.25), BigDecimal.valueOf(0.5), BigDecimal.valueOf(1))).addWild(WC)),
                entry(FF, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.25), BigDecimal.valueOf(0.5), BigDecimal.valueOf(1))).addWild(WC)),

                entry(GG, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.35))).addWild(WC)),
                entry(HH, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.35))).addWild(WC)),
                entry(JJ, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.35))).addWild(WC)),
                entry(KK, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.35))).addWild(WC)),
                entry(LL, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.35))).addWild(WC))
        );
    }

    public List<List<String[]>> createReelSets() {
        List<List<String[]>> gameReels = new ArrayList<>();
        List<String[]> bgReels = new ArrayList<>(5);

        bgReels.add(new String[]{
                GG, BB, FF, LL, JJ, LL, BB, DD, KK, HH, KK, LL, AA, LL, KK, JJ, JJ, CC, LL, HH, LL, EE, CC, AA, EE, GG, KK, HH, FF, LL, LL, BB, EE, CC, CC, CC, AA, EE, FF, LL, CC, HH, GG, LL, BB, FF, KK, LL, JJ, KK, LL, GG, FF, EE, CC, KK, JJ, EE, LL, HH, LL, KK, JJ, FF, LL, CC, DD, CC, HH, KK, GG, DD, DD, JJ, SC, CC, JJ, EE, HH, KK, JJ, FF, EE, CC, GG, EE, AA, DD, JJ, BB, KK, LL, LL, EE, FF, LL, DD, FF, DD, DD, GG, KK, EE, FF, HH, KK, JJ, GG, HH, KK, JJ, LL, FF, FF, EE, JJ, DD, GG, AA, KK, BB, GG, KK, DD, DD, LL, LL, LL, KK, HH, LL, LL, LL, GG, HH, FF, BB, KK, LL, LL, LL, FF, LL, JJ, DD, CC, LL, JJ, KK, LL, BB, HH, CC, JJ, FF, HH, CC, KK, HH, FF, KK, AA, JJ, KK, BB, BB, JJ, JJ, FF, CC, EE, EE, KK, GG, GG, LL, LL, GG, HH, LL, JJ, BB, HH, LL, DD, DD, BB, GG, KK, HH, FF, EE, HH, JJ, KK, GG, LL, DD, HH, GG, AA, JJ, HH, KK, LL, JJ, HH, KK, FF, HH, DD, JJ, EE, BB, KK, LL, JJ, EE, CC, CC, JJ, KK, KK, HH, EE, LL, FF, FF, LL, EE, GG, HH, BB, DD, LL, LL, SC, JJ, JJ, EE, EE, GG, JJ, FF, HH, DD, JJ, LL, GG, GG, GG, KK, CC, DD, HH, HH, DD, KK, CC, GG, JJ, HH, HH, FF, KK, LL, AA, DD, GG, LL, FF, GG, GG, KK, BB, SC, LL, DD, JJ, KK, FF, HH, LL, LL, KK, KK, LL, FF, GG, LL, LL, DD, KK, CC, FF, LL, KK, DD, HH, GG, JJ, CC, CC, CC, KK, DD, LL, EE, LL, GG, KK, JJ, KK, AA, GG, GG, CC, FF, DD, KK, LL, FF, EE, GG, CC, CC, JJ, EE, DD, JJ, KK, FF, LL, CC, FF, SC, FF, LL, CC, KK, FF, HH, JJ, BB, GG, EE, EE, JJ, KK, FF, GG, JJ, DD, KK, BB, FF, FF, JJ, EE, HH, KK, JJ, BB, GG, DD, DD, KK, HH, JJ, JJ, LL, LL, DD, FF, HH, JJ, HH, HH, KK, KK, JJ, JJ, AA, LL, HH, FF, GG, CC, CC, JJ, EE, HH, GG, KK, LL, AA, FF, HH, HH, GG, GG, CC, HH, HH, BB, JJ, GG, HH, LL, JJ, HH, LL, FF, FF, GG, JJ, KK, EE, HH, GG, AA, FF, BB, GG, JJ, JJ, JJ, JJ, KK, SC, LL, GG, GG, JJ, JJ, LL, FF, AA, KK, EE, HH, LL, LL, GG, FF, KK, KK, EE, EE, BB, LL, HH, DD, FF, LL, JJ, JJ, GG, HH, EE, JJ, AA, DD, DD, BB, GG, KK, LL, JJ, EE, SC, HH, JJ, EE, FF, EE, EE, LL, LL, KK, GG, SC, LL, FF, DD, EE, KK, GG, GG, GG, EE, FF, JJ, HH, KK, BB, EE, HH, SC, JJ, JJ, DD, KK, JJ, KK, FF, FF, LL, JJ, EE, BB, BB, JJ, EE, DD, GG, HH, BB, DD, DD, SC, FF, EE, KK, KK, LL, LL, JJ, DD, EE,});
        bgReels.add(new String[]{
                JJ, BB, LL, KK, KK, HH, HH, CC, HH, JJ, HH, GG, GG, FF, EE, HH, FF, DD, GG, CC, CC, LL, KK, KK, FF, DD, CC, LL, KK, HH, HH, EE, JJ, LL, LL, JJ, KK, FF, LL, JJ, DD, GG, LL, JJ, KK, JJ, FF, GG, AA, JJ, KK, LL, LL, FF, FF, HH, KK, FF, FF, BB, KK, JJ, FF, EE, EE, HH, DD, DD, GG, BB, HH, KK, CC, LL, GG, DD, LL, JJ, CC, AA, KK, LL, EE, SC, HH, FF, KK, LL, WC, LL, JJ, HH, GG, FF, EE, KK, JJ, CC, CC, KK, HH, BB, FF, SC, LL, GG, FF, HH, AA, JJ, LL, HH, KK, LL, LL, JJ, LL, LL, KK, JJ, KK, HH, JJ, KK, AA, FF, EE, KK, JJ, DD, EE, HH, HH, BB, KK, EE, KK, AA, LL, GG, JJ, EE, BB, GG, CC, DD, FF, KK, AA, LL, GG, LL, HH, AA, EE, LL, LL, LL, GG, EE, BB, BB, JJ, AA, EE, KK, KK, EE, GG, DD, HH, AA, KK, CC, LL, HH, LL, HH, GG, BB, JJ, AA, GG, HH, CC, CC, SC, HH, JJ, GG, DD, FF, FF, EE, JJ, HH, KK, CC, GG, CC, BB, KK, LL, GG, GG, KK, HH, AA, EE, GG, CC, FF, FF, EE, JJ, HH, LL, LL, FF, FF, JJ, AA, LL, JJ, KK, HH, EE, LL, KK, CC, HH, GG, FF, JJ, LL, LL, FF, AA, KK, BB, FF, HH, EE, EE, GG, CC, KK, JJ, FF, LL, SC, HH, HH, GG, GG, EE, JJ, AA, DD, DD, FF, GG, GG, LL, KK, HH, BB, KK, LL, DD, GG, KK, AA, LL, BB, JJ, HH, CC, LL, LL, EE, JJ, BB, HH, HH, FF, JJ, EE, GG, FF, FF, KK, BB, JJ, EE, GG, JJ, CC, GG, GG, KK, DD, DD, EE, FF, LL, JJ, GG, KK, HH, FF, JJ, LL, LL, BB, KK, KK, EE, CC, CC, GG, EE, FF, HH, LL, DD, EE, DD, DD, KK, AA, EE, GG, DD, KK, GG, JJ, KK, EE, JJ, EE, KK, KK, LL, HH, JJ, FF, FF, SC, GG, HH, KK, CC, GG, AA, EE, FF, EE, FF, GG, DD, KK, LL, BB, HH, FF, CC, CC, KK, GG, JJ, SC, CC, EE, EE, GG, GG, FF, KK, LL, LL, JJ, KK, KK, EE, EE, JJ, DD, DD, FF, JJ, GG, HH, LL, JJ, BB, EE, CC, HH, AA, CC, DD, GG, LL, LL, HH, HH, BB, JJ, LL, LL, KK, EE, LL, HH, KK, DD, DD, GG, EE, LL, DD, DD, HH, LL, HH, KK, GG, JJ, AA, LL, HH, HH, FF, GG, JJ, EE, DD, JJ, FF, GG, JJ, CC, CC, EE, BB, FF, JJ, FF, HH, HH, GG, FF, EE, KK, KK, KK, HH, FF, FF, KK, LL, SC, CC, KK, HH, JJ, LL, KK, LL, HH, BB, KK, FF, EE, CC, LL, EE, HH, JJ, HH, FF, GG, EE, CC, CC, JJ, HH, BB, GG, FF, LL, KK, GG, JJ, AA, FF, FF, EE, EE, LL, LL, HH, KK, JJ, LL, EE, HH, BB, KK, LL, BB, KK, LL, CC, HH, BB, KK, JJ, JJ, LL, FF, FF, EE, JJ, AA, GG, LL, CC, FF,});
        bgReels.add(new String[]{
                LL, LL, HH, HH, CC, DD, JJ, JJ, EE, FF, JJ, FF, AA, DD, HH, FF, CC, EE, GG, HH, EE, KK, GG, LL, CC, JJ, HH, KK, EE, JJ, KK, KK, LL, EE, EE, BB, GG, CC, FF, DD, EE, KK, JJ, KK, LL, EE, CC, KK, HH, FF, KK, GG, DD, EE, EE, HH, GG, CC, CC, BB, KK, HH, LL, LL, JJ, AA, JJ, EE, HH, HH, CC, LL, LL, JJ, KK, EE, EE, HH, BB, GG, GG, EE, CC, HH, JJ, CC, GG, EE, HH, EE, HH, BB, LL, CC, DD, GG, LL, HH, FF, JJ, JJ, HH, KK, WC, KK, LL, CC, JJ, KK, FF, JJ, EE, FF, FF, BB, GG, FF, JJ, AA, HH, BB, JJ, HH, GG, CC, CC, SC, EE, HH, KK, DD, JJ, CC, AA, GG, LL, KK, LL, LL, JJ, LL, EE, EE, GG, FF, CC, JJ, KK, HH, EE, LL, KK, AA, LL, EE, EE, LL, BB, BB, DD, LL, AA, GG, DD, EE, LL, GG, EE, KK, EE, JJ, CC, CC, LL, LL, JJ, JJ, BB, KK, HH, GG, BB, KK, JJ, FF, FF, GG, JJ, HH, HH, DD, KK, BB, GG, HH, JJ, FF, FF, KK, JJ, AA, GG, KK, KK, BB, JJ, JJ, HH, DD, KK, FF, GG, JJ, JJ, HH, JJ, JJ, JJ, CC, CC, GG, DD, DD, AA, CC, LL, JJ, JJ, EE, CC, JJ, KK, HH, FF, LL, LL, FF, KK, GG, CC, CC, JJ, GG, DD, BB, FF, GG, HH, CC, LL, KK, LL, JJ, FF, KK, FF, HH, HH, EE, BB, CC, KK, LL, LL, GG, JJ, AA, GG, JJ, FF, FF, BB, HH, HH, LL, EE, JJ, HH, GG, HH, DD, KK, KK, JJ, AA, SC, KK, DD, JJ, DD, DD, GG, FF, KK, JJ, KK, HH, LL, BB, FF, GG, EE, EE, LL, HH, DD, KK, KK, HH, JJ, SC, LL, LL, HH, HH, JJ, KK, EE, FF, LL, KK, DD, CC, CC, LL, LL, GG, EE, LL, GG, HH, DD, KK, EE, GG, KK, LL, BB, KK, DD, DD, LL, EE, KK, DD, GG, CC, CC, HH, DD, JJ, GG, LL, LL, LL, DD, DD, CC, CC, KK, HH, LL, BB, CC, KK, KK, LL, FF, GG, BB, KK, LL, FF, DD, BB, AA, LL, HH, HH, JJ, GG, LL, LL, GG, KK, DD, JJ, LL, EE, EE, HH, KK, JJ, KK, AA, JJ, DD, KK, JJ, EE, HH, KK, KK, GG, GG, HH, LL, KK, CC, GG, DD, EE, LL, DD, JJ, GG, HH, HH, KK, JJ, LL, DD, LL, JJ, LL, LL, LL, KK, HH, KK, JJ, DD, LL, CC, KK, KK, DD, HH, CC, CC, KK, HH, LL, JJ, DD, SC, HH, HH, LL, EE, FF, KK, FF, DD, GG, GG, GG, BB, JJ, CC, GG, GG, LL, LL, BB, JJ, HH, KK, GG, CC, BB, KK, GG, BB, JJ, LL, LL, DD, SC, HH, JJ, CC, KK, DD, DD, FF, HH, JJ, JJ, KK, CC, DD, LL, GG, HH, BB, CC, KK, LL, LL, GG, GG, JJ, KK, KK, DD, DD, JJ, GG, HH, BB, BB, GG, SC, FF, JJ, KK, GG, EE, JJ, LL, LL, HH, CC, FF, HH, LL, JJ, FF, CC,});
        bgReels.add(new String[]{
                LL, EE, LL, KK, GG, GG, JJ, KK, HH, HH, JJ, KK, KK, CC, KK, GG, DD, HH, LL, EE, JJ, JJ, JJ, GG, DD, GG, EE, LL, HH, KK, JJ, KK, KK, HH, LL, KK, KK, JJ, CC, EE, HH, FF, GG, GG, BB, KK, DD, LL, EE, EE, GG, DD, HH, DD, HH, EE, EE, DD, DD, KK, JJ, JJ, DD, LL, GG, KK, LL, HH, KK, KK, GG, JJ, KK, GG, FF, FF, SC, EE, FF, BB, JJ, CC, DD, EE, GG, DD, HH, JJ, CC, BB, SC, GG, FF, DD, CC, JJ, KK, KK, KK, FF, GG, EE, HH, HH, FF, FF, DD, KK, HH, EE, JJ, JJ, CC, CC, AA, JJ, KK, GG, JJ, HH, BB, KK, GG, JJ, DD, DD, CC, JJ, EE, FF, CC, WC, DD, BB, JJ, KK, HH, BB, BB, KK, EE, CC, FF, JJ, FF, HH, GG, JJ, EE, KK, KK, LL, GG, EE, CC, LL, JJ, LL, FF, CC, KK, AA, EE, EE, BB, JJ, JJ, CC, FF, HH, GG, DD, DD, AA, HH, LL, KK, GG, FF, EE, SC, BB, LL, KK, KK, KK, JJ, LL, LL, FF, CC, DD, DD, LL, EE, GG, CC, FF, FF, LL, GG, JJ, BB, DD, FF, JJ, GG, KK, CC, FF, KK, JJ, DD, BB, EE, LL, JJ, LL, DD, AA, JJ, EE, CC, FF, DD, JJ, CC, CC, JJ, DD, GG, FF, KK, KK, JJ, JJ, JJ, HH, HH, EE, DD, GG, DD, CC, FF, KK, JJ, EE, DD, LL, JJ, KK, KK, WC, FF, JJ, EE, EE, HH, FF, DD, FF, FF, CC, JJ, GG, AA, KK, KK, FF, CC, BB, LL, LL, EE, EE, HH, KK, JJ, EE, CC, FF, JJ, KK, GG, EE, JJ, CC, GG, LL, LL, JJ, EE, EE, CC, JJ, KK, KK, FF, GG, LL, HH, HH, BB, EE, CC, FF, HH, HH, GG, DD, HH, LL, KK, KK, GG, JJ, CC, KK, JJ, FF, CC, HH, LL, HH, HH, HH, CC, LL, LL, LL, CC, FF, HH, JJ, LL, LL, FF, HH, CC, SC, FF, JJ, BB, GG, DD, DD, DD, EE, JJ, KK, LL, KK, FF, LL, CC, CC, JJ, DD, FF, LL, HH, BB, GG, LL, SC, KK, KK, JJ, FF, KK, EE, KK, CC, LL, FF, FF, BB, DD, EE, LL, JJ, JJ, HH, LL, CC, GG, HH, HH, DD, EE, FF, KK, AA, KK, LL, CC, LL, GG, GG, FF, HH, HH, JJ, JJ, EE, GG, FF, DD, GG, CC, CC, HH, LL, HH, JJ, EE, CC, FF, LL, LL, LL, GG, GG, BB, BB, JJ, JJ, EE, GG, AA, LL, GG, DD, FF, SC, CC, LL, DD, DD, LL, EE, BB, GG, CC, JJ, JJ, LL, LL, JJ, LL, LL, KK, CC, JJ, LL, LL, GG, LL, DD, DD, LL, KK, LL, LL, GG, EE, LL, JJ, DD, GG, FF, JJ, JJ, KK, GG, LL, CC, CC, LL, GG, DD, FF, JJ, JJ, KK, EE, LL, FF, GG, DD, CC, AA, LL, LL, BB, DD, LL, LL, LL, JJ, JJ, GG, GG, HH, CC, CC, SC, JJ, EE, BB, BB, LL, LL, JJ, GG, FF, LL, LL, KK, BB, LL, GG, EE, EE, LL, FF, JJ, KK, LL,});
        bgReels.add(new String[]{
                GG, LL, LL, BB, FF, EE, BB, CC, JJ, LL, LL, JJ, JJ, KK, AA, HH, FF, DD, JJ, GG, LL, LL, EE, HH, FF, BB, GG, JJ, EE, KK, JJ, JJ, AA, DD, DD, KK, BB, FF, EE, GG, DD, LL, KK, KK, AA, BB, FF, JJ, BB, SC, KK, HH, EE, JJ, DD, GG, HH, HH, AA, BB, HH, GG, FF, HH, JJ, DD, CC, DD, LL, DD, KK, HH, JJ, SC, JJ, DD, GG, GG, JJ, DD, JJ, BB, FF, DD, KK, FF, HH, HH, KK, BB, AA, EE, JJ, KK, EE, GG, CC, CC, LL, EE, KK, GG, HH, EE, JJ, JJ, LL, KK, JJ, CC, HH, KK, KK, LL, JJ, HH, CC, SC, HH, GG, HH, KK, GG, HH, FF, FF, EE, HH, GG, LL, EE, KK, FF, BB, HH, KK, LL, KK, AA, JJ, GG, EE, BB, HH, CC, DD, HH, KK, GG, HH, JJ, JJ, KK, GG, EE, AA, DD, GG, JJ, EE, BB, BB, HH, LL, LL, HH, HH, JJ, LL, DD, HH, FF, HH, LL, LL, JJ, KK, KK, JJ, GG, HH, AA, CC, KK, CC, CC, HH, DD, GG, JJ, LL, FF, JJ, KK, GG, KK, LL, CC, JJ, LL, BB, HH, KK, JJ, JJ, HH, KK, KK, GG, JJ, CC, HH, FF, HH, CC, DD, LL, LL, FF, FF, HH, LL, EE, JJ, KK, HH, AA, CC, HH, FF, KK, JJ, LL, CC, LL, HH, LL, HH, KK, GG, FF, KK, KK, EE, JJ, CC, HH, KK, CC, JJ, LL, HH, KK, CC, JJ, FF, LL, LL, DD, KK, KK, JJ, JJ, EE, LL, KK, BB, HH, KK, JJ, EE, DD, LL, LL, GG, GG, KK, CC, LL, GG, AA, LL, KK, KK, LL, HH, HH, EE, KK, FF, BB, LL, GG, GG, JJ, JJ, GG, CC, JJ, KK, HH, DD, DD, JJ, GG, LL, JJ, JJ, GG, HH, LL, GG, GG, KK, KK, KK, LL, LL, CC, GG, JJ, JJ, FF, KK, DD, DD, CC, AA, LL, HH, GG, HH, LL, DD, HH, GG, JJ, LL, JJ, GG, EE, JJ, HH, BB, KK, FF, FF, LL, EE, CC, KK, HH, CC, JJ, GG, EE, FF, HH, FF, JJ, GG, JJ, BB, KK, CC, CC, CC, WC, HH, JJ, KK, GG, CC, EE, EE, LL, LL, FF, KK, FF, HH, HH, KK, LL, JJ, EE, KK, SC, DD, DD, GG, JJ, LL, LL, BB, BB, EE, JJ, KK, LL, LL, DD, GG, HH, HH, GG, KK, LL, FF, HH, AA, EE, GG, LL, KK, HH, GG, EE, BB, KK, HH, EE, DD, KK, LL, AA, HH, JJ, GG, KK, KK, KK, SC, JJ, KK, LL, LL, GG, FF, FF, JJ, GG, CC, KK, EE, AA, GG, CC, EE, CC, KK, JJ, CC, EE, HH, HH, HH, KK, JJ, FF, FF, CC, CC, CC, LL, KK, AA, GG, HH, FF, KK, JJ, HH, DD, DD, EE, KK, EE, BB, GG, KK, JJ, JJ, EE, HH, BB, GG, JJ, DD, JJ, FF, HH, HH, JJ, GG, AA, FF, FF, EE, HH, HH, HH, SC, HH, KK, EE, EE, KK, HH, BB, KK, AA, HH, GG, CC, KK, LL, SC, EE, CC, HH, FF, FF, AA, GG, GG, JJ, HH, CC, FF,
        });


        gameReels.add(bgReels);
//        gameReels.add(topReel);
//        gameReels.add(fgReels);
//        gameReels.add(fgTopReel);

        return gameReels;
    }

    public WeightedPrizeData createReel1And2Sym() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(27, 2))
                .addWeightedConfig(new WeightedPrizeConfig(27, 3))
                .addWeightedConfig(new WeightedPrizeConfig(28, 4))
                .addWeightedConfig(new WeightedPrizeConfig(11, 5))
                .addWeightedConfig(new WeightedPrizeConfig(4, 6))
                .addWeightedConfig(new WeightedPrizeConfig(3, 7));
    }

    public WeightedPrizeData createRee3And4Sym() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(26, 2))
                .addWeightedConfig(new WeightedPrizeConfig(24, 3))
                .addWeightedConfig(new WeightedPrizeConfig(19, 4))
                .addWeightedConfig(new WeightedPrizeConfig(15, 5))
                .addWeightedConfig(new WeightedPrizeConfig(10, 6))
                .addWeightedConfig(new WeightedPrizeConfig(6, 7));
    }

    public WeightedPrizeData createRee5And6Sym() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(28, 2))
                .addWeightedConfig(new WeightedPrizeConfig(24, 3))
                .addWeightedConfig(new WeightedPrizeConfig(19, 4))
                .addWeightedConfig(new WeightedPrizeConfig(13, 5))
                .addWeightedConfig(new WeightedPrizeConfig(10, 6))
                .addWeightedConfig(new WeightedPrizeConfig(6, 7));
    }

    public WeightedPrizeData createReel1Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(27, 3))
                .addWeightedConfig(new WeightedPrizeConfig(15, 4))
                .addWeightedConfig(new WeightedPrizeConfig(13, 5))
                .addWeightedConfig(new WeightedPrizeConfig(7, 6))
                .addWeightedConfig(new WeightedPrizeConfig(3, 7));
    }

    public WeightedPrizeData createReelSym2Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(28, 3))
                .addWeightedConfig(new WeightedPrizeConfig(14, 4))
                .addWeightedConfig(new WeightedPrizeConfig(12, 5))
                .addWeightedConfig(new WeightedPrizeConfig(7, 6))
                .addWeightedConfig(new WeightedPrizeConfig(4, 7));
    }

    public WeightedPrizeData createReelSym3Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(29, 3))
                .addWeightedConfig(new WeightedPrizeConfig(15, 4))
                .addWeightedConfig(new WeightedPrizeConfig(10, 5))
                .addWeightedConfig(new WeightedPrizeConfig(8, 6))
                .addWeightedConfig(new WeightedPrizeConfig(3, 7));
    }

    public WeightedPrizeData createReelSym4Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(27, 3))
                .addWeightedConfig(new WeightedPrizeConfig(17, 4))
                .addWeightedConfig(new WeightedPrizeConfig(11, 5))
                .addWeightedConfig(new WeightedPrizeConfig(6, 6))
                .addWeightedConfig(new WeightedPrizeConfig(4, 7));
    }

    public WeightedPrizeData getNumbersToMatch() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(400, 0))
                .addWeightedConfig(new WeightedPrizeConfig(300, 1))
                .addWeightedConfig(new WeightedPrizeConfig(200, 2))
                .addWeightedConfig(new WeightedPrizeConfig(100, 3));
    }

    public WeightedPrizeData getNumberTypeToMatch() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(50, 0)) // normal
                .addWeightedConfig(new WeightedPrizeConfig(17, 1)) // Minor
                .addWeightedConfig(new WeightedPrizeConfig(17, 2)) // Major
                .addWeightedConfig(new WeightedPrizeConfig(16, 3)); // Grand
    }

    public WeightedPrizeData createWheelPrizes() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(70, 25))
                .addWeightedConfig(new WeightedPrizeConfig(90, 12))
                .addWeightedConfig(new WeightedPrizeConfig(40, 100))
                .addWeightedConfig(new WeightedPrizeConfig(85, 20))
                .addWeightedConfig(new WeightedPrizeConfig(100, 12))
                .addWeightedConfig(new WeightedPrizeConfig(50, 50))
                .addWeightedConfig(new WeightedPrizeConfig(100, 15))
                .addWeightedConfig(new WeightedPrizeConfig(60, 30))
                .addWeightedConfig(new WeightedPrizeConfig(85, 20))
                .addWeightedConfig(new WeightedPrizeConfig(50, 0)) // 0 means second wheel will be used
                .addWeightedConfig(new WeightedPrizeConfig(80, 15))
                .addWeightedConfig(new WeightedPrizeConfig(60, 30))
                .addWeightedConfig(new WeightedPrizeConfig(80, 20))
                .addWeightedConfig(new WeightedPrizeConfig(50, 50));
    }

    public WeightedPrizeData createJackpotPrizes() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(100, 1))
                .addWeightedConfig(new WeightedPrizeConfig(25, 3))
                .addWeightedConfig(new WeightedPrizeConfig(100, 1))
                .addWeightedConfig(new WeightedPrizeConfig(50, 2))
                .addWeightedConfig(new WeightedPrizeConfig(100, 1))
                .addWeightedConfig(new WeightedPrizeConfig(50, 2))
                .addWeightedConfig(new WeightedPrizeConfig(100, 1))
                .addWeightedConfig(new WeightedPrizeConfig(50, 2))
                .addWeightedConfig(new WeightedPrizeConfig(100, 1))
                .addWeightedConfig(new WeightedPrizeConfig(25, 3))
                .addWeightedConfig(new WeightedPrizeConfig(100, 1))
                .addWeightedConfig(new WeightedPrizeConfig(50, 2))
                .addWeightedConfig(new WeightedPrizeConfig(100, 1))
                .addWeightedConfig(new WeightedPrizeConfig(50, 2));
    }

    public WeightedPrizeData createTicketPrizes() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(200, 10))
                .addWeightedConfig(new WeightedPrizeConfig(200, 15))
                .addWeightedConfig(new WeightedPrizeConfig(150, 20))
                .addWeightedConfig(new WeightedPrizeConfig(120, 25))
                .addWeightedConfig(new WeightedPrizeConfig(100, 30))
                .addWeightedConfig(new WeightedPrizeConfig(70, 40))
                .addWeightedConfig(new WeightedPrizeConfig(50, 50))
                .addWeightedConfig(new WeightedPrizeConfig(40, 60))
                .addWeightedConfig(new WeightedPrizeConfig(30, 75))
                .addWeightedConfig(new WeightedPrizeConfig(20, 80))
                .addWeightedConfig(new WeightedPrizeConfig(20, 100))
                ;
    }

    private List<List<Integer>> getWinLines() {

        return List.of(
                List.of(20, 16, 12, 8, 4),
                List.of(0, 6, 12, 18, 24),

                List.of(0, 1, 2, 3, 4),
                List.of(5, 6, 7, 8, 9),
                List.of(10, 11, 12, 13, 14),
                List.of(15, 16, 17, 18, 19),
                List.of(20, 21, 22, 23, 24),

                List.of(0, 5, 10, 15, 20),
                List.of(1, 6, 11, 16, 21),
                List.of(2, 7, 12, 17, 22),
                List.of(3, 8, 13, 18, 23),
                List.of(4, 9, 14, 19, 24)
        );


    }
}
