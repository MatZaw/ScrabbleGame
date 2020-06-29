package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller{
    public Button newGame;
    public Button exit;

    // Start Gry:

    public void onClick() throws IOException {
        System.out.println("Nowa gra");
        Stage window = (Stage) newGame.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("game.fxml"));
        window.setScene(new Scene(root2, 1000, 650));
    }

    // Zamkniecie gry:

    public void close(){
        Stage w = (Stage) exit.getScene().getWindow();
        w.close();
    }
}
