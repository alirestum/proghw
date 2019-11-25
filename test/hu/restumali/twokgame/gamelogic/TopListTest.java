package hu.restumali.twokgame.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TopListTest {
    Toplist tl;
    TopListEntry te1;
    TopListEntry te2;

    @Before
    public void setUp(){
        tl = new Toplist();
        te1 = new TopListEntry(200, "Name");
        te2 = new TopListEntry(400, "Other Name");
    }

    @Test
    public void Test(){
        tl.add(te1);
        tl.add(te2);
        assertEquals(400, tl.get(0).getScore());
        assertEquals("Other Name", tl.get(0).getName());

        assertEquals(200, tl.get(1).getScore());
        assertEquals("Name", tl.get(1).getName());
    }
}
