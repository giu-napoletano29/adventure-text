package di.uniba.map.game.type;

import di.uniba.map.game.parser.Parser;
import di.uniba.map.game.parser.ParserOutput;

import java.util.*;

public class Item {
    private final int id;

    private String name;

    private String description;

    //private Set<String> alias;

    private List<Item> list = new ArrayList<>();

    private boolean openable = false;

    private boolean pickupable = true;

    private boolean weapon = false;

    private int power = 0;

    private boolean pushable = false;

    private boolean container = false;

    private boolean open = false;

    private boolean push = false;

    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    //weapon set

    public boolean isWeapon(){return weapon;}

    public void setWeapon(boolean weapon){
        this.weapon = weapon;
    }

    public int getPower(){ return power;};

    public void setPower(int power){
        this.power = power;
    }

    //end weapon

    //Impostazione Oggetto che può contenere oggetti
    public void setIsContainer(boolean cont) { this.container = cont;}

    public boolean getIsContainer() { return this.container;}

    public List<Item> getItemList() { return this.list;}

    //Fine
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpenable() {
        return openable;
    }

    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    public boolean isPickupable() {
        return pickupable;
    }

    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    public boolean isPushable() {
        return pushable;
    }

    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }
}
