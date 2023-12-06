package com.example.demo1;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class main_menu {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login scene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}