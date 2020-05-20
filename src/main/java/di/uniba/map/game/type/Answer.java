package di.uniba.map.game.type;
//TODO: Look for possible conversion with HashMap
public class Answer {
    private String answer = new String();

    private Talk warp = null;

    private triggerInterface triggerReference = null;

    //insert trigger to answer

    public String getAnswer(){return answer;}

    public void setAnswer(String answer){this.answer = answer;}

    public Talk getWarp(){return warp;}

    public void setWarp(Talk warp){this.warp = warp;}

    @FunctionalInterface
    public static interface triggerInterface{
        void trigger();
    }

    public void setTriggerReference(triggerInterface T){this.triggerReference = T;}

    public triggerInterface getTriggerReference(){
        return this.triggerReference;
    }
}
