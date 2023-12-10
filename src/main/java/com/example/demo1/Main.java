package com.example.demo1;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load((getClass().getResource("login scene.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Facebook");
            stage.setScene(scene);
            stage.setResizable(false);
          stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        login.loadvariables();
        launch(args);}
    public static int id;
    public static ArrayList<User> users = new ArrayList <User> ();
    public static ArrayList<Post> posts = new ArrayList <Post> ();
    public static ArrayList<Comment> comments = new ArrayList <> ();
    public static ArrayList<Reply> replies = new ArrayList <> ();

}
