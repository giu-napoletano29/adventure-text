package di.uniba.map.game.engine;

import di.uniba.map.game.parser.Parser;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;
import di.uniba.map.game.language.LanguageSelector;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Engine {

    private final GameDescription game;
    private final Parser cmd;
    private final Utils u = new Utils();

    public Engine(Object obj, LanguageSelector language) {
        this.game = (GameDescription) obj;
        try {
            this.game.init(language);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        cmd = new Parser();
    }

    protected void begin(){
        System.out.println("--------------------------------------------------------------");
        System.out.println("    Game Engine - by Giuseppe Napoletano & Domenico Sarcina   ");
        System.out.println("--------------------------------------------------------------");
    }

    public void run(LanguageSelector language) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        u.printRoom(game);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            ParserOutput command = cmd.parseCmd(scanner.nextLine(), game);
            if(command.getCommand() != null && command.getCommand().getType() == CommandType.END){
                System.out.println(language.getDocument().getElementsByTagName("not_good_decision").item(0).getTextContent());
                break;
            }else{
                u.move(command, game, language);
                //u.printRoom(game); //Inserire enum per capire quale messaggio stampare in base a movimento o azione
            }
            if(game.isWin()){
                System.out.println(language.getDocument().getElementsByTagName("win").item(0).getTextContent());
                break;
            }
            else if(game.isLose()){
                System.out.println(language.getDocument().getElementsByTagName("lose").item(0).getTextContent());
                break;
            }

        }
    }

    public static void engine(Engine engine, LanguageSelector language) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        engine.begin();
        engine.run(language);
    }

}
