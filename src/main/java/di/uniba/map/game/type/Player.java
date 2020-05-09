package di.uniba.map.game.type;

public class Player {
    private int hp;

    private int armor = 0;

    private String name;

    private String description;

    private Inventory inventory = new Inventory();

    private Item weaponEquip;

    public Player(int hp, String name, String description) {
        this.hp = hp;
        this.name = name;
        this.description = description;
    }

    public Inventory getInventory(){ return inventory;}

    public Item getWeaponEquip() { return weaponEquip;}

    public void setWeaponEquip(Item weapon){
        this.weaponEquip = weapon;
    }
}
