package com.example.smartvois;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ConvertController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea textArea;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button convertButton;

    @FXML
    private Button shareButton;

    @FXML
    private Button exitButton;

    private FileChooser fileChooser;
    private MediaPlayer mediaPlayer;

    public void PlayButtonAction() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.m4v", "*.mov", "*.flv", "*.webm"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        mediaView.setMediaPlayer(mediaPlayer);
    }

    @FXML
    private void ConvertButtonAction() {
        String text = textArea.getText();
        // Convert text
        // ...
    }

    @FXML
    private void ShareButtonAction() {
        // Share converted video
        // ...
    }

    @FXML
    private void ExitButtonAction() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void OpenButtonAction() {
        File file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }

}
