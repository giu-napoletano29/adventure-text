package di.uniba.map.game.type;


import java.util.ArrayList;
import java.util.List;

public class Room2 {
    private final int id;

    private String name;

    private String description;

    private String look= "Vuoto";

    private boolean locked = false;

    private boolean explored = false;

    private Room2 south = null;

    private Room2 north = null;

    private Room2 east = null;

    private Room2 west = null;

    private Triggers.triggerInterface triggerReference = null;

    private final List<Item> items=new ArrayList<>();

    private final List<Npc> npc=new ArrayList<>();

    public Room2(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName(){ return name;}

    public String getDescription() { return description;}

    public Room2 getSouth() {
        return south;
    }

    public void setSouth(Room2 south) {
        this.south = south;
    }

    public Room2 getNorth() {
        return north;
    }

    public void setNorth(Room2 north) {
        this.north = north;
    }

    public Room2 getEast() {
        return east;
    }

    public void setEast(Room2 east) {
        this.east = east;
    }

    public Room2 getWest() {
        return west;
    }

    public void setWest(Room2 west) {
        this.west = west;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Npc> getNpcs() {
        return npc;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public boolean getLock(){return locked;}

    public void setLock(boolean lock){this.locked = lock;}

    public boolean getExplored(){return this.explored;}

    public void setExplored(boolean exp){this.explored = exp;}

    public void setTriggerReference(Triggers.triggerInterface T){this.triggerReference = T;}

    public Triggers.triggerInterface getTriggerReference(){
        return this.triggerReference;
    }
}
