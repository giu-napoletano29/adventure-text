package di.uniba.map.game.engine;

import di.uniba.map.game.engine.GameDescription;
import di.uniba.map.game.type.Item;
import di.uniba.map.game.parser.ParserOutput;
import di.uniba.map.game.type.CommandType;
import di.uniba.map.game.type.Npc;
import di.uniba.map.game.type.Room;
import di.uniba.map.game.language.LanguageSelector;

public class Utils {
    public void move(ParserOutput cmd, GameDescription game, LanguageSelector language) {
        boolean attack = false;
        if(cmd.getCommand().getType() != null){
            if(cmd.getCommand().getType() == CommandType.NORD){
                checkRoom(game, game.getCurrentRoom().getNorth(), language);
            } else if(cmd.getCommand().getType() == CommandType.SOUTH){
                checkRoom(game, game.getCurrentRoom().getSouth(), language);
            }else if(cmd.getCommand().getType() == CommandType.EAST){
                checkRoom(game, game.getCurrentRoom().getEast(), language);
            }else if(cmd.getCommand().getType() == CommandType.WEST) {
                checkRoom(game, game.getCurrentRoom().getWest(), language);
            }else if(cmd.getCommand().getType() == CommandType.INVENTORY){
                if(game.getInventory().getList().size() > 0){
                    for (Item o : game.getInventory().getList()) {
                        System.out.println(o.getName() + ": " + o.getDescription());
                    }
                }else{
                    System.out.println(language.getDocument().getElementsByTagName("empty_inv").item(0).getTextContent());
                }
            }
            else if(cmd.getCommand().getType() == CommandType.PICK_UP){
                if(cmd.getItem() != null && game.getCurrentRoom().getItems().contains(cmd.getItem())){
                    if(cmd.getItem().getIsContainer() && cmd.getContainerItem() != null){
                        if(cmd.getItem().isOpen()){
                            game.getInventory().add(cmd.getContainerItem());
                            cmd.getItem().getItemList().remove(cmd.getContainerItem());
                        }else{
                            System.out.println(language.getDocument().getElementsByTagName("cant_pick").item(0).getTextContent() + cmd.getItem().getName() + language.getDocument().getElementsByTagName("is_closed").item(0).getTextContent());
                        }
                    }
                    else{
                        if(cmd.getItem().isPickupable()){
                            game.getInventory().add(cmd.getItem());
                            game.getCurrentRoom().getItems().remove(cmd.getItem());
                        }else{
                            System.out.println(language.getDocument().getElementsByTagName("cant_pick2").item(0).getTextContent() + cmd.getItem().getName());
                        }
                    }
                    attack = true;
                }
                else{
                    System.out.println(language.getDocument().getElementsByTagName("no_item").item(0).getTextContent());
                }
            }
            else if(cmd.getCommand().getType() == CommandType.THROW){
                if(cmd.getItem() != null && game.getInventory().getList().contains(cmd.getItem())){
                    game.getInventory().getList().remove(cmd.getItem());
                    game.getCurrentRoom().getItems().add(cmd.getItem());
                    System.out.println(cmd.getItem().getName() + language.getDocument().getElementsByTagName("is_throw").item(0).getTextContent());
                }else{
                    System.out.println(language.getDocument().getElementsByTagName("no_item_inv").item(0).getTextContent());
                }

            }
            else if(cmd.getCommand().getType() == CommandType.OPEN){
                if(cmd.getItem() != null && game.getCurrentRoom().getItems().contains(cmd.getItem())){
                    if(cmd.getItem().isOpenable()){
                        System.out.println(language.getDocument().getElementsByTagName("look_in").item(0).getTextContent());
                        if(cmd.getItem().getItemList().size() > 0){
                            for (Item o : cmd.getItem().getItemList()) {
                                System.out.println(o.getName() + ": " + o.getDescription());
                            }
                            cmd.getItem().setOpen(true);
                        }else{
                            System.out.println(language.getDocument().getElementsByTagName("is_empty").item(0).getTextContent());
                        }
                    }else if(cmd.getItem().getIsContainer() && cmd.getContainerItem() != null){
                        if(game.getPlayer().getInventory().getList().contains(cmd.getContainerItem())){
                            if(cmd.getItem().getOpenWith() == cmd.getContainerItem()){
                                cmd.getItem().setOpenable(true);
                                System.out.println(cmd.getItem().getName() + language.getDocument().getElementsByTagName("is_opened").item(0).getTextContent());
                            }else{
                                System.out.println(language.getDocument().getElementsByTagName("cant_open").item(0).getTextContent() + cmd.getItem().getName() + language.getDocument().getElementsByTagName("with").item(0).getTextContent() + cmd.getContainerItem().getName());
                            }
                        }else{
                            System.out.println(cmd.getContainerItem().getName() + language.getDocument().getElementsByTagName("not_in_inv").item(0).getTextContent());
                        }
                    }
                    else{
                        System.out.println(language.getDocument().getElementsByTagName("cant_opening").item(0).getTextContent() + cmd.getItem().getName());
                    }
                    attack = true;
                }else{
                    System.out.println(language.getDocument().getElementsByTagName("no_item_searched").item(0).getTextContent());
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
                        System.out.println(cmd.getNpc().getName() + language.getDocument().getElementsByTagName("dont_annoy").item(0).getTextContent());
                    }
                    attack = true;
                }else{
                    System.out.println(language.getDocument().getElementsByTagName("no_npc_here").item(0).getTextContent());
                }
            }
            else if(cmd.getCommand().getType() == CommandType.SEARCH){
                System.out.print(language.getDocument().getElementsByTagName("looking_for").item(0).getTextContent());
                if(game.getCurrentRoom().getItems().size() != 0 || game.getCurrentRoom().getNpcs().size() != 0){
                    System.out.println(language.getDocument().getElementsByTagName("found_something").item(0).getTextContent());
                    for(int i = 0; i<game.getCurrentRoom().getItems().size(); i++){
                        System.out.print(game.getCurrentRoom().getItems().get(i).getName() + ", ");
                    }
                    for(int i = 0; i<game.getCurrentRoom().getNpcs().size(); i++){
                        if(i == 0){
                            System.out.println("\n" + language.getDocument().getElementsByTagName("is_there_anybody").item(0).getTextContent());
                        }
                        System.out.print(game.getCurrentRoom().getNpcs().get(i).getName() + ", ");
                    }
                    System.out.println(language.getDocument().getElementsByTagName("justit").item(0).getTextContent());
                }else{
                    System.out.println(language.getDocument().getElementsByTagName("found_nothing_interesting").item(0).getTextContent());
                }

            }
            else if(cmd.getCommand().getType() == CommandType.EQUIP){
                if(cmd.getItem() != null && game.getPlayer().getInventory().getList().contains(cmd.getItem())){
                    if(cmd.getItem().isWeapon()){
                        game.getPlayer().setWeaponEquip(cmd.getItem());
                        attack = true;
                    }else{
                        System.out.println(language.getDocument().getElementsByTagName("cant_equip").item(0).getTextContent());
                    }
                }
                else{
                    System.out.println(language.getDocument().getElementsByTagName("no_item_in_inv").item(0).getTextContent());
                }

            }
            else if(cmd.getCommand().getType() == CommandType.EAT){
                if(cmd.getItem() != null && game.getPlayer().getInventory().getList().contains(cmd.getItem())){
                    if(cmd.getItem().getHealer()){
                        if(game.getPlayer().getHp() < 100){
                            game.getPlayer().setHp(game.getPlayer().getHp() + cmd.getItem().getHeal());
                            if(game.getPlayer().getHp() > 100){
                                game.getPlayer().setHp(100);
                            }
                            game.getPlayer().getInventory().getList().remove(cmd.getItem());
                        }else{
                            System.out.println(language.getDocument().getElementsByTagName("max_hp").item(0).getTextContent());
                        }
                        attack = true;
                    }else{
                        System.out.println(language.getDocument().getElementsByTagName("cant_eat").item(0).getTextContent());
                    }
                }
                else{
                    System.out.println(language.getDocument().getElementsByTagName("no_item_in_inv").item(0).getTextContent());
                }

            }
            else if(cmd.getCommand().getType() == CommandType.USE){
                if(cmd.getItem() != null && game.getPlayer().getInventory().getList().contains(cmd.getItem())){
                    if(cmd.getItem().isArmor()){
                        game.getPlayer().setClothesEquip(cmd.getItem());
                        game.getPlayer().setArmor(cmd.getItem().getProtection());

                        attack = true;
                    }else{
                        System.out.println(language.getDocument().getElementsByTagName("cant_fit").item(0).getTextContent());
                    }
                }
                else{
                    System.out.println(language.getDocument().getElementsByTagName("no_item_in_inv").item(0).getTextContent());
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
                        cmd.getNpc().setSpeakable(false);
                        System.out.println(cmd.getNpc().getName() + " HP: " + cmd.getNpc().getHp());
                    }else if(cmd.getNpc().getGod() == true){
                        System.out.println(cmd.getNpc().getName() + language.getDocument().getElementsByTagName("junior_dragon_ball").item(0).getTextContent());
                    }
                    else{
                        System.out.println(language.getDocument().getElementsByTagName("dont_attack_without_weapon").item(0).getTextContent());
                    }
                    attack = true;
                }else{
                    System.out.println(language.getDocument().getElementsByTagName("cant_attack_someone_not_here").item(0).getTextContent());
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
            System.out.println(language.getDocument().getElementsByTagName("cant_understand").item(0).getTextContent());
        }
    }

    private void npcResponse(Npc cmd, GameDescription game) {
        if(cmd.getHp() <= 0){
            System.out.println(cmd.getName() + ": Ouch..");
            if(cmd.getWeaponEquip() != null){
                game.getCurrentRoom().getItems().add(cmd.getWeaponEquip());
            }
            game.getCurrentRoom().getNpcs().remove(cmd);
        }else{
            if(cmd.getEnemy()){
                System.out.println(cmd.getName() + ": E no eh"); //TODO: cambiare frase in base al personaggio
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

    private void checkRoom(GameDescription game, Room room, LanguageSelector language){
        if(room != null){
            if(!room.getLock()){
                game.setCurrentRoom(room);
                printRoom(game);
                if(room.getTriggerReference() != null && room.getExplored() == false){
                    room.setExplored(true);
                    room.getTriggerReference().trigger();
                }
            }else{
                System.out.println(language.getDocument().getElementsByTagName("is_blocked").item(0).getTextContent());
            }
        }else{
            System.out.println(language.getDocument().getElementsByTagName("nothing_here").item(0).getTextContent());  //TODO: cambiare i print in base al gioco, impostabile da file
        }
    }
}
