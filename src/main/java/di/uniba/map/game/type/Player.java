package di.uniba.map.game.type;

public class Player extends Character{

    private Inventory inventory = new Inventory();

    public Player(int hp, String name, String description) {
        super(hp, name, description);
    }

    public Inventory getInventory(){ return inventory;}

}
