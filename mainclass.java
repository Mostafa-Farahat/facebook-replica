import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class mainclass {
    public static void main(String[] args) throws IOException
    {
        LoginRegisterClass account = new LoginRegisterClass();
        int choice;
        Scanner input =new Scanner(System.in);
        System.out.println("Please enter your choice:\n1-Register\n2-Login\n");
        choice =input.nextInt();
        while(true)
        {
            if (choice == 1)
            {
                account.register();
                break;
            }
            else if (choice == 2)
            {
                account.Login();
                break;
            }
            else
            {
                System.out.println("Wrong choice!");
                System.out.println("Please enter your choice:\n1-Register\n2-Login");
                choice =input.nextInt();
            }
        }
    }
}
