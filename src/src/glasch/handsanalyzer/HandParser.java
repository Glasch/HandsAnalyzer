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
    private static final String HAND_LINE_START = "PokerStars Hand";
    private static final String TABLE_LINE_END = "is the button";
    private static final String PLAYER_LINE_END = "in chips)";
    private static final String LINES_SEPARATOR = " ";
    private static final String DOLLAR = "$";
    private String path;

    HandParser(String path) {
        this.path = path;
    }

    Hand parse() throws IOException {
        Hand hand = new Hand();

        Stream<String> streamFromFiles = Files.lines(Paths.get(path));
        streamFromFiles
                .forEach(s -> {
                            String[] line = s.split(LINES_SEPARATOR);
                            if (s.startsWith(HAND_LINE_START)) {
                                parseHandInfo(hand, line);
                            }else
                            if (s.endsWith(TABLE_LINE_END)) {
                                parseTableInfo(hand, line);
                            }else
                            if (s.endsWith(PLAYER_LINE_END)){
                                parsePlayerPosition(hand, line);
                            }
                        }
                );
        return hand;
    }

    private void parseTableInfo(Hand hand, String[] line) {
        hand.setSize(Integer.parseInt(line[3].substring(0,1)));
        hand.setButtonSeat(Integer.parseInt(line[5].substring(1,2)));
    }

    private void parseHandInfo(Hand hand, String[] line) {
        hand.setId(line[2].substring(1, line.length));
        hand.setLimit(parseMoney(line[7], "/$"));
        hand.setDate(line[10] + " " + line[11] + " " + line[12]); //todo ag parse using SimpleDateFormat
    }

    private void parsePlayerPosition(Hand hand, String[] line) {
        int seat = Integer.parseInt(line[1].substring(0, 1));
        Integer position = seat - hand.getButtonSeat();
        if(position < 0) position = hand.getSize() + position;
        hand.getPlayers().put(Position.fromSeatNumber(position), parsePlayer(line));
    }

    private Player parsePlayer(String[] line) {
        return new Player(line[2], parseMoney(line[3]));
    }

    private Float parseMoney(String line) {
        return parseMoney(line, DOLLAR);
    }

    private Float parseMoney(String line, String startString){ //we expect that money ends with a space
        int start = line.indexOf(startString);
        if(start == -1) throw new IllegalStateException("Currency sign not found in line: " + line);
        return Float.parseFloat(line.substring(start+startString.length()));
    }

}
