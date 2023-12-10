package com.example.demo1;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.example.demo1.Main.id;
import static com.example.demo1.Main.users;
import static com.example.demo1.Main.posts;

public class Profile {
    @FXML
    private HBox box;
    private String post ;
    @FXML
    private TextArea txt;
    @FXML
    private RadioButton rbutton;
    @FXML
    private Button submit;
    private static boolean priv;
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
public void ispriv(ActionEvent event) {
    if(rbutton.isSelected()) {
        priv=true;}
}
//public void enterpost(ActionEvent event)
//{
//post = txt.getText();
//
//}
public void submit(ActionEvent event)
{
   // submit.setOnAction(e -> {
        post = txt.getText();
    users.get(id).addPost(post,priv);
    showAlert(Alert.AlertType.INFORMATION,"post saved","the post succsefuly posted");
    txt.clear();
    users.get(id).getPosts();
}
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        ButtonType closeButton = new ButtonType("Close");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(closeButton);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStyleClass().add("my-alert");
        dialogPane.getStyleClass().remove("alert");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        Button closeButtonNode = (Button) alert.getDialogPane().lookupButton(closeButton);
        closeButtonNode.setOnAction(e -> stage.close());
        alert.showAndWait();
    }
}