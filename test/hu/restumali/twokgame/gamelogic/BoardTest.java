package hu.restumali.twokgame.gamelogic;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {
    Board board;

    @Test
    public void BlankGridTest() {
        board = new Board();
        assertEquals(0, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(3, 3).getValue());
    }

    @Test
    public void AddTileToGridTest() {
        board = new Board();
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);
        board.setTile(0, 0, t1);
        board.setTile(0, 1, t2);

        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());

    }

    @Test
    public void SwapWithZeroTest() {
        board = new Board();
        Tile t1 = new Tile();
        Tile t2 = new Tile(2);
        board.setTile(0, 0, t1);
        board.setTile(0, 1, t2);
        board.swapWithZero(0, 1, 0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(0, 1).getValue());
    }

    @Test
    public void RemoveZerosTest() {
        board = new Board();
        Tile t1 = new Tile(2);
        Tile t2 = new Tile();

        //0 2 0 0 -> 2 0 0 0
        board.setTile(0, 1, t1);
        board.removeZeros(0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //0 0 2 0 -> 2 0 0 0
        board.setTile(0, 0, t2);
        board.setTile(0, 1, t2);
        board.setTile(0, 2, t1);
        board.setTile(0, 3, t2);
        board.removeZeros(0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //0 0 0 2 -> 2 0 0 0
        board = new Board();
        board.setTile(0, 3, t1);
        board.removeZeros(0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //2 0 2 0 -> 2 2 0 0
        board.setTile(0, 2, t1);
        board.removeZeros(0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //0 2 0 2 -> 2 2 0 0
        board = new Board();
        board.setTile(0, 1, t1);
        board.setTile(0, 3, t1);
        board.removeZeros(0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //0 0 2 2 -> 2 2 0 0
        board = new Board();
        board.setTile(0, 2, t1);
        board.setTile(0, 3, t1);
        board.removeZeros(0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //2 2 0 2 -> 2 2 2 0
        board = new Board();
        board.setTile(0, 0, t1);
        board.setTile(0, 1, t1);
        board.setTile(0, 3, t1);
        board.removeZeros(0);
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(0, 1).getValue());
        assertEquals(2, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());


    }


    @Test
    public void TileMergeTest() {
        board = new Board();
        Tile t2 = new Tile(2);
        board.setTile(0, 0, t2);
        board.setTile(0, 1, t2);

        board.mergeTiles(0, 1, 0);
        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(0, 1).getValue());
        assertFalse("Tile is not mergeable", board.getTile(0, 0).getMergeable());
    }

    @Test
    public void LeftShiftTest() {
        Tile t1 = new Tile();
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);

        //2 2 0 0 -> 4 0 0 0
        board = new Board();
        board.setTile(0, 0, t2);
        board.setTile(0, 1, t2);
        board.shiftLeft();
        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //2 2 0 4 -> 4 4 0 0
        t1 = new Tile();
        t2 = new Tile(2);
        t3 = new Tile(4);
        board = new Board();
        board.setTile(0, 0, t2);
        board.setTile(0, 1, t2);
        board.setTile(0, 2, t1);
        board.setTile(0, 3, t3);
        board.shiftLeft();
        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //2 2 4 0 -> 4 4 0 0 -> 8 0 0 0
        t1 = new Tile();
        t2 = new Tile(2);
        t3 = new Tile(4);
        board = new Board();
        board.setTile(0, 0, t2);
        board.setTile(0, 1, t2);
        board.setTile(0, 2, t3);
        board.setTile(0, 3, t1);
        board.shiftLeft();
        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());
        board.shiftLeft();
        assertEquals(8, board.getTile(0, 0).getValue());
        assertEquals(0, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());


        //2 0 2 4 -> 4 4 0 0
        t1 = new Tile();
        t2 = new Tile(2);
        t3 = new Tile(4);
        board = new Board();
        board.setTile(0, 0, t2);
        board.setTile(0, 1, t1);
        board.setTile(0, 2, t2);
        board.setTile(0, 3, t3);
        board.shiftLeft();
        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(0, board.getTile(0, 2).getValue());
        assertEquals(0, board.getTile(0, 3).getValue());

        //4 0 2 2 -> 4 4 0 0
      /*  board.setTile(0,2,t2);
        board.setTile(0,3, t2);
        board.shiftLeft();
        assertEquals(4,board.getTile(0,0).getValue());
        assertEquals(4,board.getTile(0,1).getValue());
        assertEquals(0,board.getTile(0,2).getValue());
        assertEquals(0,board.getTile(0,3).getValue());
*/

    }

    @Before
    public void setBoard(){
        board = new Board(4);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);
        for (int row = 0; row<4; row++){
            for (int col = 0; col<4; col++){
                if (row % 2 == 0){
                    board.setTile(row, col, t2);
                } else {
                    board.setTile(row, col, t3);
                }
            }
        }
    }
    @Test
    public void RotationTest(){
        for (int row = 0; row<4; row++){
            for (int col = 0; col<4; col++){
                if (row % 2 == 0){
                    assertEquals(2, board.getTile(row,col).getValue());
                } else {
                    assertEquals(4, board.getTile(row,col).getValue());
                }
            }
        }

        board.rotateCntClockwise(1);
        for (int row = 0; row<4; row++){
            for (int col = 0; col<4; col++){
                if (col % 2 == 0){
                    assertEquals(2, board.getTile(row,col).getValue());
                } else {
                    assertEquals(4, board.getTile(row,col).getValue());
                }
            }
        }
        board.rotateCntClockwise(3);
        for (int row = 0; row<4; row++){
            for (int col = 0; col<4; col++){
                if (row % 2 == 0){
                    assertEquals(2, board.getTile(row,col).getValue());
                } else {
                    assertEquals(4, board.getTile(row,col).getValue());
                }
            }
        }


    }

    @Before
    public void gameLostSetUp(){
        Tile t = new Tile(2);
        board = new Board();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                board.setTile(row,col, t);
            }
        }
    }

    @Test
    public void gameLostTest(){
        assertTrue("Game lost", board.gameLost());
    }

    @Before
    public void gameWonSetUp(){
        Tile t1 = new Tile(4);
        for (int i = 0; i < 9; i++) {
            t1.mergeTile();
            t1.setMergeable(true);
        }
        board = new Board();
        board.setTile(0,0,t1);
    }

    @Test
    public void gameWonTest(){
        assertEquals(2048, board.getTile(0,0).getValue());
        assertTrue("Game Won", board.gameWon());
    }



}
