package di.uniba.map.game.parser;

import di.uniba.map.game.type.CommandType;
import di.uniba.map.game.type.Item;
import di.uniba.map.game.type.Command;

public class ParserOutput {

    private Command command;

    private String obj;

    public ParserOutput(Command command, String obj) {
        this.command = command;
        this.obj = obj;
    }

    public Command getCommand() { return command;}
    public String getObj() { return obj;}
}
