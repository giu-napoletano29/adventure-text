package di.uniba.map.game;

import di.uniba.map.game.GameDescription;
import di.uniba.map.game.games.GothicGame;
import di.uniba.map.game.parser.Parser;

import java.util.Scanner;

public class Engine {

    private final GameDescription game;
    private final Parser cmd;
    private String command;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        cmd = new Parser();
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            cmd.parseCmd(scanner.nextLine(), game);
        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine(new GothicGame());
        engine.run();
    }
}
