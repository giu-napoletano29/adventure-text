package di.uniba.map.game;

import di.uniba.map.game.type.Item;
import di.uniba.map.game.type.Command;
import di.uniba.map.game.type.Room;

import java.util.ArrayList;
import java.util.List;

public abstract class GameDescription {
    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private final List<Item> inventory = new ArrayList<>();

    public abstract void init() throws Exception;

    public List<Command> getCommands() {
        return commands;
    }

}
