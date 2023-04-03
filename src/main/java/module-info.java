module com.example.smartvois {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    requires javafx.media;

    opens com.example.smartvois to javafx.fxml;
    exports com.example.smartvois;
}