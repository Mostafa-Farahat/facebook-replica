import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class LoginRegisterClass {
    private  String username;
    private  String gender;
    private  String email;
    private  String password;
    private  String birth_date;
    private String phone_number;
    Scanner in =new Scanner(System.in);
    public void register() throws IOException {
        BufferedWriter accountWriter = new BufferedWriter(new FileWriter("AcountsData.txt", true));
        System.out.println("REGISTRATION\n-Please enter username: ");
        username = in.next();
        while (true) {
            if (username.isEmpty())
            {
                System.out.println("the input is empty");
                System.out.println("Please enter username: ");
                username = in.next();
                continue;
            } else if (username.length() < 3) {
                System.out.println("the username must be more than 3 characters");
                System.out.println("Please enter username: ");
                username = in.next();
                continue;
            }
            boolean x=false;
            for (char ch : username.toCharArray()) {
                if (Character.isDigit(ch)) {
                    x=false;
                } else {
                    x=true;
                }
            }
            if (x==false)
            {
                System.out.println("the username cant be all digits");
                System.out.println("Please enter username: ");
                username = in.next();
                continue;
            } else if (x==true) {
                break;
            }
            break;
        }
        accountWriter.write("\n" + username);
        accountWriter.newLine();
        System.out.println("-Please enter your email: ");
        email = in.next();
        while (true) {
            if (!email.contains("@")) {
                System.out.println(" the email must contains @");
                System.out.println("-Please enter your email: ");
                email = in.next();
            } else if (email.isEmpty()) {
                System.out.println(" the email can't be empty");
                System.out.println("-Please enter your email: ");
                email = in.next();
            } else if (email.startsWith("@")) {
                System.out.println(" the email must begin with the  username");
                System.out.println("-Please enter your email: ");
                email = in.next();
            }// try to find another way
            else {
                break;
            }
        }
        accountWriter.write(email);
        accountWriter.write("/");
        System.out.println("-Please enter password: ");
        password = in.next();
        while (true) {
            if (password.isEmpty()) {
                System.out.println("the password mustn't be empty");
                System.out.println("-Please enter password: ");
                password = in.next();
            } else if (password.length() < 5) {
                System.out.println("the password must be more than 5 characters");
                System.out.println("-Please enter password: ");
                password = in.next();
            }
        else {
            break;
            }
        }
            accountWriter.write(password);
        accountWriter.newLine();
        System.out.println("-Please enter phone number: ");
    phone_number=in.next();
while(true)
{
    if (phone_number.isEmpty())
    {
        System.out.println("the phone number cant be empty");
        System.out.println("-Please enter phone number: ");
        phone_number = in.next();
    } else if (phone_number.length()!=11) {
        System.out.println("the phone must be 11 digits");
        System.out.println("-Please enter phone number: ");
        phone_number = in.next();
    } else if (!phone_number.startsWith("0")) {
        System.out.println("the phone number in egypt must start with 0");
        System.out.println("-Please enter phone number: ");
        phone_number = in.next();
    }else if(phone_number.charAt(2)!=0&&phone_number.charAt(2)!=1&&phone_number.charAt(2)!=2&&phone_number.charAt(2)!=5){
            System.out.println("the phone number providers in egypt either start with 010 or 011 or 012 or 015");
            System.out.println("-Please enter phone number: ");
        phone_number = in.next();
    } else {break;}
}
        accountWriter.write(phone_number);
        accountWriter.newLine();
        System.out.println("-Please enter your gender: ");
        gender = in.next();
        while (true){
            gender=gender.toLowerCase();
            if (gender.equals("male")||gender.equals("female"))
            {
                break;
            }
            else {
                System.out.println("the gender you entered is invaled");
                System.out.println("-Please enter your gender: ");
                gender = in.next();
                continue;
            }
        }
        accountWriter.write(gender);
        accountWriter.newLine();
        System.out.print("Enter your birthdate (day/Month/year): ");
        birth_date = in.next();
while(true) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(birth_date, formatter);
        break;
    } catch (Exception e) {
        System.out.println("Invalid date format. Please enter the date in day/month/year format.");
        birth_date = in.next();

    }
}
        accountWriter.write(birth_date);
        accountWriter.newLine();
            accountWriter.write("-----------------------------------------------");
            accountWriter.close();
            System.out.println("Register successful!");
        }
        public void Login () throws IOException
        {
            while (true) {
                BufferedReader accountReader = new BufferedReader(new FileReader("AcountsData.txt"));
                boolean validation = false;
                System.out.println("LOGIN\n-Please enter your email: ");
                String checkEmail = in.next();
                System.out.println("Password: ");
                String checkPassword = in.next();
                String checkingInformation = (checkEmail + "/" + checkPassword);
                String data;
                while ((data = accountReader.readLine()) != null) {
                    if (checkingInformation.equals(data)) {
                        validation = true;
                    }
                }
                if (validation) {
                    System.out.println("Login successful!");
                    break;
                } else {
                    System.out.println("Username or password are invalid\nPlease try again\n\n");
                }
            }
        }
    }