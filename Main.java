// TO RUN THE PROGRAM, TYPE THE FOLLOWING IN THE TERMINAL
// java -cp ".;gson-2.13.2.jar" Main.java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // create an object of signUp class to call the constructor and start the sign up process for doctors.
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome Doctor!");
        
        // ask the doctor if they want to sign up or login and call the appropriate class based on their choice.
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter your choice \n1- Sign Up\n2- Login\n3- Exit\nYour choice: ");
            try {
                int choice = sc.nextInt();
                // choice 1 for sign up and choice 2 for login, if the input is invalid ask the user to enter a valid input until they do.

                if (choice == 1) {
                    @SuppressWarnings("unused")
                    Signup doctor1 = new Signup();
                    validInput = true;
                } else if (choice == 2) {
                    @SuppressWarnings("unused")
                    Login doctor2 = new Login();
                    validInput = true;
                } else if (choice == 3) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Sign Up or 2 for Login.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                sc.next(); // Clear the invalid input
            }
        }

        sc.close();
    }
}
