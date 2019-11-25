package hu.restumali.twokgame.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TopListEntryTest {

    TopListEntry te;

    @Before
    public void setUp(){
        te = new TopListEntry(200, "Name");
    }
    @Test
    public void Test(){
        assertEquals(200, te.getScore());
        assertEquals("Name",te.getName());

        te.setName("Other Name");
        te.setScore(400);

        assertEquals(400, te.getScore());
        assertEquals("Other Name",te.getName());
    }
}
