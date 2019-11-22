package hu.restumali.twokgame.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    private Button newgame;

    @FXML
    void halo(MouseEvent event) {
        System.out.println("megy gefcó!");
    }
}
