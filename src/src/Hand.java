import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    Hand parse() throws IOException {
        Hand hand = new Hand();

        Stream <String> streamFromFiles = Files.lines(Paths.get("src/hand"));
        streamFromFiles
                .forEach(s -> {
                            String[] line = s.split(" ");
                            if (s.startsWith("PokerStars Hand")) {
                                hand.id = line[2].substring(1, line.length);
                                System.out.println(hand.id);
                                hand.limit = Float.parseFloat(line[7].substring(8, 12));
                                System.out.println(hand.limit);
                                hand.date = line[10] + " " + line[11] + " " + line[12];
                                System.out.println(hand.date);
                            }else
                            if (s.endsWith("is the button")) {
                                hand.size = Integer.parseInt(line[3].substring(0,1));
                                System.out.println(hand.size);
                                hand.buttonSeat = Integer.parseInt(line[5].substring(1,2));
                                System.out.println(hand.buttonSeat);
                            }else
                            if (s.endsWith("in chips)")){
                                players.put(Position.findPosition(hand.buttonSeat, Integer.parseInt(line[1].substring(0,1))), new Player(line[2], Float.parseFloat(validateStake(line))));
                            }


                        }
                );
        System.out.println(players);
        return hand;
    }

    private String validateStake(String[] line)  {
        if (line[3].length() < 6 ){
            return line[3].substring(2,4);
        }
        return line[3].substring(2,7);
    }


    public static void main(String[] args) throws IOException {
        Hand hand = new Hand();
        hand.parse();
    }

}
