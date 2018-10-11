package glasch.handsanalyzer.model;

/**
 * Copyright (c) Anton on 27.09.2018.
 */
public enum Position {
    BTN(0), SB(1), BB(2), EP(3), MP(4), CO(5);

    private int position;

    Position(int position) {
        this.position = position;
    }

    public static Position fromSeatNumber(Integer seatNumber) {
        for (Position each : values()) {
            if(each.position == seatNumber) return each;
        }
        throw new IllegalStateException("Unexpected seatNumber: " + seatNumber);
    }
}
