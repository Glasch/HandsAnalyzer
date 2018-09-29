package glasch.handsanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Copyright (c) Anton on 12.09.2018.
 */
public class Hand {
    // Room + Limit
    private String id;
    private Float limit;
    private String date;
    // Table + BTN
    private Integer size;
    private Integer buttonSeat;
    //  Players and Stakes
    private   Map<Position,Player> players = new HashMap <>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getLimit() {
        return limit;
    }

    public void setLimit(Float limit) {
        this.limit = limit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getButtonSeat() {
        return buttonSeat;
    }

    public void setButtonSeat(Integer buttonSeat) {
        this.buttonSeat = buttonSeat;
    }

    public Map<Position, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Position, Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Hand id=" + id +
                "\nlimit=" + limit +
                "\ndate='" + date + '\'' +
                "\nsize=" + size +
                "\nbuttonSeat=" + buttonSeat +
                "\nPlayers:");
        for (Position position : players.keySet()) {
            res.append("\n").append(position).append(" ").append(players.get(position));
        }
        return res.toString();
    }
}
