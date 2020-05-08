package di.uniba.map.game.parser;

import di.uniba.map.game.type.CommandType;
import di.uniba.map.game.type.Item;
import di.uniba.map.game.type.Command;
import di.uniba.map.game.type.Npc;

public class ParserOutput {

    private Command command;

    private Item item;

    private Npc npc;

    private String obj;

    public ParserOutput(Command command, String obj) {
        this.command = command;
        this.obj = obj;
    }

    public ParserOutput(Command command, Item item) {
        this.command = command;
        this.item = item;
    }

    public ParserOutput(Command command, Npc npc) {
        this.command = command;
        this.npc = npc;
    }

    public ParserOutput(Command command) {
        this.command = command;
    }

    public Command getCommand() { return command;}
    public String getObj() { return obj;}
    public Item getItem() { return item;}
    public Npc getNpc() { return npc;}
}
