package hu.restumali.twokgame.gamelogic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * A pálya fájlba írásáért felelős osztály.
 */
public class BoardPersister {

    private Board board = new Board();
    private ObjectMapper mapper = new ObjectMapper();

    public BoardPersister(){

    }

    public BoardPersister(Board b1){
        this.board = b1;
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);

    }

    /**
     * Kiírja a pályát a megadott nevű json fájlba.
     * @param filename A fájl neve amibe ki kell írni a pályát.
     */
    public void write(String filename){
        try {
            mapper.writerWithType(Board.class).writeValue(new File(filename +".json"), board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visszaolvassa a pályát a megadott nevű json fájlból.
     * @param filename A visszaolvasandó json fájl neve.
     * @return A beolvasás igaz/hamis értéke.
     */
    public boolean read(String filename){
        File f = new File(".");
        File [] list = f.listFiles();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getName().equals(filename)){
                try {
                    board = mapper.readValue(new File(filename), Board.class);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
