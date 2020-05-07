package di.uniba.map.game.type;

public class Npc {
    private int hp;

    private String name;

    private String description;

    private boolean enemy = false;

    public Npc(int hp, String name, String description) {
        this.hp = hp;
        this.name = name;
        this.description = description;
    }
}
