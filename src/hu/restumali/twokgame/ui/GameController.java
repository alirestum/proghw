package hu.restumali.twokgame.ui;


import hu.restumali.twokgame.gamelogic.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {


    @FXML
    private Canvas canvas;
    @FXML
    private Pane pane;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label youwon;

    @FXML
    private Label youlost;
    private Board board = new Board();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        board.draw(gc);
        youwon.setVisible(false);
        youlost.setVisible(false);
        anchor.setVisible(false);
    }


    @FXML
    void shift(KeyEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int[] stepcounter = new int[4];
        if (event.getCode() == KeyCode.UP) {
            if (!checkGameOutput()) {
                board.shiftUp();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
            }
        } else if (event.getCode() == KeyCode.RIGHT) {
            if (!checkGameOutput()) {
                board.shiftRight();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            if (!checkGameOutput()) {
                board.shiftDown();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
            }
        } else if (event.getCode() == KeyCode.LEFT) {
            if (!checkGameOutput()) {
                board.shiftLeft();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
            }
        }
    }

    public boolean checkGameOutput() {
        if (board.gameLost()) {
            anchor.setVisible(true);
            youlost.setVisible(true);
            return false;
        } else if (board.gameWon()) {
            anchor.setVisible(true);
            youwon.setVisible(true);
            return false;
        }
        return false;
    }

}
