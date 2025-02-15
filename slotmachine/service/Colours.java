package slotmachine.service;

import java.util.ArrayList;
import java.util.List;

public class Colours {

    List<Integer> coloursAtTop = new ArrayList<>();
    int colourAtBottom = -1;

    public List<Integer> getColoursAtTop() {
        return coloursAtTop;
    }

    public void setColoursAtTop(List<Integer> coloursAtTop) {
        this.coloursAtTop = coloursAtTop;
    }

    public int getColourAtBottom() {
        return colourAtBottom;
    }

    public void setColourAtBottom(int colourAtBottom) {
        this.colourAtBottom = colourAtBottom;
    }

    @Override
    public String toString() {
        return "Colours{" +
                "coloursAtTop=" + coloursAtTop +
                ", colourAtBottom=" + colourAtBottom +
                '}';
    }
}
