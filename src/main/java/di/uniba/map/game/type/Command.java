package di.uniba.map.game.type;

import di.uniba.map.game.type.CommandType;

import java.util.Set;

public class Command {

    private final CommandType type;

    private final String name;

    //private Set<String> alias;

    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }


    public CommandType getType() {
        return type;
    }
}
