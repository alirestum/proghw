package hu.restumali.twokgame.ui;


import hu.restumali.twokgame.gamelogic.Board;
import hu.restumali.twokgame.gamelogic.Tile;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;


public class GameController {

    @FXML
    private GridPane gridPane;
    private Board board= new Board();
   // private AnchorPane ar = new AnchorPane();

    void drawBoard(){
        for (int row=0; row<4; row++){
            for (int col=0; col<4; col++){
                System.out.println("sor: " + row + " oszlop: "  + col);
                gridPane.add(board.getTile(row, col), row, col);
            }
        }
    }

    @FXML
    void halo(MouseEvent event) throws IOException {
        drawBoard();
    }

    @FXML
    void masik(ScrollEvent event) {
      
    }

    @FXML
    void shift(KeyEvent event) {
        board.shiftUp();
        drawBoard();
        System.out.println("fel");
        if (event.getCode() == KeyCode.UP){

        } else if (event.getCode() == KeyCode.DOWN){
            board.shiftDown();
            System.out.println("le");
            drawBoard();
        } else if (event.getCode() == KeyCode.LEFT){
            board.shiftLeft();
            drawBoard();
            System.out.println("balra");
        } else if (event.getCode() == KeyCode.RIGHT){
            board.shiftRight();
            drawBoard();
            System.out.println("jobbra");
        }
    }

}
