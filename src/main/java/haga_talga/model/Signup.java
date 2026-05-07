package haga_talga.model;

import java.util.Scanner;

import haga_talga.page.LoginPage;

// this class is for signup of doctors.
public class Signup {

    // this is doctor's attributes
    String username, password, repeatedPassword, ID;

    // take input from user in the default constructor
    public Signup() {
        Scanner sc = new Scanner(System.in);

        // take Doctor name and save it in username variable
        System.out.println("Enter username: ");
        this.username = sc.nextLine();

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        System.out.println("Enter ID: ");
        this.ID = sc.nextLine();

        // take Doctor password and save it in password variable, and ask to repeat the
        // password until it matches with the first one.
        System.out.println("Enter password: ");
        this.password = sc.nextLine();

        while (true) {
            System.out.println("Enter password again: ");
            this.repeatedPassword = sc.nextLine();

            // if it matches save it in json file
            if (this.password.equals(this.repeatedPassword)) {
                System.out.println("account created successfully");

                // create a new doctor object and save it in json file
               // Doctor doctor = new Doctor();
               //int login = doctor.signUp(this.username, this.ID, this.password);
               int login = 1;
                if(login == 2){
                    // login page 
                    LoginPage loginPage = new LoginPage();
                }
                break;
            }

            // if it does not match ask to enter password again
            else {
                System.out.println("Passwords do not match. Please enter password again: ");
            }
        }
    }

}