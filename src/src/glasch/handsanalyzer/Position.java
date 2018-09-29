package glasch.handsanalyzer;

/**
 * Copyright (c) Anton on 27.09.2018.
 */
public enum Position {
    BTN(0), SB(1), BB(2), EP(3), MP(4), CO(5);

    private int position;

    Position(int position) {
        this.position = position;
    }

    static Position findPosition(Integer buttonSeat, Integer seat) {
        Integer position = seat - buttonSeat;
        if(position < 0) position = 6 + position;

        for (Position each : values()) {
            if(each.position == position) return each;
        }
        throw new IllegalStateException("Unexpected position: " + position);
    }
}
