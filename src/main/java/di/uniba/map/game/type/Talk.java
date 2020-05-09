package di.uniba.map.game.type;

import java.util.ArrayList;
import java.util.List;

public class Talk {

    private String speech = new String();  //Lista di frasi che il personaggio può dire

    private AnswerType speechtrigger;

    private List<String> answerList = new ArrayList<>();

    private List<AnswerType> answerTrigger = new ArrayList<>();; // Lista di risposte che triggerano azioni

    //TODO: aggiungere lista risposte di tipo BAD

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String s){
        speech = s;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public List<AnswerType> getAnswerTrigger() {
        return answerTrigger;
    }

    public AnswerType getSpeechtrigger(){return speechtrigger;}

    public void setSpeechtrigger(AnswerType a){
        speechtrigger = a;
    }
}
