package com.example.smartvois;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashController{
    public Button convert;

    @FXML
    void loadConvert() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("convert.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Convert TTS");
        stage.setScene(scene);
        stage.show();
    }


}
