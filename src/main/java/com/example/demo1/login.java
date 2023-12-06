package com.example.demo1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.PasswordField;
public class login {
    private static String username;
    private static String password;
    @FXML
private TextField usernameFeild;
    @FXML
    private PasswordField passwordField;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchtoRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("register scene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private  void showAlert(javafx.scene.control.Alert.AlertType alertType, String title, String content) {
        javafx.scene.control.Alert alert = new Alert(alertType);
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
public void getusername() {
    username = usernameFeild.getText();
    if (username.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Empty username", "username is empty \n please enter the username again");
    }
}
    public void getpassword() {
        password = passwordField.getText();
        if (password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Empty password", "password is empty \n please enter the password again");
        }
    }
    private Stage stage1;
    private Scene scene1;
    private Parent root1;
     public static   boolean  validation = false;
    public void login(ActionEvent event) throws IOException {
        BufferedReader accountReader = new BufferedReader(new FileReader("AccountsData.txt"));
        String checkingInformation = (username + "/" + password).trim();
        String data;
        while ((data = accountReader.readLine()) != null) {
            if (checkingInformation.equals(data.trim())) {
                validation = true;
                break;
            }
        }
        accountReader.close();
        if (validation) {
            showAlert(Alert.AlertType.INFORMATION, "Successfully logged in", "Your login was successful");
            switchtomainmenu(event);
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed login", "The username and password did not match. Please check your information and try again");
            usernameFeild.clear();
            passwordField.clear();
        }
    }

    public void switchtomainmenu(ActionEvent  event) throws IOException {
        root1 = FXMLLoader.load(getClass().getResource("main menu.fxml"));
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }
}