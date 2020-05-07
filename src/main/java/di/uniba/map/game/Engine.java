package di.uniba.map.game;

import di.uniba.map.game.GameDescription;
import di.uniba.map.game.games.GothicGame;
import di.uniba.map.game.parser.Parser;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;

import java.util.Scanner;

public class Engine {

    private final GameDescription game;
    private final Parser cmd;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        cmd = new Parser();
    }

    protected void begin(){
        System.out.println("-------------------------------------------");
        System.out.println("NOME DEL GIOCO QUI - by Giuseppe Napoletano");
        System.out.println("-------------------------------------------");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            ParserOutput command = cmd.parseCmd(scanner.nextLine(), game);
            if(command.getCommand() != null && command.getCommand().getType() == CommandType.END){
                System.out.println("Chiusura....");
                break;
            }else{

            }
        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine(new GothicGame());
        engine.begin();
        engine.run();
    }
}
