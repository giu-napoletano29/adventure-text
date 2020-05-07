package di.uniba.map.game.parser;

import di.uniba.map.game.GameDescription;
import di.uniba.map.game.type.Command;

import java.util.Scanner;

public class Parser {
    public void commandEnter(){
        //Scanner scanner = new Scanner(System.in);
    }

    public ParserOutput parseCmd(String command, GameDescription game){
        ParserOutput cmd;
        Command token = new Command(null, null);
        String[] key = command.split(" ");

        for(int i = 0; i<game.getCommands().size(); i++){
            if(key[0].contains(game.getCommands().get(i).getName())){
                token = new Command(game.getCommands().get(i).getType(),game.getCommands().get(i).getName());
                break;
            }
        }

        if(key.length > 1){
            cmd = new ParserOutput(token, key[1]);
        }else{
            cmd = new ParserOutput(token, null);
        }
        return cmd;
    }
}
