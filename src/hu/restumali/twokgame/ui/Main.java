package hu.restumali.twokgame.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("2kGame");
        primaryStage.setScene(new Scene(root, 400, 460));
        primaryStage.show();
        root.requestFocus();

    }


    public static void main(String[] args) {
        launch(args);

       // Board test = new Board();
      //  test.printBoard();

     //   System.out.println();

      //  test.shiftLeft();
      //  test.printBoard();

     //   System.out.println();

     //   test.shiftLeft();
      //  test.printBoard();


    }
}
