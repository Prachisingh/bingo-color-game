package slotmachine.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OfAKindWins {
    Map<Integer, BigDecimal> winningMap = new HashMap<>();
    Map<Integer, Integer> occuranceMap = new HashMap<>();
    public void merge(OfAKindWins other){
        for (var entry : other.occuranceMap.entrySet()) {
            if (this.occuranceMap.containsKey(entry.getKey())) {
                this.occuranceMap.put(entry.getKey(), this.occuranceMap.get(entry.getKey()) + entry.getValue());
            } else {
                this.occuranceMap.put(entry.getKey(), entry.getValue());
            }
        }

        for (var entry : other.winningMap.entrySet()) {
            if (this.winningMap.containsKey(entry.getKey())) {
                this.winningMap.put(entry.getKey(), this.winningMap.get(entry.getKey()).add(entry.getValue()));
            } else {
                this.winningMap.put(entry.getKey(), entry.getValue());
            }
        }
    }
    public Map<Integer, BigDecimal> getWinningMap() {
        return winningMap;
    }

    public void setWinningMap(Map<Integer, BigDecimal> winningMap) {
        this.winningMap = winningMap;
    }

    public Map<Integer, Integer> getOccuranceMap() {
        return occuranceMap;
    }

    public void setOccuranceMap(Map<Integer, Integer> occuranceMap) {
        this.occuranceMap = occuranceMap;
    }
}
