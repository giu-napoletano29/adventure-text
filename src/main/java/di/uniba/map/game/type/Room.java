package di.uniba.map.game.type;


import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int id;

    private String name;

    private String description;

    private String look= "Vuoto";

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
    public String getName(){ return name;}

    public String getDescription() { return description;}

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }
}
