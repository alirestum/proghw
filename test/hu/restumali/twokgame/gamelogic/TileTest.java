package hu.restumali.twokgame.gamelogic;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TileTest {
    Tile tile;

    @Test
    public void BlankTile(){
        tile = new Tile();
        assertEquals(0, tile.getValue());
        assertEquals("asd", tile.getColor());
        assertTrue("Tile is mergeable", tile.getMergeable());
    }

    @Test
    public void Number2_Tile(){
        tile = new Tile(2);
        assertEquals(2, tile.getValue());
        assertEquals("asd", tile.getColor());
        assertTrue("Tile is mergeable", tile.getMergeable());
    }

    @Test
    public void Number4_Tile(){
        tile = new Tile(4);
        assertEquals(4, tile.getValue());
        assertEquals("asd", tile.getColor());
        assertTrue("Tile is mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number4_Tile(){
        tile = new Tile(4);
        tile.mergeTile();
        assertEquals(8, tile.getValue());
        assertEquals("asd", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number8_Tile(){
        tile = new Tile(4);
        tile.mergeTile();
        tile.mergeTile();
        assertEquals(16, tile.getValue());
        assertEquals("asd", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }

    @Test
    public void Set_Mergeable(){
        tile = new Tile();
        tile.setMergeable(false);
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }
}
