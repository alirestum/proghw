package hu.restumali.twokgame.ui;


import hu.restumali.twokgame.gamelogic.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private Label scorelabel;

    @FXML
    private TextField namefield;

    private Board board;
    private boolean createNewBoard = true;
    private Toplist toplist= new Toplist();
    private ToplistPersister toplistPersister = new ToplistPersister();
    private BoardPersister bp = new BoardPersister();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        board = new Board();
        board.draw(gc);
        youwon.setVisible(false);
        youlost.setVisible(false);
        anchor.setVisible(false);
        namefield.setVisible(false);
        scorelabel.setText("0");
    }


    @FXML
    void control(KeyEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int[] stepcounter = new int[4];
        if (event.getCode() == KeyCode.UP) {
            if (!checkGameOutput()) {
                board.shiftUp();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
                scorelabel.setText(Integer.toString(board.getScore()));
            }
        } else if (event.getCode() == KeyCode.RIGHT) {
            if (!checkGameOutput()) {
                board.shiftRight();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
                scorelabel.setText(Integer.toString(board.getScore()));
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            if (!checkGameOutput()) {
                board.shiftDown();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
                scorelabel.setText(Integer.toString(board.getScore()));
            }
        } else if (event.getCode() == KeyCode.LEFT) {
            if (!checkGameOutput()) {
                board.shiftLeft();
                gc.clearRect(0, 0, 600, 400);
                board.draw(gc);
                scorelabel.setText(Integer.toString(board.getScore()));
            }
        } else if (event.getCode() == KeyCode.ESCAPE){
            Pane mainroot = null;
            try {
                mainroot = (Pane) FXMLLoader.load(Main.class.getResource("menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene newscene = new Scene(mainroot);
            Stage primarywindow = (Stage) (((Node) event.getSource()).getScene().getWindow());
            primarywindow.setScene(newscene);
            primarywindow.show();
            mainroot.requestFocus();
        } else if (event.getCode() == KeyCode.S){
            bp.setBoard(board);
            bp.write("test");
        }
    }

    public void addToToplist(String name){
        if (!toplistPersister.read()){
            toplist = new Toplist();
        } else {
            toplist = toplistPersister.getToplist();
        }
        toplist.add(new TopListEntry(board.getScore(), name));
        toplistPersister.setToplist(toplist);
        toplistPersister.write();
    }

    @FXML
    public   void setUserName(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            addToToplist(namefield.getText());
            namefield.setVisible(false);
        }
    }

    public boolean checkGameOutput() {
        if (board.gameLost()) {
            anchor.setVisible(true);
            youlost.setVisible(true);
            namefield.setVisible(true);
            return false;
        } else if (board.gameWon()) {
            anchor.setVisible(true);
            youwon.setVisible(true);
            namefield.setVisible(true);
            return false;
        }
        return false;
    }

    public void transferBoard(Board br){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.createNewBoard = false;
        this.board = br;
        this.board.draw(gc);
    }

}
