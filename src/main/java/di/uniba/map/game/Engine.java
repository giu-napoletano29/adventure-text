package di.uniba.map.game;

import di.uniba.map.game.GameDescription;
import di.uniba.map.game.games.GothicGame;
import di.uniba.map.game.parser.Parser;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;

import java.io.IOException;
import java.util.Scanner;

public class Engine {

    private final GameDescription game;
    private final Parser cmd;
    private final Utils u = new Utils();

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

    public void run() throws IOException {
        u.printRoom(game);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            ParserOutput command = cmd.parseCmd(scanner.nextLine(), game);
            if(command.getCommand() != null && command.getCommand().getType() == CommandType.END){
                System.out.println("Forse una decisione poco saggia, ma è pur sempre una decisione!");
                break;
            }else{
                u.move(command, game);
                //u.printRoom(game); //Inserire enum per capire quale messaggio stampare in base a movimento o azione
            }
            if(game.isWin()){
                System.out.println("Hai vinto!");
                break;
            }
            if(game.isLose()){
                System.out.println("Sei morto!");
                break;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        Engine engine = new Engine(new GothicGame());
        engine.begin();
        engine.run();
    }
}
