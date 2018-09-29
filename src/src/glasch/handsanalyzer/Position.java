package glasch.handsanalyzer;

/**
 * Copyright (c) Anton on 27.09.2018.
 */
public enum Position {
    EP, MP, CO, BTN, SB, BB;

    static Position findPosition(Integer buttonSeat, Integer seat) {
        Integer diff = buttonSeat - seat;
        if (buttonSeat == seat) return BTN;
        else if (Math.abs(diff) == 1) {
            if (diff > 0) return CO;
            else return SB;
        } else if (Math.abs(diff) == 2) {
            if (diff > 0) return MP;
            else return BB;
        } else if (Math.abs(diff) == 3) {
            return EP;
        } else if (Math.abs(diff) == 4) {
            if (diff > 0) return BB;
            else return MP;
        } else if (Math.abs(diff) == 5) {
            if (diff > 0) return SB;
            else return CO;
        } else return null;
    }
}
