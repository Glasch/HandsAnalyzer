package glasch.handsanalyzer;

import glasch.handsanalyzer.decisiontree.DecisionTree;
import glasch.handsanalyzer.decisiontree.DecisionTreeBuilder;
import glasch.handsanalyzer.model.Game;

import java.io.IOException;

/*
 * Author: glaschenko
 * Created: 29.09.2018
 */
public class Main {

    public static void main(String[] args) throws IOException {
        HandParser parser = new HandParser("src/hand", "A.Glasch");
        Game game = parser.parse();
        System.out.println(game);
        DecisionTreeBuilder builder = new DecisionTreeBuilder();
        builder.build(game);
    }
}
