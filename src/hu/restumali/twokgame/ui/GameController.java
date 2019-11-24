package hu.restumali.twokgame.ui;


import hu.restumali.twokgame.gamelogic.Board;
import hu.restumali.twokgame.gamelogic.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        board.draw(gc);
    }


    @FXML
    private Canvas canvas;
    @FXML
    private Pane pane;

    private Board board= new Board();
   // private AnchorPane ar = new AnchorPane();




  /*  @FXML
    void halo(MouseEvent event) throws IOException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Tile t = new Tile(4);

    }*/

    @FXML
    void shift(KeyEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (event.getCode() == KeyCode.UP){
            board.shiftUp();
            gc.clearRect(0,0,600,400);
            board.draw(gc);
        } else if (event.getCode() == KeyCode.RIGHT){
            board.shiftRight();
            gc.clearRect(0,0,600,400);
            board.draw(gc);
        } else if (event.getCode() == KeyCode.DOWN){
            board.shiftDown();
            gc.clearRect(0,0,600,400);
            board.draw(gc);
        } else if (event.getCode() == KeyCode.LEFT){
            board.shiftLeft();
            gc.clearRect(0,0,600,400);
            board.draw(gc);
        }
    }

}
