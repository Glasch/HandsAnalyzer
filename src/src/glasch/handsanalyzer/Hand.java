package glasch.handsanalyzer;

import java.util.*;

/**
 * Copyright (c) Anton on 12.09.2018.
 */
public class Hand {
    private String id;
    private Float limit;
    private String date;
    private Integer size;
    private Integer buttonSeat;
    private Map <Position, Player> players = new HashMap <>();

    private Map <Player, Float> postingRound = new HashMap <>();
    private FirstRound firstRoundAction;

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

    public Map <Position, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map <Position, Player> players) {
        this.players = players;
    }

    public Map <Player, Float> getPostingRound() {
        return postingRound;
    }

    public void setPostingRound(Map <Player, Float> postingRound) {
        this.postingRound = postingRound;
    }

    public FirstRound getFirstRoundAction() {
        return firstRoundAction;
    }

    public void setFirstRoundAction(FirstRound firstRoundAction) {
        this.firstRoundAction = firstRoundAction;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Hand id=" + id +
                "\nlimit=" + limit +
                "\ndate='" + date + '\'' +
                "\nsize=" + size +
                "\nbuttonSeat=" + buttonSeat +
                "\nPlayers:");
        List <Position> positions = new ArrayList <>(players.keySet());
        Collections.sort(positions);

        for (Position position : positions) {
            res.append("\n").append(position).append(" ").append(players.get(position));
        }

        return res.toString();

    }
}
