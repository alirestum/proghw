package hu.restumali.twokgame.gamelogic;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A toplistát tároló osztály. TopListEntry-kből álló lista.
 */
public class Toplist {

    private ArrayList<TopListEntry> scores= new ArrayList();

    public Toplist(){

    }

    /**
     * TopListEntry hozzáadása.
     * @param e A hozzáadandó entry.
     */
    public void add(TopListEntry e){
        scores.add(e);
        scores.sort(Comparator.comparing(TopListEntry::getScore, Comparator.reverseOrder()));
    }

    /**
     * Adott indexű entry visszaadása.
     * @param i A kívánt index.
     * @return Az adott entry amivel vissza kell térni.
     */
    public TopListEntry get(int i){
        return scores.get(i);
    }

    public int size(){
        return scores.size();
    }


    public ArrayList<TopListEntry> getScores() {
        return scores;
    }
}
