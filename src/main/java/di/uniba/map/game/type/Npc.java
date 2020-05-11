package di.uniba.map.game.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Npc extends Character{

    private boolean isEnemy = false;

    private boolean isGod = false;

    private boolean isAttacking = false;

    private boolean isSpeakable = false;

    private List<Talk> talk;

    public Npc(int hp, String name, String description) {
        super(hp, name, description);
    }

    public void talking(){
        Scanner scanner = new Scanner(System.in);
        int answer, temp = 0;

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
                        i = talk.size();
                        break;
                    }else if(talk.get(i).getAnswerTrigger().get(answer - 1) == AnswerType.DESC){
                        System.out.println("Tu: " + talk.get(i).getAnswerList().get(answer - 1));
                        System.out.println(name + ": " + description);
                        i -= 1;
                        break;
                    }else if(talk.get(i).getAnswerTrigger().get(answer - 1) == AnswerType.BACK){
                        System.out.println("Tu: " + talk.get(i).getAnswerList().get(answer - 1));
                        i = temp;
                        break;
                    }
                    else {
                        temp = i-1;
                        System.out.println("Tu: " + talk.get(i).getAnswerList().get(answer - 1));
                        if(talk.get(i).getAnswerTrigger().get(answer - 1) == AnswerType.GOOD){
                            for(int j = i; j<talk.size(); j++){
                                if(talk.get(j).getSpeechtrigger() == AnswerType.GOOD){
                                    i = j-1;
                                    error = false;
                                    break;
                                }
                            }
                        }else if(talk.get(i).getAnswerTrigger().get(answer - 1) == AnswerType.BAD){
                            for(int j = i; j<talk.size(); j++){
                                if(talk.get(j).getSpeechtrigger() == AnswerType.BAD){
                                    i = j-1;
                                    error = false;
                                    break;
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    //System.out.println("Error: " + e);
                    System.out.println(name + ": " + "Dammi una risposta sensata.");
                    error = true;
                }

            }

        }
    }

    public boolean getAttacking(){return this.isAttacking;};

    public void setAttacking(boolean attacking){
        this.isAttacking = attacking;
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

    public boolean getEnemy(){return this.isEnemy;}

    public void setTalk(List<Talk> talk){
        this.talk = talk;
    }

    public boolean getGod(){ return isGod;}

    public void setGod(boolean god){ isGod = god;}
}
