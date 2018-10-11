package glasch.handsanalyzer.decisiontree;

import glasch.handsanalyzer.model.Game;
import glasch.handsanalyzer.model.PlayerAction;
import glasch.handsanalyzer.model.Position;

import java.util.List;

/*
 * Author: glaschenko
 * Created: 11.10.2018
 */
public class DecisionTreeBuilder {
    private Game game;

   public DecisionTree build(Game game) {
        this.game = game;
        DecisionTree decisionTree = new DecisionTree();
        System.out.println(buildActionsLine(2, true));
        return decisionTree;
    }

   private String buildActionsLine(int indexFrom, boolean toHero) {
        StringBuilder res = new StringBuilder();
        List<PlayerAction> actions = game.getPlayerActions();
        PlayerAction action = actions.get(indexFrom);
        while (!needStop(toHero, action)) {
            res.append(action.getActionType().toActionChar());
            action = actions.get(++indexFrom);
        }
        return res.toString();
    }

    private boolean needStop(boolean toHero, PlayerAction action) {
        if (toHero && action.getPlayer().isHero()) return true;
        if (!toHero && action.getPlayer().getPosition().equals(Position.BB)) return true;
        return false;
    }


}
