package glasch.handsanalyzer;

/**
 * Copyright (c) Anton on 27.09.2018.
 */
public class Player {
    private String nickname;
    private Float stake;

    public Player(String nickname, Float stake) {
        this.nickname = nickname;
        this.stake = stake;
    }

    public String getNickname() {
        return nickname;
    }

    public Float getStake() {
        return stake;
    }

    @Override
    public String toString() {
        return nickname + ", stake: " + stake;
    }
}
