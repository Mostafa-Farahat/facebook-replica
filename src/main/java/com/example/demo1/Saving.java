package com.example.demo1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Saving {
    public static void save(String username, String email, String password, String phoneNumber, String gender, String birthDate) {
        ArrayList<String> accountData = new ArrayList<>();
        accountData.add(username + "/" + password);
        accountData.add(email);
        accountData.add(phoneNumber);
        accountData.add(gender);
        accountData.add(birthDate);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("AccountsData.txt", true))) {
            for (String data : accountData) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }
}

