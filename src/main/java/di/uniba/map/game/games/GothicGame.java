package di.uniba.map.game.games;

import di.uniba.map.game.type.*;
import di.uniba.map.game.GameDescription;

import java.util.ArrayList;
import java.util.List;

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

        //Rooms
        Room entrylevel = new Room(0, "Ingresso", "Sei appena arrivato in questo nuovo mondo."
                + " Trova il modo di uscirne vivo.."
                + "\nQui troverai qualcuno in grado di aiutarti ad ambientarti. (Inserisci 'parla helper')");
        entrylevel.setLook("Circondato dal nulla, si può solo andare avanti.");

        Room woods = new Room(1, "Bosco", "Sei nel bosco!");
        Room testRoom = new Room(2, "Test Room", "Stanza Test"); //TODO: da rendere stanza reward con password per accedere e bonus
        testRoom.setLook("Che bella stanza di test! Sembra ci sia da provare un foglio, una cassa e Alessandra con cui parlare!");

        getRooms().add(testRoom);
        getRooms().add(entrylevel);
        getRooms().add(woods);

        entrylevel.setNorth(woods);
        entrylevel.setEast(testRoom);
        woods.setSouth(entrylevel);
        testRoom.setWest(entrylevel);

        //oggetti
        Item foglio = new Item(1, "foglio", "Un foglio su cui potrebbe esserci scritto qualcosa di interessante...");
        getItemList().add(foglio);
        testRoom.getItems().add(foglio);

        Item mela = new Item(4, "mela", "Restituisce 20 hp.");
        mela.setHealer(true);
        mela.setHeal(20);
        getItemList().add(mela);
        testRoom.getItems().add(mela);

        Item spada = new Item(3, "spada", "Sembra abbastanza affilata..");
        spada.setWeapon(true);
        spada.setPower(40);
        getItemList().add(spada);
        testRoom.getItems().add(spada);

        Item cassa = new Item (2, "cassa", "Potrà contenere sicuramente qualcosa..");
        cassa.setPickupable(false);
        cassa.setOpenable(true);
        cassa.setIsContainer(true);
        cassa.getItemList().add(foglio);
        getItemList().add(cassa);
        testRoom.getItems().add(cassa);

        //NPC HELPER
        Npc helper = new Npc(100, "helper", "Sono qui a spiegarti le basi del gioco!");
        List<Talk> talk2 = new ArrayList<>();
        Talk talk1 = new Talk();
        talk1.setSpeech("Hai bisogno di aiuto?");
        talk1.setSpeechtrigger(AnswerType.NOTHING);
        talk1.getAnswerList().add("Che comandi posso usare?");
        talk1.getAnswerList().add("Cosa bisogna fare?");
        talk1.getAnswerList().add("Chi sei?");
        talk1.getAnswerList().add("Fine");
        talk1.getAnswerTrigger().add(AnswerType.GOOD);
        talk1.getAnswerTrigger().add(AnswerType.BAD);
        talk1.getAnswerTrigger().add(AnswerType.DESC);
        talk1.getAnswerTrigger().add(AnswerType.END);
        talk2.add(talk1);
        talk1 = new Talk();
        talk1.setSpeech("Questi sono i comandi che puoi usare: \n-parla (nome): per parlare con un npc \n-attacca (nome): per attaccare un npc \n-osserva: descrive la stanza corrente \n-cerca: lista di oggetti o npc presenti nella stanza \n-nord,sud,ovest,est: navigazione \n-inventario: mostra inventario \n-stats: mostra statistiche giocatore \n-equip (nome arma): per equipaggiare un'arma \n-prendi: raccogliere oggetto \n-apri: aprire oggetto");
        talk1.setSpeechtrigger(AnswerType.GOOD);
        talk1.getAnswerList().add("Grazie!");
        talk1.getAnswerTrigger().add(AnswerType.BACK);
        talk2.add(talk1);
        talk1 = new Talk();
        talk1.setSpeech("Devi arrivare alla fine per vincere.");
        talk1.setSpeechtrigger(AnswerType.BAD);
        talk1.getAnswerList().add("Ok,grazie!");
        talk1.getAnswerTrigger().add(AnswerType.BACK);
        talk2.add(talk1);

        helper.setTalk(talk2);
        helper.setSpeakable(true);
        helper.setGod(true);
        getNpcList().add(helper);
        entrylevel.getNpcs().add(helper);
        //END NPC HELPER

        //NPC ENEMY
        Npc enemy = new Npc(100, "nemico", "Un tipo.");
        enemy.setEnemy(true);
        enemy.setWeaponEquip(spada);
        getNpcList().add(enemy);
        testRoom.getNpcs().add(enemy);
        //NPC ENEMY

        //Starting room
        setCurrentRoom(entrylevel);

    }
}
