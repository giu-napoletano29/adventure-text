package di.uniba.map.game.type;

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

    //Impostazione Oggetto che può contenere oggetti
    public void setIsContainer() { this.container = true;}

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
