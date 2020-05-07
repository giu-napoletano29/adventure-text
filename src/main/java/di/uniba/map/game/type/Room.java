package di.uniba.map.game.type;

import di.uniba.map.b.adventure.type.AdvObject;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int id;

    private String name;

    private String description;

    private Room south = null;

    private Room north = null;

    private Room east = null;

    private Room west = null;

    private final List<Item> items=new ArrayList<>();

    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
