package glasch.handsanalyzer.model;

/**
 * Copyright (c) Anton on 27.09.2018.
 */
public class Player {
    private String nickname;
    private Float stake;
    private Position position;
    private boolean isHero;

    public Player(String nickname, Float stake, boolean isHero) {
        this.nickname = nickname;
        this.stake = stake;
        this.isHero = isHero;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public String getNickname() {
        return nickname;
    }

    public Float getStake() {
        return stake;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return  (isHero ? "HERO " : "") +nickname + ", " + position + ", stake: " + stake;
    }
}
