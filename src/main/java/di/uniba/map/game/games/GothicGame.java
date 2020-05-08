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
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati","exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi","attiva"});
        getCommands().add(push);
        Command talk = new Command(CommandType.TALK, "parla");
        push.setAlias(new String[]{"Parla","PARLA"});
        getCommands().add(talk);

        //Rooms
        Room entrylevel = new Room(0, "Ingresso", "Sei appena arrivato in questo nuovo mondo."
                + " Trova il modo di uscirne vivo..");
        entrylevel.setLook("Circondato dal nulla, si può solo andare avanti.");

        Room woods = new Room(1, "Bosco", "Sei nel bosco!");
        Room testRoom = new Room(2, "Test Room", "Stanza Test");

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

        Item cassa = new Item (2, "cassa", "Potrà contenere sicuramente qualcosa..");
        cassa.setPickupable(false);
        cassa.setOpenable(true);
        cassa.setIsContainer(true);
        cassa.getItemList().add(foglio);
        getItemList().add(cassa);

        List<Talk> talk2 = new ArrayList<>();
        Talk talk1 = new Talk();
        talk1.setSpeech("Ehi ciao come va?");
        talk1.getAnswerList().add("Bene");
        talk1.getAnswerList().add("Male");
        talk1.getAnswerList().add("Fine");
        talk1.getAnswerTrigger().add(AnswerType.GOOD);
        talk1.getAnswerTrigger().add(AnswerType.BAD);
        talk1.getAnswerTrigger().add(AnswerType.END);

        talk2.add(talk1);
        Npc alessandra = new Npc(100, "Alessandra", "Sisisi sono perfettissimissima fantasticas adhfghgksjdhgksd", talk2);
        getNpcList().add(alessandra);
        testRoom.getNpcs().add(alessandra);

        //Starting room
        setCurrentRoom(entrylevel);

    }
}
