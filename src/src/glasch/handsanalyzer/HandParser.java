package glasch.handsanalyzer;

import glasch.handsanalyzer.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
    private static final String POSTINGSB = "posts small";
    private static final String POSTINGBB = "posts big";
    private static final String DEALT = "Dealt";
    private String lineBeforeHeroFirstRound;
    private String heroReactFirstRound;
    private String actionAfterHeroFirstRound;
    private Map<String, Player> nick2player = new HashMap<>();
    private  String hero;
    private String path;




    HandParser(String path, String hero) {
        this.path = path;
        this.hero = hero;
    }

    Game parse() throws IOException {
        Game game = new Game(hero);

        Stream<String> streamFromFiles = Files.lines(Paths.get(path));
        String[] fileLines = streamFromFiles.toArray(String[]::new);
        for (String fileLine : fileLines) {
            if (fileLine.contains("*** FLOP ***")) {
                break;
            }
            processFileLine(game, fileLine);
        }
        game.getPlayers().sort(Comparator.comparing(Player::getPosition));
        return game;
    }

    private void processFileLine(Game game, String s) {
        String[] line = s.split(LINES_SEPARATOR);

        if (s.startsWith(HAND_LINE_START)) {
            parseHandInfo(game, line);
        } else if (s.endsWith(TABLE_LINE_END)) {
            parseTableInfo(game, line);
        } else if (s.endsWith(PLAYER_LINE_END)) {
            parsePlayerPosition(game, line);

        }
        if (s.startsWith(DEALT)) {
            //TODO Parse HoleCards
        }
        if (nick2player.keySet().contains(line[0])) {
            parsePlayerAction(game, line);
        }
    }

    private void parsePlayerAction(Game game, String[] line) {
        PlayerAction playerAction = new PlayerAction();
        playerAction.setPlayer(nick2player.get(line[0]));
        String sizing = line[line.length - 1];
        if (sizing.startsWith("$")) {
            playerAction.setSizing(Float.parseFloat(sizing.substring(1, sizing.length())));
        }
        playerAction.setActionType(ActionType.fromString(line[1]));
        game.getPlayerActions().add(playerAction);
    }


    private void parseTableInfo(Game game, String[] line) {
        game.setSize(Integer.parseInt(line[3].substring(0, 1)));
        game.setButtonSeat(Integer.parseInt(line[5].substring(1, 2)));
    }

    private void parseHandInfo(Game game, String[] line) {
        game.setId(line[2].substring(1, line.length));
        game.setLimit(parseMoney(line[7], "/$"));
        game.setDate(line[10] + " " + line[11] + " " + line[12]); //todo ag parse using SimpleDateFormat
    }

    private void parsePlayerPosition(Game game, String[] line) {
        int seat = Integer.parseInt(line[1].substring(0, 1));
        Integer position = seat - game.getButtonSeat();
        if (position < 0) position = game.getSize() + position;
        Player player = parsePlayer(line,hero);
        player.setPosition(Position.fromSeatNumber(position));
        game.getPlayers().add(player);
        nick2player.put(player.getNickname() + ":", player);
    }

    private Player parsePlayer(String[] line, String hero) {

        String nickname = line[2];

        return new Player(nickname, parseMoney(line[3]), hero.equals(nickname));
    }

    private Float parseMoney(String line) {
        return parseMoney(line, DOLLAR);
    }

    private Float parseMoney(String line, String startString) { //we expect that money ends with a space
        int start = line.indexOf(startString);
        if (start == -1) throw new IllegalStateException("Currency sign not found in line: " + line);
        return Float.parseFloat(line.substring(start + startString.length()));
    }

}
