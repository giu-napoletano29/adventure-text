package di.uniba.map.game.type;

public class Player {
    private int hp;

    private String name;

    private String description;

    private Inventory inventory = new Inventory();

    public Player(int hp, String name, String description) {
        this.hp = hp;
        this.name = name;
        this.description = description;
    }

    public Inventory getInventory(){ return inventory;}
}
