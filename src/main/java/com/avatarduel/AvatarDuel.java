package com.avatarduel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
    @Override
    public void start(Stage stage) throws IOException {   
        Parent windows = FXMLLoader.load(getClass().getClassLoader().getResource("com/avatarduel/fxml/MainWindow.fxml"));
        Scene scene = new Scene(windows);
        stage.setTitle("Avatar Duel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}