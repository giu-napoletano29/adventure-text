package di.uniba.map.game.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Npc {
    private int hp;

    private String name;

    private String description;

    private boolean enemy = false;

    private List<Talk> talk;

    public Npc(int hp, String name, String description, List<Talk> talk) {
        this.hp = hp;
        this.name = name;
        this.description = description;
        this.talk = talk;
    }

    public void talking(){
        Scanner scanner = new Scanner(System.in);
        int answer;

        for(int i = 0; i<talk.size(); i++){
            System.out.println(name + ": " + talk.get(i).getSpeech());
            System.out.println("Come rispondi?");
            for(int j = 0; j<talk.get(i).getAnswerList().size(); j++){
                System.out.println(j+1 + ": " + talk.get(i).getAnswerList().get(j));
            }
            answer = Integer.parseInt(scanner.nextLine());  //TODO: aggiungere controllo sull'input

            if(talk.get(i).getAnswerTrigger().get(answer-1) == AnswerType.END){
                System.out.println("Tu: Arrivederci.");
                System.out.println(name + ": " + "Allora è tutto.");
                break;
            }else{
                System.out.println("Tu: " + talk.get(i).getAnswerList().get(answer-1));
                //System.out.println("Altro trigger");
                break;
            }

        }
    }

    public String getName() {
        return name;
    }
}
