package hu.restumali.twokgame.gamelogic;


/**
 * A toplistában egy bejegyzés. Nevet és hozzá tartozó értéket tárol.
 */
public class TopListEntry {
    private String name;
    private int score;

    public TopListEntry(){
    }

    public TopListEntry(int score, String name){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }

}
