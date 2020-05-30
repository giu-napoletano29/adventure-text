package di.uniba.map.game;

import di.uniba.map.game.parser.Parser;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

    public Engine(Object obj) {
        this.game = (GameDescription) obj;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        cmd = new Parser();
    }

    protected void begin(){
        System.out.println("-------------------------------------------");
        System.out.println("    Game Engine - by Giuseppe Napoletano   ");
        System.out.println("-------------------------------------------");
    }

    public void run() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
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
            else if(game.isLose()){
                System.out.println("Sei morto!");
                break;
            }

        }
    }

    public static void engine(Engine engine) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //Engine engine = new Engine(new GothicGame());
        //Engine engine = new Engine(new GothicGame2());
        engine.begin();
        engine.run();
    }
}
