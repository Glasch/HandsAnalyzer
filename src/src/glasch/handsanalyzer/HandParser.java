package glasch.handsanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
 * Author: glaschenko
 * Created: 29.09.2018
 */
public class HandParser {
    private String path;

    public HandParser(String path) {
        this.path = path;
    }

    Hand parse() throws IOException {
        Hand hand = new Hand();

        Stream<String> streamFromFiles = Files.lines(Paths.get(path));
        streamFromFiles
                .forEach(s -> {
                            String[] line = s.split(" ");
                            if (s.startsWith("PokerStars Hand")) {
                                hand.setId(line[2].substring(1, line.length));
                                System.out.println(hand.getId());
                                hand.setLimit(Float.parseFloat(line[7].substring(8, 12)));
                                System.out.println(hand.getLimit());
                                hand.setDate(line[10] + " " + line[11] + " " + line[12]);
                            }else
                            if (s.endsWith("is the button")) {
                                hand.setSize(Integer.parseInt(line[3].substring(0,1)));
                                hand.setButtonSeat(Integer.parseInt(line[5].substring(1,2)));
                            }else
                            if (s.endsWith("in chips)")){
                                hand.getPlayers().put(
                                        Position.findPosition(hand.getButtonSeat(),
                                                Integer.parseInt(line[1].substring(0,1))), new Player(line[2],
                                                Float.parseFloat(validateStake(line))));
                            }
                        }
                );
        return hand;
    }

    private String validateStake(String[] line)  {
        if (line[3].length() < 6 ){
            return line[3].substring(2,4);
        }
        return line[3].substring(2,7);
    }

}
