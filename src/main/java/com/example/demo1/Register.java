package com.example.demo1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.demo1.Main.posts;
import static com.example.demo1.Main.users;
public  class Register implements Initializable {
    @FXML
    private DatePicker bd;
    private Stage stage1;
    private Scene scene1;
    private Parent root1;
    @FXML
    private ChoiceBox<String> mychoicebox;
    @FXML
    private TextField phonenumberfeild;
    private static String phonenumber;
    @FXML
    private TextField emailfeild;
    private static String email;
    @FXML
    private TextField userfeild;
    private static String username;
    private static String gender;
    private final String[] genders = {"male", "female"};
    private static String birth_date;
    private static String password;
    @FXML
    private TextField passwordfeild;
    public void setEmail(ActionEvent event) {
        email = emailfeild.getText();
        if (!email.contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Invalid email", "email must contains @ \n please enter the password again");
            email = emailfeild.getText();
        } else if (email.startsWith("@")) {
            showAlert(Alert.AlertType.ERROR, "Invalid email", "email must contains username \n please enter the password again");
            email = emailfeild.getText();
        } else if (checkFound(email)) {
            showAlert(Alert.AlertType.ERROR, "Already registered", "the email you choose is already registerd \n Please enter the email again.");
            emailfeild.clear();
        }
    }

    public void setPhonenumber(ActionEvent event) {
        phonenumber = phonenumberfeild.getText();
        if (phonenumber.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Empty phone number", "phone number is empty \n please enter the phone number again");
        } else if (phonenumber.length() != 11) {
            showAlert(Alert.AlertType.ERROR, "Invalid phone number", "phone number length must be 11 \n please enter the phone number again");
            phonenumberfeild.clear();
        } else if (!phonenumber.startsWith("0")) {
            showAlert(Alert.AlertType.ERROR, "Invalid phone number", "phone number must start with 0 \n please enter the phone number again");
            phonenumberfeild.clear();
        }
//        else if (phonenumber.charAt(1) != 1 ||( phonenumber.charAt(2) != 0 && phonenumber.charAt(2) != 1 && phonenumber.charAt(2) != 2 && phonenumber.charAt(2) != 5)) {
//            showAlert(Alert.AlertType.ERROR, "Invalid Service provider", "phone number service providers in egypt either ( 010 / 011 / 012 /015 ) \n please enter the phone number again");
//            phonenumberfeild.clear();
//        }
        else if (checkFound(phonenumber)) {
            showAlert(Alert.AlertType.ERROR, "Already registered", "the phonenumber you choose is already registerd /n Please enter another phonenumber again.");
            phonenumberfeild.clear();
        }
    }

    public void reg(ActionEvent e) throws IOException {

        User x = new User(getUsername(), getGender(), getEmail(), getPassword(), getBirth_date(), getPhonenumber());
        users = User.getUserStore();
        Main.id = x.getId();
        root1 = FXMLLoader.load(getClass().getResource("my profile.fxml"));
        stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
        stage1.setOnCloseRequest(event -> {
            e.consume();
            confirmExit();
            Saving.save(users);
            Saving.saveposts(posts);
        });
    }

    public void setPassword(ActionEvent event) {
        password = passwordfeild.getText();
        if (password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Invalid password", "password must be at least 5 characters \n please enter the password again");
            passwordfeild.clear();
        } else if (password.length() < 5) {
            showAlert(Alert.AlertType.ERROR, "Invalid password", "password must be at least 5 characters \n please enter the password again");
            passwordfeild.clear();
        }
    }

    public void setUsername(ActionEvent event) {
        username = userfeild.getText();
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Empty Username", "Username is Empty /n Please enter the username again.");
            userfeild.clear();
        } else if (username.length() < 5) {
            showAlert(Alert.AlertType.ERROR, "Invalid Username", "Username must be at least 5 characters /n Please enter the username again.");
            userfeild.clear();
        } else if (containsNumber(username)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Username", "Username cannot have numbers /n Please enter the username again.");
            userfeild.clear();
        } else if (checkFound(username)) {
            showAlert(Alert.AlertType.ERROR, "Already registered", "the username you choose is already registerd /n Please enter the username again.");
            userfeild.clear();
        }
    }
    private boolean containsNumber(String str) {
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
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

    public void initialize(URL arg0, ResourceBundle arg1) {
        mychoicebox.getItems().addAll(genders);
        mychoicebox.setOnAction(this::getgender);}
    public void getgender(ActionEvent event) {gender = mychoicebox.getValue();}
    public void getDate(ActionEvent event) {
        LocalDate dateofbirth = bd.getValue();
        LocalDate currentDate = LocalDate.now();
        if (Period.between(dateofbirth, currentDate).getYears() < 13) {
            showAlert(Alert.AlertType.WARNING, "Age restriction", "you have to be Above 13 years to use the app");
            bd.setValue(null);
        } else {
            birth_date = dateofbirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }
    public static String getPhonenumber() {return phonenumber;}
    public static String getEmail() {return email;}
    public static String getUsername() {return username;}
    public static String getGender() {return gender;}
    public static String getBirth_date() {return birth_date;}

    public static String getPassword() {return password;}

    private boolean checkFound(String input) {
        for (User x : users) {
            if (x.getEmail().equals(input) || x.getPhoneNumber().equals(input) || x.getUserName().equals(input)) {
                return true;
            }
        }
        return false;
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
