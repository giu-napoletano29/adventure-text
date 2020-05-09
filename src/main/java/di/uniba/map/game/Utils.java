package di.uniba.map.game;

import di.uniba.map.game.type.Item;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;

public class Utils {
    public void move(ParserOutput cmd, GameDescription game){
        if(cmd.getCommand().getType() != null){
            if(cmd.getCommand().getType() == CommandType.NORD){
                if(game.getCurrentRoom().getNorth() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getNorth());
                    printRoom(game);
                }
                else{
                    System.out.println("Ehi non c'è niente qui.");  //TODO: cambiare i print in base al gioco, impostabile da file
                }
            } else if(cmd.getCommand().getType() == CommandType.SOUTH){
                if(game.getCurrentRoom().getSouth() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getSouth());
                    printRoom(game);
                }
                else{
                    System.out.println("Ehi non c'è niente qui.");
                }
            }else if(cmd.getCommand().getType() == CommandType.EAST){
                if(game.getCurrentRoom().getEast() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getEast());
                    printRoom(game);
                }
                else{
                    System.out.println("Ehi non c'è niente qui.");
                }
            }else if(cmd.getCommand().getType() == CommandType.WEST){
                if(game.getCurrentRoom().getWest() != null){
                    game.setCurrentRoom(game.getCurrentRoom().getWest());
                    printRoom(game);
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
                    }else{
                        System.out.println("Non si può equipaggiare questo oggetto!");
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
                    if(game.getPlayer().getWeaponEquip() != null){
                        cmd.getNpc().setHp(cmd.getNpc().getHp() - (game.getPlayer().getWeaponEquip().getPower() - (game.getPlayer().getWeaponEquip().getPower() * (cmd.getNpc().getArmor()/200))));
                        if(cmd.getNpc().getHp() <= 0){
                            System.out.println(cmd.getNpc().getName() + ": Ouch..");
                            game.getCurrentRoom().getNpcs().remove(cmd.getNpc());
                        }else{
                            if(cmd.getNpc().getEnemy()){
                                System.out.println(cmd.getNpc().getName() + ": E no eh");
                                cmd.getNpc().setAttacking(true);
                                game.getPlayer().setHp(game.getPlayer().getHp() - (cmd.getNpc().getWeaponEquip().getPower() - (cmd.getNpc().getWeaponEquip().getPower() * (game.getPlayer().getArmor()/200))));
                            }
                        }
                    }else{
                        System.out.println("Non conviene attaccare qualcuno senza armi..");
                    }
                }else{
                    System.out.println("Non si può attaccare qualcuno che non c'è..");
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

    public void printPlayerStats(GameDescription game){
        if(game.getPlayer().getWeaponEquip() != null){
            System.out.println("Hp: " + game.getPlayer().getHp() + " Armor: " + game.getPlayer().getArmor() + " Equip: " + game.getPlayer().getWeaponEquip().getName());
        }else{
            System.out.println("Hp: " + game.getPlayer().getHp() + " Armor: " + game.getPlayer().getArmor());
        }
    }
}
