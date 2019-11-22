package hu.restumali.twokgame.ui;

import hu.restumali.twokgame.gamelogic.*;
import hu.restumali.twokgame.gamelogic.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);

        Board test = new Board();
        test.printBoard();

        System.out.println();

        test.shiftLeft();
        test.printBoard();

        System.out.println();

        test.shiftLeft();
        test.printBoard();


    }
}
