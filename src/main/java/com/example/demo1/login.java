package com.example.demo1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

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

import static com.example.demo1.Main.posts;
import static com.example.demo1.Main.users;

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
       public static void loadvariables() throws IOException {
           BufferedReader accountReader = new BufferedReader(new FileReader("AccountsData.txt"));
           String line;
           while ((line = accountReader.readLine()) != null) {
               // Split the line into respective fields
               String[] fields = line.split(",");
               // Extract the user information from the fields
               String userName = fields[0];
               String password = fields[1];
               String email = fields[2];
               String phoneNumber = fields[3];
               String gender = fields[4];
               String birthDate = fields[5];
               //Array [] int
               User user = new User(userName, gender, email, password, birthDate, phoneNumber);
               Main.users.add(user);
           }
       }public void login(ActionEvent event) throws IOException {
        for (User x: Main.users)
        {
            if (username.equals(x.getUserName())&&password.equals(x.getPassword())) {
                validation=true;
                break;}
        }
       // accountReader.close();
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
        root1 = FXMLLoader.load(getClass().getResource("my profile.fxml"));
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
        stage1.setOnCloseRequest(e -> {
            e.consume();
            confirmExit();
            Saving.save(users);
            Saving.saveposts(posts);
        });
    }
    private void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("Any unsaved changes will be lost.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }
}