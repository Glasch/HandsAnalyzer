package glasch.handsanalyzer.model;

import glasch.handsanalyzer.decisiontree.FirstRound;

import java.util.*;

/**
 * Copyright (c) Anton on 12.09.2018.
 */
public class Game {
    private String id;
    private Float limit;
    private String date;
    private Integer size;
    private Integer buttonSeat;
    private List<Player> players = new ArrayList<>();
    private List<PlayerAction> playerActions = new ArrayList<>();
    private String hero;

    public Game(String hero) {
        this.hero = hero;
    }

    public List<PlayerAction> getPlayerActions() {
        return playerActions;
    }

    public void setPlayerActions(List<PlayerAction> playerActions) {
        this.playerActions = playerActions;
    }

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


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Hand id=" + id +
                "\nlimit=" + limit +
                "\ndate='" + date + '\'' +
                "\nsize=" + size +
                "\nbuttonSeat=" + buttonSeat +
                "\nPlayers: ");

        for (Player player : players) {
            res.append("\n").append(player);
        }
        res.append("\nActions :");
        for (PlayerAction playerAction : playerActions) {
            res.append("\n").append(playerAction);
        }

        return res.toString();

    }
}
