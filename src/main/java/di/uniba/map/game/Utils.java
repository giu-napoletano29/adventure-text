package di.uniba.map.game;

import di.uniba.map.game.type.Item;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;
import di.uniba.map.game.type.Npc;
import di.uniba.map.game.type.Room;

public class Utils {
    public void move(ParserOutput cmd, GameDescription game){
        boolean attack = false;
        if(cmd.getCommand().getType() != null){
            if(cmd.getCommand().getType() == CommandType.NORD){
                checkLock(game, game.getCurrentRoom().getNorth());
            } else if(cmd.getCommand().getType() == CommandType.SOUTH){
                checkLock(game, game.getCurrentRoom().getSouth());
            }else if(cmd.getCommand().getType() == CommandType.EAST){
                checkLock(game, game.getCurrentRoom().getEast());
            }else if(cmd.getCommand().getType() == CommandType.WEST) {
                checkLock(game, game.getCurrentRoom().getWest());
            }else if(cmd.getCommand().getType() == CommandType.INVENTORY){
                if(game.getInventory().getList().size() > 0){
                    for (Item o : game.getInventory().getList()) {
                        System.out.println(o.getName() + ": " + o.getDescription());
                    }
                }else{
                    System.out.println("Non ci sono oggetti nel tuo inventario!");
                }
            }
            else if(cmd.getCommand().getType() == CommandType.PICK_UP){
                if(cmd.getItem() != null && game.getCurrentRoom().getItems().contains(cmd.getItem())){
                    if(cmd.getItem().getIsContainer() && cmd.getContainerItem() != null){
                        if(cmd.getItem().isOpen()){
                            game.getInventory().add(cmd.getContainerItem());
                            cmd.getItem().getItemList().remove(cmd.getContainerItem());
                        }else{
                            System.out.println("Non puoi prendere qualcosa se " + cmd.getItem().getName() + " è chiuso!");
                        }
                    }
                    else{
                        if(cmd.getItem().isPickupable()){
                            game.getInventory().add(cmd.getItem());
                            game.getCurrentRoom().getItems().remove(cmd.getItem());
                        }else{
                            System.out.println("Ehi non puoi mica raccogliere " + cmd.getItem().getName());
                        }
                    }
                    attack = true;
                }
                else{
                    System.out.println("L'oggetto che cerchi non c'è!");
                }
            }
            else if(cmd.getCommand().getType() == CommandType.OPEN){
                if(cmd.getItem() != null && game.getCurrentRoom().getItems().contains(cmd.getItem())){
                    if(cmd.getItem().isOpenable()){
                        System.out.println("Guardando dentro..");
                        if(cmd.getItem().getItemList().size() > 0){
                            for (Item o : cmd.getItem().getItemList()) {
                                System.out.println(o.getName() + ": " + o.getDescription());
                            }
                            cmd.getItem().setOpen(true);
                        }else{
                            System.out.println("E' vuoto!");
                        }
                    }else{
                        System.out.println("Non riesco ad aprire " + cmd.getItem().getName());
                    }
                    attack = true;
                }else{
                    System.out.println("L'oggetto che cerchi non c'è!");
                }
            }
            else if(cmd.getCommand().getType() == CommandType.LOOK_AT){
                System.out.println(game.getCurrentRoom().getLook());
            }
            else if(cmd.getCommand().getType() == CommandType.TALK){
                if(cmd.getNpc() != null && game.getCurrentRoom().getNpcs().contains(cmd.getNpc())){
                    if(cmd.getNpc().getSpeakable()){
                        cmd.getNpc().talking();
                    }else{
                        System.out.println(cmd.getNpc().getName() + ": Non mi disturbare.");
                    }
                    attack = true;
                }else{
                    System.out.println("Quel tizio non c'è!");
                }
            }
            else if(cmd.getCommand().getType() == CommandType.SEARCH){
                System.out.print("Cerchiamo un po'...");
                if(game.getCurrentRoom().getItems().size() != 0 || game.getCurrentRoom().getNpcs().size() != 0){
                    System.out.println("Trovato qualcosa!");
                    for(int i = 0; i<game.getCurrentRoom().getItems().size(); i++){
                        System.out.print(game.getCurrentRoom().getItems().get(i).getName() + ", ");
                    }
                    for(int i = 0; i<game.getCurrentRoom().getNpcs().size(); i++){
                        if(i == 0){
                            System.out.println("\nC'è qualcuno..");
                        }
                        System.out.print(game.getCurrentRoom().getNpcs().get(i).getName() + ", ");
                    }
                    System.out.println("tutto qui!");
                }else{
                    System.out.println("Non ho trovato nulla di interessante!");
                }

            }
            else if(cmd.getCommand().getType() == CommandType.EQUIP){
                if(cmd.getItem() != null && game.getPlayer().getInventory().getList().contains(cmd.getItem())){
                    if(cmd.getItem().isWeapon()){
                        game.getPlayer().setWeaponEquip(cmd.getItem());
                        attack = true;
                    }else{
                        System.out.println("Non si può equipaggiare questo oggetto!");
                    }
                }
                else{
                    System.out.println("Questo oggetto non è in inventario!");
                }

            }
            else if(cmd.getCommand().getType() == CommandType.EAT){
                if(cmd.getItem() != null && game.getPlayer().getInventory().getList().contains(cmd.getItem())){
                    if(cmd.getItem().getHealer()){
                        game.getPlayer().setHp(game.getPlayer().getHp() + cmd.getItem().getHeal());
                        game.getPlayer().getInventory().getList().remove(cmd.getItem());
                        attack = true;
                    }else{
                        System.out.println("Non si può mangiare questo!");
                    }
                }
                else{
                    System.out.println("Questo oggetto non è in inventario!");
                }

            }
            else if(cmd.getCommand().getType() == CommandType.USE){
                if(cmd.getItem() != null && game.getPlayer().getInventory().getList().contains(cmd.getItem())){
                    if(cmd.getItem().isArmor()){
                        game.getPlayer().setClothesEquip(cmd.getItem());
                        game.getPlayer().setArmor(cmd.getItem().getProtection());

                        attack = true;
                    }else{
                        System.out.println("Non si può indossare questo!");
                    }
                }
                else{
                    System.out.println("Questo oggetto non è in inventario!");
                }

            }
            else if(cmd.getCommand().getType() == CommandType.STATS){
                printPlayerStats(game);
            }
            else if(cmd.getCommand().getType() == CommandType.ATTACK){
                if(cmd.getNpc() != null && game.getCurrentRoom().getNpcs().contains(cmd.getNpc())){
                    if(game.getPlayer().getWeaponEquip() != null && cmd.getNpc().getGod() == false){
                        cmd.getNpc().setHp((int) Math.round(cmd.getNpc().getHp() - (game.getPlayer().getWeaponEquip().getPower() - (game.getPlayer().getWeaponEquip().getPower() * (cmd.getNpc().getArmor()/200.000)))));
                        cmd.getNpc().setAttacking(true);
                        System.out.println(cmd.getNpc().getName() + " HP: " + cmd.getNpc().getHp());
                    }else if(cmd.getNpc().getGod() == true){
                        System.out.println(cmd.getNpc().getName() + ": Non perdere tempo con me. Sono invincibile.");
                    }
                    else{
                        System.out.println("Non conviene attaccare qualcuno senza armi..");
                    }
                    attack = true;
                }else{
                    System.out.println("Non si può attaccare qualcuno che non c'è..");
                }
            }
            if(attack){
                for(int i = 0; i<game.getCurrentRoom().getNpcs().size(); i++){
                    if(game.getCurrentRoom().getNpcs().get(i).getAttacking()){
                        npcResponse(game.getCurrentRoom().getNpcs().get(i), game);
                        printPlayerStats(game);
                    }
                }
            }
        }else{
            System.out.println("Ehm... non ho capito il comando");
        }
    }

    private void npcResponse(Npc cmd, GameDescription game) {
        if(cmd.getHp() <= 0){
            System.out.println(cmd.getName() + ": Ouch..");
            game.getCurrentRoom().getNpcs().remove(cmd);
        }else{
            if(cmd.getEnemy()){
                System.out.println(cmd.getName() + ": E no eh");
                game.getPlayer().setHp((int) Math.round(game.getPlayer().getHp() - (cmd.getWeaponEquip().getPower() - (cmd.getWeaponEquip().getPower() * (game.getPlayer().getArmor()/200.000)))));
            }
        }
    }

    public void printRoom(GameDescription game){
        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
    }

    public void printPlayerStats(GameDescription game){
        if(game.getPlayer().getWeaponEquip() != null){
            System.out.println("Hp: " + game.getPlayer().getHp() + " Armor: " + game.getPlayer().getArmor() + " Equip: " + game.getPlayer().getWeaponEquip().getName());
        }else{
            System.out.println("Hp: " + game.getPlayer().getHp() + " Armor: " + game.getPlayer().getArmor());
        }
    }

    private void checkLock(GameDescription game, Room room){
        if(room != null){
            if(!room.getLock()){
                game.setCurrentRoom(room);
                printRoom(game);
            }else{
                System.out.println("Non si può passare");
            }
        }else{
            System.out.println("Ehi non c'è niente qui.");  //TODO: cambiare i print in base al gioco, impostabile da file
        }
    }
}
