package hu.restumali.twokgame.ui;

import hu.restumali.twokgame.gamelogic.Board;
import hu.restumali.twokgame.gamelogic.BoardPersister;
import hu.restumali.twokgame.gamelogic.Toplist;
import hu.restumali.twokgame.gamelogic.ToplistPersister;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button newgame;
    @FXML
    private Button loadgame;
    @FXML
    private Button toplist;
    @FXML
    private ListView listview;// = new ListView();
    @FXML
    private TextField loadgamefield;

    private ToplistPersister tw = new ToplistPersister();
    private Toplist tl;
    private BoardPersister bp = new BoardPersister();
    private Board br;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listview.setVisible(false);
        loadgamefield.setVisible(false);
    }

    /**
     * Új játékot indít.
     * @param event Az esemény ami tüzeli a függvényt.
     */
    @FXML
    void newgame(MouseEvent event) {
        loadGameScene(event, br);
    }


    /**
     * Betölti a játék nézetet.
     * @param event Az esemény ami tüzeli a függvényt.
     * @param br A tábla amit be kell tölteni.
     */
    public void loadGameScene(Event event, Board br){
        Pane mainroot = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));

        try {
            mainroot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.transferBoard(br);

        Scene newscene = new Scene(mainroot);
        Stage primarywindow = (Stage) (((Node) event.getSource()).getScene().getWindow());
        primarywindow.setScene(newscene);
        primarywindow.show();
        mainroot.requestFocus();
    }


    /**
     * Betölti a toplistát, majd kiirja a TextBox-ba.
     * @param event Az esemény ami tüzeli a függvényt.
     */
    @FXML
    void gettoplist(MouseEvent event){
        tw.read();
        tl = tw.getToplist();
        listview.setVisible(true);
        for (int i = 0; i < tl.size(); i++) {
            listview.getItems().add(tl.get(i).toString());
            System.out.println(tl.get(i).toString());
        }
    }


    /**
     * Betölti a korábban mentett játékokat, ebből a felhasználó választhat hogy melyiket szeretné betölteni.
     * @param event Az esemény ami tüzeli a függvényt.
     */
    @FXML
    void loadgame(MouseEvent event) {
        File f = new File(".");
        File[] list = f.listFiles();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getName().endsWith(".json") && !list[i].getName().equals("toplist.json") && !list[i].getName().equals("bptest.json")){
                listview.getItems().add(list[i].getName().replace(".json", ""));
                listview.setVisible(true);
                loadgamefield.setVisible(true);
            }
        }
    }

    /**
     * Betölti a kiválasztott mentést.
     * @param event Az esemény ami tüzeli a függvényt.
     */
    @FXML
    void loadSavedGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            bp.read(loadgamefield.getText()+".json");
            br = bp.getBoard();
            loadGameScene(event, this.br);
        }
    }

    /**
     * A kilistázott mentések közül az aktuálisan kiválasztottat beleteszi a TextBox-ba.
     * @param event Az esemény ami tüzeli a függvényt.
     */
    @FXML
    void getSelectedListItem(MouseEvent event){
        String selected = String.valueOf(listview.getSelectionModel().getSelectedItems());
        loadgamefield.textProperty().setValue(selected.substring(1,selected.length()-1));
        loadgamefield.requestFocus();
    }
}
