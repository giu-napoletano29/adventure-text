package di.uniba.map.game.parser;

import di.uniba.map.game.GameDescription;
import di.uniba.map.game.type.Command;
import di.uniba.map.game.type.Item;

import java.util.Scanner;

public class Parser {
    public void commandEnter(){
        //Scanner scanner = new Scanner(System.in);
    }

    public ParserOutput parseCmd(String command, GameDescription game){
        ParserOutput cmd;
        Command token = new Command(null, null);
        Item item = null;
        String[] key = command.split(" ");

        for(int i = 0; i<game.getCommands().size(); i++){
            if(key[0].contains(game.getCommands().get(i).getName()) || game.getCommands().get(i).getAlias().contains(key[0])){
                token = new Command(game.getCommands().get(i).getType(),game.getCommands().get(i).getName());
                break;
            }
        }
        for(int i = 0; i<game.getItemList().size(); i++){
            try{
                if(key[1].contains(game.getItemList().get(i).getName())){
                    item = game.getItemList().get(i);
                    break;
                }
            }catch (Exception ArrayIndexOutOfBoundsException){
                break;
            }
        }

       // if(key.length > 1){
            cmd = new ParserOutput(token, item);
       // }else{
       //     cmd = new ParserOutput(token);
       // }
        return cmd;
    }
}
