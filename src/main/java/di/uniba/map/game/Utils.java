package di.uniba.map.game;

import di.uniba.map.game.type.Item;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;

public class Utils {
    public void move(ParserOutput cmd, GameDescription game){
        if(cmd != null){
            if(cmd.getCommand().getType() == CommandType.NORD){
                if(game.getCurrentRoom().getNorth() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getNorth());
                }
                else{
                    System.out.println("Ehi non c'è niente qui.");
                }
            } else if(cmd.getCommand().getType() == CommandType.SOUTH){
                if(game.getCurrentRoom().getSouth() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getSouth());
                }
                else{
                    System.out.println("Ehi non c'è niente qui.");
                }
            }else if(cmd.getCommand().getType() == CommandType.EAST){
                if(game.getCurrentRoom().getSouth() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getEast());
                }
                else{
                    System.out.println("Ehi non c'è niente qui.");
                }
            }else if(cmd.getCommand().getType() == CommandType.WEST){
                if(game.getCurrentRoom().getSouth() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getWest());
                }
                else{
                    System.out.println("Ehi non c'è niente qui.");
                }
            }
            else if(cmd.getCommand().getType() == CommandType.INVENTORY){
                if(game.getInventory().getList().size() > 0){
                    for (Item o : game.getInventory().getList()) {
                        System.out.println(o.getName() + ": " + o.getDescription());
                    }
                }else{
                    System.out.println("Non ci sono oggetti nel tuo inventario!");
                }

            }
        }else{
            System.out.println("Ehm... non ho capito il comando");
        }
    }

    public void printRoom(GameDescription game){
        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
    }

    public void printPlayerStats(){
        System.out.println("Hp: ");
    }
}
