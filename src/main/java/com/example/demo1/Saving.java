package com.example.demo1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Saving {
    public static void save(ArrayList<User> users) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("AccountsData.txt", true))) {
                    for (User user : Main.users) {
                        String username = user.getUserName();
                        String password = user.getPassword();
                        String email = user.getEmail();
                        String phoneNumber = user.getPhoneNumber();
                        String gender = user.getGender();
                        String birthDate = user.getBirth_date();
                        Post[] posts = user.getPosts();
                        writer.write(username+",");
                        writer.write(password+",");
                        writer.write(email+",");
                        writer.write(phoneNumber+",");
                        writer.write(gender+",");
                        writer.write(birthDate+",");
                        writer.write(posts+",");
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Error saving user data: " + e.getMessage());
                }
            }
    public static void saveposts(ArrayList<Post> posts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Posts.txt", true))) {
            for (Post post: Main.posts) {
                String x=  post.getContent();
                int xx = post.getPublisherId();
                boolean xxx=post.isPublic();
                int z=1 ;
              if (xxx){
                  z=1;
              } else if (!xxx) {
                  z=0;
              }
                writer.write(x+",");
              //  writer.newLine();
                writer.write(String.valueOf(xx)+",");
                //writer.newLine();
                writer.write(String.valueOf(z)+",");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }
}
