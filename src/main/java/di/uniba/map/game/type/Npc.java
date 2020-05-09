package di.uniba.map.game.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Npc extends Character{

    private boolean isEnemy = false;

    private boolean isSpeakable = false;

    private List<Talk> talk;

    public Npc(int hp, String name, String description) {
        super(hp, name, description);
    }

    /*
    public Npc(int hp, String name, String description) {
        this.hp = hp;
        this.name = name;
        this.description = description;
    }*/

    public void talking(){
        Scanner scanner = new Scanner(System.in);
        int answer;

        for(int i = 0; i<talk.size(); i++){
            System.out.println(name + ": " + talk.get(i).getSpeech());
            for(int j = 0; j<talk.get(i).getAnswerList().size(); j++){
                System.out.println(j+1 + ": " + talk.get(i).getAnswerList().get(j));
            }
            boolean error = true;
            while(error) {
                try{
                    answer = Integer.parseInt(scanner.nextLine());
                    if (talk.get(i).getAnswerTrigger().get(answer - 1) == AnswerType.END) {
                        System.out.println("Tu: Arrivederci.");
                        System.out.println(name + ": " + "Allora è tutto.");
                        break;
                    }else if(talk.get(i).getAnswerTrigger().get(answer - 1) == AnswerType.DESC){
                        System.out.println("Tu: " + talk.get(i).getAnswerList().get(answer - 1));
                        System.out.println(name + ": " + description);
                        i -= 1;
                        break;
                    }
                    else {
                        System.out.println("Tu: " + talk.get(i).getAnswerList().get(answer - 1));
                        //System.out.println("Altro trigger");
                        error = false;
                    }
                }catch (Exception e){
                    //System.out.println("Error: " + e);
                    System.out.println(name + ": " + "Dammi una risposta sensata.");
                    error = true;
                }

            }

        }
    }

    public void setSpeakable(boolean speakable) {
        isSpeakable = speakable;
    }

    public boolean getSpeakable(){
        return isSpeakable;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public void setTalk(List<Talk> talk){
        this.talk = talk;
    }
}
