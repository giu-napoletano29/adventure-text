package di.uniba.map.game.games;

import di.uniba.map.game.type.*;
import di.uniba.map.game.GameDescription;

import java.util.List;
import java.util.stream.Collectors;

public class GothicGame extends GameDescription {
    @Override
    public void init() throws Exception {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command inventory = new Command(CommandType.INVENTORY, "inventario");
        inventory.setAlias(new String[]{"inv", "i", "I"});
        getCommands().add(inventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci","exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "descrivi"});
        getCommands().add(look);
        Command search = new Command(CommandType.SEARCH, "cerca");
        search.setAlias(new String[]{"trova", "controlla"});
        getCommands().add(search);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi", "pick", "prendere"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi","attiva"});
        getCommands().add(push);
        Command talk = new Command(CommandType.TALK, "parla");
        talk.setAlias(new String[]{"Parla","PARLA"});
        getCommands().add(talk);
        Command equip = new Command(CommandType.EQUIP, "equipaggia");
        equip.setAlias(new String[]{"equip"});
        getCommands().add(equip);
        Command stats = new Command(CommandType.STATS, "statistica");
        stats.setAlias(new String[]{"stat", "stats", "statistiche", "vita", "info", "informazioni"});
        getCommands().add(stats);
        Command attack = new Command(CommandType.ATTACK, "attacca");
        attack.setAlias(new String[]{"attack", "combatti"});
        getCommands().add(attack);
        Command eat = new Command(CommandType.EAT, "mangia");
        eat.setAlias(new String[]{"eat", "bevi", "assumi"});
        getCommands().add(eat);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"indossa", "vesti", "use"});
        getCommands().add(use);

        //Rooms
        Room entrylevel = new Room(0, "Ingresso", "Sei appena arrivato in questo nuovo mondo."
                + " Trova il modo di uscirne vivo.."
                + "\nQui troverai qualcuno in grado di aiutarti ad ambientarti. (Inserisci 'parla helper')");
        entrylevel.setLook("Circondato dal nulla, si può solo andare avanti.");

        Room entry_woods = new Room(1, "Bosco", "Ingresso del bosco ");
        entry_woods.setLook("Meglio trovare qualcosa qua intorno, potrebbe essere pericoloso d'ora in poi..");

        Room woods_1 = new Room(3, "Bosco_1", "Sentiero del bosco");
        woods_1.setLook("Sembra ci siano i resti di qualcuno qui..");

        Room woods_2 = new Room(4, "Bosco_2", "Bosco_2");
        woods_2.setLook("Sei nel bosco_2");

        Room woods_3 = new Room(5, "Bosco_3", "Bosco_3");
        woods_3.setLook("Sei nel bosco_3");

        Room woods_4 = new Room(6, "Bosco_4", "Bosco_4");
        woods_4.setLook("Sei nel bosco_4");

        Room woods_end = new Room(7, "Bosco_Fine", "Bosco_Fine");
        woods_end.setLook("Sei nel bosco_Fine");

        Room entry_old_camp = new Room(8, "Ingresso Campo Vecchio", "Ingresso Campo Vecchio");
        entry_old_camp.setLook("Sei nel Ingresso Campo Vecchio");

        Room old_camp_1 = new Room(9, "Campo Vecchio_1", "Campo Vecchio_1");
        old_camp_1.setLook("Sei nel Campo Vecchio_1");

        Room old_camp_2 = new Room(10, "Campo Vecchio-2", "Campo Vecchio_2");
        old_camp_2.setLook("Sei nel Campo Vecchio_2");

        Room old_camp_gate = new Room(11, "Campo Vecchio_gate", "Campo Vecchio_gate");
        old_camp_gate.setLook("Sei nel Campo Vecchio_gate");

        Room old_camp_3 = new Room(12, "Campo Vecchio_3", "Campo Vecchio_3");
        old_camp_3.setLook("Sei nel Campo Vecchio_3");

        Room old_camp_4 = new Room(13, "Campo Vecchio_4", "Campo Vecchio_4");
        old_camp_4.setLook("Sei nel Campo Vecchio_4");

        Room baron_room = new Room(14, "Stanza del Barone", "Stanza del Barone");
        baron_room.setLook("Sei nella stanza del barone");

        Room final_room = new Room(15, "Stanza del Boss", "Stanza del Boss");
        final_room.setLook("Sei nella stanza del Boss");

        Room testRoom = new Room(2, "Test Room", "Stanza Test"); //TODO: da rendere stanza reward con password per accedere e bonus
        testRoom.setLook("Che bella stanza di test! Sembra ci sia da provare un foglio, una cassa e Alessandra con cui parlare!");

        getRooms().add(testRoom);
        getRooms().add(entrylevel);
        getRooms().add(entry_woods);
        getRooms().add(woods_1);
        getRooms().add(woods_2);
        getRooms().add(woods_3);
        getRooms().add(woods_4);
        getRooms().add(woods_end);
        getRooms().add(entry_old_camp);
        getRooms().add(old_camp_1);
        getRooms().add(old_camp_2);
        getRooms().add(old_camp_gate);
        getRooms().add(old_camp_3);
        getRooms().add(old_camp_4);
        getRooms().add(baron_room);
        baron_room.setLock(true);
        getRooms().add(final_room);

        entrylevel.setNorth(entry_woods);
        entrylevel.setEast(testRoom);
        entry_woods.setSouth(entrylevel);
        entry_woods.setWest(woods_1);
        entry_woods.setNorth(woods_2);
        entry_woods.setEast(woods_3);
        woods_1.setEast(entry_woods);
        woods_1.setWest(woods_end);
        woods_2.setSouth(entry_woods);
        woods_3.setWest(entry_woods);
        woods_3.setSouth(woods_4);
        woods_4.setNorth(woods_3);
        woods_end.setEast(woods_1);
        woods_end.setNorth(entry_old_camp);
        entry_old_camp.setSouth(woods_end);
        entry_old_camp.setNorth(old_camp_1);
        old_camp_1.setSouth(entry_old_camp);
        old_camp_1.setEast(old_camp_2);
        old_camp_2.setWest(old_camp_1);
        old_camp_2.setEast(old_camp_gate);
        old_camp_gate.setWest(old_camp_2);
        old_camp_gate.setEast(old_camp_3);
        old_camp_gate.setNorth(baron_room);
        old_camp_3.setWest(old_camp_gate);
        old_camp_3.setEast(old_camp_4);
        old_camp_4.setWest(old_camp_3);
        baron_room.setSouth(old_camp_gate);
        baron_room.setNorth(final_room);
        final_room.setSouth(baron_room);
        testRoom.setWest(entrylevel);

        //oggetti
        Item letter = new Item(1, "lettera", "Askfhak shfak hskfhkaaff sfaagfege ... E' una lettera incomprensibile.");
        getItemList().add(letter);

        Item mela = new Item(4, "mela", "Restituisce 20 hp.");
        mela.setHealer(true);
        mela.setHeal(20);
        getItemList().add(mela);

        Item potion = new Item(6, "pozione", "Restituisce 50 hp.");
        potion.setHealer(true);
        potion.setHeal(20);
        getItemList().add(potion);

        Item spada = new Item(3, "spada", "Sembra abbastanza affilata..");
        spada.setWeapon(true);
        spada.setPower(40);
        getItemList().add(spada);

        Item zanne = new Item(5, "zanne", "Zanne di lupo");
        zanne.setWeapon(true);
        zanne.setPower(15);
        getItemList().add(zanne);

        Item broken_spada = new Item(7, "spada_rotta", "Non molto affilata...contro qualche animale dovrebbe comunque andare bene");
        broken_spada.setWeapon(true);
        broken_spada.setPower(20);
        getItemList().add(broken_spada);

        Item club = new Item(8, "Mazza", "Una mazza, efficace contro le persone");
        club.setWeapon(true);
        club.setPower(25);
        getItemList().add(club);

        Item super_spada = new Item(9, "super_spada", "E' una delle spade più forti che esistano");
        super_spada.setWeapon(true);
        super_spada.setPower(55);
        getItemList().add(super_spada);

        Item key = new Item(10, "chiave", "Utile per aprire qualche forziere..");
        getItemList().add(key);

        Item w_clothes = new Item(11, "vestiti", "Vestiti pesanti da lavoratore. Fornisce 15 punti di armatura.");
        w_clothes.setArmor(true);
        w_clothes.setProtection(15);
        getItemList().add(w_clothes);

        Item light_armor = new Item(12, "armatura_leggera", "Armatura leggera consumata. Fornisce 35 punti di armatura.");
        light_armor.setArmor(true);
        light_armor.setProtection(35);
        getItemList().add(light_armor);

        Item heavy_armor = new Item(13, "armatura_leggera", "Armatura leggera consumata. Fornisce 35 punti di armatura.");
        heavy_armor.setArmor(true);
        heavy_armor.setProtection(70);
        getItemList().add(heavy_armor);

        Item cassa = new Item (2, "cassa", "Potrà contenere sicuramente qualcosa..");
        cassa.setPickupable(false);
        cassa.setOpenable(true);
        cassa.setIsContainer(true);
        cassa.getItemList().add(letter);
        cassa.getItemList().add(heavy_armor);
        getItemList().add(cassa);


        //NPC HELPER
        Npc helper = new Npc(100, "helper", "Sono qui a spiegarti le basi del gioco!");
        Talk talk1 = new Talk();
        Talk talk2 = new Talk();
        Answer answer = new Answer();
        talk1.setSpeech("Hai bisogno di aiuto?");
        answer.setAnswer("Che comandi posso usare?");
        answer.setWarp(talk2);
        talk1.getAns().add(answer);

        answer = new Answer();
        talk2.setSpeech("Questi sono i comandi che puoi usare: \n-parla (nome): per parlare con un npc \n-attacca (nome): per attaccare un npc \n-osserva: descrive la stanza corrente \n-cerca: lista di oggetti o npc presenti nella stanza \n-nord,sud,ovest,est: navigazione \n-inventario: mostra inventario \n-stats: mostra statistiche giocatore \n-equip (nome arma): per equipaggiare un'arma \n-prendi: raccogliere oggetto \n-apri: aprire oggetto");
        answer.setAnswer("Grazie!");
        answer.setWarp(talk1);
        talk2.getAns().add(answer);

        talk2 = new Talk();
        answer = new Answer();
        answer.setAnswer("Cosa bisogna fare?");
        answer.setWarp(talk2);
        talk1.getAns().add(answer);

        answer = new Answer();
        talk2.setSpeech("Devi arrivare alla fine per vincere.");
        answer.setAnswer("Ho capito!");
        answer.setWarp(talk1);
        talk2.getAns().add(answer);
        commonDialog(talk1, helper);

        helper.setTalk(talk1); //Set di dialogo iniziale
        helper.setSpeakable(true);
        helper.setGod(true);
        getNpcList().add(helper);
        //END NPC HELPER

        Npc fabbro = new Npc(100, "fabbro", "Sono il fabbro del campo");
        fabbro.setGod(true);
        fabbro.setSpeakable(true);
        getNpcList().add(fabbro);

        //NPC ENEMY
        Npc enemy = new Npc(100, "nemico", "Un tipo.");
        enemy.setEnemy(true);
        enemy.setWeaponEquip(spada);
        getNpcList().add(enemy);

        Npc e_guard = new Npc(100, "guardia", "Sono una guardia di Campo Vecchio.");
        e_guard.setEnemy(true);
        e_guard.setSpeakable(true);
        e_guard.setWeaponEquip(spada);
        //speaking
        e_guardDialog(e_guard);
        //end speaking
        getNpcList().add(e_guard);

        Npc gate_guard = new Npc(100, "thorus", "Un tipo.");
        gate_guard.setEnemy(true);
        gate_guard.setArmor(70);
        gate_guard.setWeaponEquip(spada);
        //speaking
        gate_guardDialog(e_guard);
        //end speaking
        getNpcList().add(gate_guard);

        Npc bully_1 = new Npc(100, "Bullo", "Un tipo.");
        bully_1.setEnemy(true);
        bully_1.setWeaponEquip(club);
        getNpcList().add(bully_1);

        Npc bully_2 = new Npc(100, "Bullo_2", "Un tipo.");
        bully_2.setEnemy(true);
        bully_2.setWeaponEquip(club);
        getNpcList().add(bully_2);

        Npc bully_3 = new Npc(100, "Bullo_3", "Un tipo.");
        bully_3.setEnemy(true);
        bully_3.setWeaponEquip(club);
        getNpcList().add(bully_3);

        Npc wolf = new Npc(40, "lupo", "Un lupo, potrebbe essere aggressivo.");
        wolf.setEnemy(true);
        wolf.setWeaponEquip(zanne);
        getNpcList().add(wolf);

        Npc wolf2 = new Npc(30, "lupo_piccolo", "Un lupo piccolo, potrebbe essere aggressivo.");
        wolf2.setEnemy(true);
        wolf2.setWeaponEquip(zanne);
        getNpcList().add(wolf2);

        Npc wolf3 = new Npc(50, "lupo_forte", "Un lupo, potrebbe essere aggressivo.");
        wolf3.setEnemy(true);
        wolf3.setWeaponEquip(zanne);
        getNpcList().add(wolf3);

        Npc boss = new Npc(100, "boss", "E' il boss finale");
        boss.setEnemy(true);
        boss.setArmor(100);
        boss.setWeaponEquip(super_spada);
        getNpcList().add(boss);
        //NPC ENEMY

        //item insert
        testRoom.getItems().add(letter);
        testRoom.getItems().add(mela);
        testRoom.getItems().add(spada);
        testRoom.getItems().add(cassa);
        testRoom.getItems().add(w_clothes);
        entry_woods.getItems().add(mela);
        entry_woods.getItems().add(broken_spada);
        entry_woods.getItems().add(w_clothes);
        woods_1.getItems().add(light_armor);
        woods_4.getItems().add(potion);

        //NPC
        entrylevel.getNpcs().add(helper);
        testRoom.getNpcs().add(enemy);
        woods_2.getNpcs().add(wolf);
        woods_3.getNpcs().add(wolf2);
        woods_end.getNpcs().add(wolf3);
        entry_old_camp.getNpcs().add(e_guard);
        old_camp_gate.getNpcs().add(gate_guard);
        old_camp_1.getNpcs().add(bully_1);
        old_camp_3.getNpcs().add(bully_2);
        old_camp_3.getNpcs().add(bully_3);
        old_camp_3.getNpcs().add(fabbro);
        final_room.getNpcs().add(boss);

        //end insert

        //Starting room
        setCurrentRoom(entrylevel);

    }

    private static void commonDialog(Talk talk1, Npc npc) {
        Talk talk2 = new Talk();
        Answer answer = new Answer();
        answer.setAnswer("Chi sei?");
        answer.setWarp(talk2);
        talk1.getAns().add(answer);

        answer = new Answer();
        talk2.setSpeech(npc.getDescription());
        answer.setAnswer("Ho capito!");
        answer.setWarp(talk1);
        talk2.getAns().add(answer);

        answer = new Answer();
        answer.setAnswer("Fine");
        answer.setTriggerReference(() -> {System.out.println("Prova delle lambda expression"); });
        talk1.getAns().add(answer);
    }

    private static void e_guardDialog(Npc e_guard) {
        Talk talk1 = new Talk();
        e_guard.setTalk(talk1);
        Talk talk2 = new Talk();
        Answer answer = new Answer();
        talk1.setSpeech("Salve!");
        answer.setAnswer("Dove mi trovo?");
        answer.setWarp(talk2);
        talk1.getAns().add(answer);

        answer = new Answer();
        talk2.setSpeech("Ti trovi a Campo Vecchio! E' l'ultimo accampamento sopravvissuto qui.");
        Talk talk3 = new Talk();
        answer.setAnswer("Cosa è successo?");
        answer.setWarp(talk3);
        talk2.getAns().add(answer);

        answer = new Answer();
        talk3.setSpeech("E' passato ormai tanto tempo da quando siamo chiusi qui dentro. I mostri della zona hanno fatto il resto. Guardati sempre le spalle anche da quelli come te.");
        answer.setAnswer("Mmm..");
        answer.setWarp(talk2);
        talk3.getAns().add(answer);

        talk3 = new Talk();
        answer = new Answer();
        answer.setAnswer("Come esco da qui?");
        answer.setWarp(talk3);
        talk2.getAns().add(answer);

        answer = new Answer();
        talk3.setSpeech("Solo il Barone decide chi può uscire, e non credo la tua permanenza durerà così poco...");
        answer.setAnswer("Mmm..");
        answer.setWarp(talk2);
        talk3.getAns().add(answer);

        answer = new Answer();
        answer.setAnswer("Ho capito.");
        answer.setWarp(talk1);
        talk2.getAns().add(answer);
        commonDialog(talk1, e_guard);
    }

    private static void gate_guardDialog(Npc e_guard) { //TODO: completare dialoghi Thorus
        Talk talk1 = new Talk();
        e_guard.setTalk(talk1);
        Talk talk2 = new Talk();
        Answer answer = new Answer();
        talk1.setSpeech("Salve!");
        answer.setAnswer("Dove mi trovo?");
        answer.setWarp(talk2);
        talk1.getAns().add(answer);

        answer = new Answer();
        talk2.setSpeech("Ti trovi a Campo Vecchio! E' l'ultimo accampamento sopravvissuto qui.");
        Talk talk3 = new Talk();
        answer.setAnswer("Cosa è successo?");
        answer.setWarp(talk3);
        talk2.getAns().add(answer);

        answer = new Answer();
        talk3.setSpeech("E' passato ormai tanto tempo da quando siamo chiusi qui dentro. I mostri della zona hanno fatto il resto. Guardati sempre le spalle anche da quelli come te.");
        answer.setAnswer("Mmm..");
        answer.setWarp(talk2);
        talk3.getAns().add(answer);

        talk3 = new Talk();
        answer = new Answer();
        answer.setAnswer("Come esco da qui?");
        answer.setWarp(talk3);
        talk2.getAns().add(answer);

        answer = new Answer();
        talk3.setSpeech("Solo il Barone decide chi può uscire, e non credo la tua permanenza durerà così poco...");
        answer.setAnswer("Mmm..");
        answer.setWarp(talk2);
        talk3.getAns().add(answer);

        answer = new Answer();
        answer.setAnswer("Ho capito.");
        answer.setWarp(talk1);
        talk2.getAns().add(answer);
        commonDialog(talk1, e_guard);
    }

    public boolean isWin(){
        boolean win = false;
        List<Npc> boss = getNpcList().stream()
                .filter(item -> item.getName().equals("boss"))
                .filter(item -> item.getHp() > 0)
                .collect(Collectors.toList());
        if(boss.isEmpty()){
            win = true;
        }
        return win;
    }
}
