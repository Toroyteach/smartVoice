package com.example.smartvois;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    final String url = "jdbc:mysql://localhost:3306/smartvoices_db";
    final String user = "root";
    final String pass = "password";


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void Login()  {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("root")) {
            //success login
            System.out.println("Successful login");
            //code to open admin
            //TODO: and to close the login panel
            loadAdmin();
        } else if (checkRecord(username, password)) {
            if (checkRecord(username, password)) {
                // Load the Dashboard FXML
                loadDashboard();
            } else {
                // Display a popup instructing the user to register
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You need to register to access the system.");
                alert.showAndWait();
            }
        }
    }




    @FXML
    public  void signup(Event e) {
        String username = usernameField.getText();
        String password = passwordField.getText();


        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sql = "INSERT INTO users(user_name, pass_word)VALUES(?,?)";
            try (PreparedStatement signup = conn.prepareStatement(sql)) {
                signup.setString(1, username);
                signup.setString(2, password);
                signup.executeUpdate();
                System.out.println("Credentials submitted");
                int rowsAffected = signup.executeUpdate();
                conn.close();
                if(rowsAffected==1){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root = loader.load();

                    // create a new stage to display the new FXML file
                    Stage stage = new Stage();
                    stage.setTitle("My Dashboard");
                    stage.setScene(new Scene(root));
                    stage.show();

                    // close the current window
                    ((Node) (e.getSource())).getScene().getWindow().hide();
                }
            } catch (IOException i) {
                throw new RuntimeException(i);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    boolean checkRecord(String username, String password){
        boolean exists;

        try (Connection conn1 = DriverManager.getConnection(url, user, pass)) {
            String checkRecord = "SELECT * FROM users WHERE user_name=? AND pass_word=?";
            PreparedStatement login = conn1.prepareStatement(checkRecord);
            login.setString(1, username);
            login.setString(2, password);
            ResultSet rs = login.executeQuery();
            exists = rs.next();
            if(exists){
                System.out.println("They exist");
            }
            return exists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();

            // create a new stage to display the new FXML file
            Stage stage = new Stage();
            stage.setTitle("My Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root = loader.load();

            // create a new stage to display the new FXML file
            Stage stage = new Stage();
            stage.setTitle("My Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
