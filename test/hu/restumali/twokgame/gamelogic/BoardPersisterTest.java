package hu.restumali.twokgame.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BoardPersisterTest {
    Board br = new Board();
    BoardPersister bp = new BoardPersister();

    @Before
    public void setUp(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);
        br.setTile(0,0,t1);
        br.setTile(0,2,t2);
    }
    @Test
    public void readWriteTest(){
        assertEquals(2, br.getTile(0,0).getValue());
        assertEquals(4,br.getTile(0,2).getValue());

        bp.setBoard(br);
        bp.write("bptest");
        bp.read("bptest.json");
        br = bp.getBoard();

        assertEquals(2, br.getTile(0,0).getValue());
        assertEquals(4,br.getTile(0,2).getValue());
    }
}
