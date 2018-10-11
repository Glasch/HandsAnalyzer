package glasch.handsanalyzer;

import java.io.IOException;

/*
 * Author: glaschenko
 * Created: 29.09.2018
 */
public class Main {

    public static void main(String[] args) throws IOException {
        HandParser parser = new HandParser("src/hand");
        Hand hand = parser.parse();
        System.out.println(hand);

    }
}
