package di.uniba.map.game.type;

public class Answer {
    private String answer = new String();

    private Talk warp = null;

    public String getAnswer(){return answer;}

    public void setAnswer(String answer){this.answer = answer;}

    public Talk getWarp(){return warp;}

    public void setWarp(Talk warp){this.warp = warp;}
}
