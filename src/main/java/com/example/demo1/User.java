package com.example.demo1;
import java.util.Date;
public class User {
    private  String username;
    private  String gender;
    private  String email;
    private  String password;
    private  String birth_date;
    //private int age;
    private static int usercounter=0;
    private  int id ;
    private String phone_number;
    public User(String username, String gender, String email, String password, String birth_date, String phone_number) {
        this.username = username;
        this.gender = gender;
        this.email = email;
        id = usercounter;
        this.password = password;
        usercounter++;
        this.birth_date=birth_date;
        this.phone_number = phone_number;
    }
    public String getUsername() {
        return username;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getBirth_date() {
        return birth_date;
    }
    public String getPhone_number() {
        return phone_number;
    }
}
