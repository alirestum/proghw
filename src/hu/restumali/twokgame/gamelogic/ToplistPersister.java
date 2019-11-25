package hu.restumali.twokgame.gamelogic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Toplista fájlba írására szolgáló osztály.
 */
public class ToplistPersister {

    private Toplist toplist = new Toplist();
    private ObjectMapper mapper = new ObjectMapper();

    public ToplistPersister() {

    }

    public ToplistPersister(Toplist tl) {
        this.toplist = tl;
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    }

    /**
     * A toplista fájlba írása.
     */
    public void write() {
        try {
            mapper.writeValue(new File("toplist.json"), toplist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A toplista visszaolvasása.
     * @return A visszaolvasás sikeressége.
     */
    public boolean read() {
        File f = new File(".");
        File[] list = f.listFiles();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getName().equals("toplist.json")) {
                try {
                    toplist = mapper.readValue(new File("toplist.json"), Toplist.class);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        return false;
    }

    public Toplist getToplist() {
        return toplist;
    }

    public void setToplist(Toplist tl) {
        this.toplist = tl;
    }
}
