package glasch.handsanalyzer.model;

/*
 * Author: glaschenko
 * Created: 11.10.2018
 */
public class PlayerAction {
    Player player;
    ActionType actionType;
    Float sizing;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Float getSizing() {
        return sizing;
    }

    public void setSizing(Float sizing) {
        this.sizing = sizing;
    }

    @Override
    public String toString() {
        return "Act: " + player.getNickname() + " " + actionType + " " + (sizing == null ? "" : "$" + sizing);
    }
}
