package hu.restumali.twokgame.gamelogic;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class TileTest {
    private Tile tile;

    @Test
    public void BlankTile(){
        tile = new Tile();
        assertEquals(0, tile.getValue());
        assertEquals("#F0ECE3", tile.getColor());
        assertTrue("Tile is mergeable", tile.getMergeable());
    }

    @Test
    public void Number2_Tile(){
        tile = new Tile(2);
        assertEquals(2, tile.getValue());
        assertEquals("#DFD4C0", tile.getColor());
        assertTrue("Tile is mergeable", tile.getMergeable());
    }

    @Test
    public void Number4_Tile(){
        tile = new Tile(4);
        assertEquals(4, tile.getValue());
        assertEquals("#D8CBB3", tile.getColor());
        assertTrue("Tile is mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number4_Tile(){
        tile = new Tile(4);
        tile.mergeTile();
        assertEquals(8, tile.getValue());
        assertEquals("#E7C3A3", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number8_Tile(){
        tile = new Tile(4);
        tile.mergeTile();
        tile.mergeTile();
        assertEquals(16, tile.getValue());
        assertEquals("#DFAD81", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number16_Tile(){
        tile = new Tile(4);
        for (int i = 0; i < 3; i++) {
            tile.mergeTile();
        }
        assertEquals(32, tile.getValue());
        assertEquals("#E4CC83", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number32_Tile(){
        tile = new Tile(4);
        for (int i = 0; i < 4; i++) {
            tile.mergeTile();
        }
        assertEquals(64, tile.getValue());
        assertEquals("#DBB955", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number64_Tile(){
        tile = new Tile(4);
        for (int i = 0; i < 5; i++) {
            tile.mergeTile();
        }
        assertEquals(128, tile.getValue());
        assertEquals("#E3AC9C", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }

    @Test
    public void Merge_Number1024_Tile(){
        tile = new Tile(4);
        for (int i = 0; i < 9; i++) {
            tile.mergeTile();
        }
        assertEquals(2048, tile.getValue());
        assertEquals("#D65133", tile.getColor());
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }


    @Test
    public void Set_Mergeable(){
        tile = new Tile();
        tile.setMergeable(false);
        assertFalse("Tile is not mergeable", tile.getMergeable());
    }
}
