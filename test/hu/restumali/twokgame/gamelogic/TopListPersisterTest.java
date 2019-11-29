package hu.restumali.twokgame.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TopListPersisterTest {
    Toplist tl = new Toplist();
    ToplistPersister tlp = new ToplistPersister();


    @Before
    public void setUp(){
        TopListEntry t1 = new TopListEntry(125, "Test1");
        TopListEntry t2 = new TopListEntry(2345, "Test2");
        tl.add(t1);
        tl.add(t2);
        tlp.setToplist(tl);
    }
    @Test
    public void readWriteTest(){
        tlp.write();
        tlp.read();

        tl = tlp.getToplist();

        //A toplista rendezettsege miatt visszatolteskor a sorrend mar valtozni fog.
        assertEquals(2345, tl.get(0).getScore());
        assertEquals("Test2", tl.get(0).getName());
        assertEquals(125, tl.get(1).getScore());
        assertEquals("Test1", tl.get(1).getName());
    }
}
