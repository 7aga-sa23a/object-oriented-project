// TO RUN THE PROGRAM, TYPE THE FOLLOWING IN THE TERMINAL
// java -cp ".;gson-2.13.2.jar" Main.java

import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        // create an object of signUp class to call the constructor and start the sign up process for doctors.
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome Doctor!");
        
        // ask the doctor if they want to sign up or login and call the appropriate class based on their choice.
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter your choice \n1- Sign Up\n2- Login\nYour choice: ");
            try {
                int choice = sc.nextInt();
                // choice 1 for sign up and choice 2 for login, if the input is invalid ask the user to enter a valid input until they do.

                if (choice == 1) {
                    signup doctor1 = new signup();
                    validInput = true;
                } else if (choice == 2) {
                    login doctor2 = new login();
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Sign Up or 2 for Login.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                sc.next(); // Clear the invalid input
            }
        }
    }
}

// this class is for signup of doctors.
class signup {

    // this is doctor's attributes
    String username, password, repeatedPasswordc, ID, Courses[] = new String[10];

    // take input from user in the default constructor
    public signup() {
        Scanner sc = new Scanner(System.in);

        Gson gsn = new Gson();
        String fileName = "doctors.json";
        JsonArray doctorsArray;

        try (FileReader reader = new FileReader(fileName)) {
            doctorsArray = JsonParser.parseReader(reader).getAsJsonArray();
        } catch (Exception e) {
            doctorsArray = new JsonArray();
        }

        //take Doctor name and save it in username variable
        System.out.println("Enter username: ");
        this.username = sc.nextLine();

        // take Doctor ID and save it in ID variable and all should be unique and numbers 
        System.out.println("Enter ID: ");
        while (true) {
                
            this.ID = sc.nextLine();
            boolean uniqueID = true;
            for (JsonElement element : doctorsArray) {
                String id = element.getAsJsonObject()
                        .get("ID")
                        .getAsString();

                if (id.equals(this.ID)) {
                    uniqueID = false;
                    break;
                }
            }
            if (!uniqueID) {
                System.out.println("ID already exists. Please enter a unique ID: ");
                continue;
            }
            if (this.ID.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("ID should be numbers only. Please enter ID again: ");
            }
        }

        // take Doctor password and save it in password variable, and ask to repeat the password until it matches with the first one.
        System.out.println("Enter password: ");
        this.password = sc.nextLine();
        while (true) {
            System.out.println("Enter password again: ");
            this.repeatedPasswordc = sc.nextLine();

            // if it matches save it in json file 
            if (this.password.equals(this.repeatedPasswordc)) {
                System.out.println("account created successfully");

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                try (FileReader reader = new FileReader(fileName)) {
                    JsonElement element = JsonParser.parseReader(reader);

                    if (element.isJsonArray()) {
                        doctorsArray = element.getAsJsonArray();
                    } else {
                        doctorsArray = new JsonArray();
                    }

                } catch (IOException e) {

                    doctorsArray = new JsonArray();
                }

                JsonElement doctorJson = gson.toJsonTree(this);
                doctorsArray.add(doctorJson);

                try (FileWriter writer = new FileWriter(fileName)) {
                    gson.toJson(doctorsArray, writer);
                } catch (IOException e) {
                    System.out.println("Error saving doctors data");
                    e.printStackTrace();
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


// this class is for login of doctors.
class login {
    // this is doctor's attributes
    String ID, password;

    // take input from user in the default constructor
    public login() {
        Scanner sc = new Scanner(System.in);

        //take Doctor ID and save it in ID variable and all should be unique and numbers
        System.out.println("Enter Your ID: ");
        this.ID = sc.nextLine();

        // read the json file and check if the ID exists in the file
        Gson gson = new Gson();
        String fileName = "doctors.json";
    

        JsonArray doctorsArray;

        try (FileReader reader = new FileReader(fileName)) {
            doctorsArray = JsonParser.parseReader(reader).getAsJsonArray();
        } catch (Exception e) {
            System.out.println("File error or empty database");
            return;
        }

        // if the ID exists ask for password and check if it matches with the password in the file, if it matches print login successful, if it does not match print incorrect password and contact IT in the next 24 hours or try again, if the ID does not exist ask to enter ID again or type 'signup' to create a new account.
        boolean found = false, sign_up = false;

        while (!found && !sign_up) {

            for (JsonElement element : doctorsArray) {

                String id = element.getAsJsonObject()
                        .get("ID")
                        .getAsString();

                if (id.equals(this.ID)) {
                    found = true;
                    break;
                }}
                if (this.ID.equals("signup")) {
                    signup doctor1 = new signup();
                    sign_up = true;
                    break;
                }
            

            if (!found) {
                System.out.println("ID not found. Try again or type 'signup' to create a new account: ");
                this.ID = sc.nextLine();
            }
        }

        // if the ID exists ask for password and check if it matches with the password in the file, if it matches print login successful, if it does not match print incorrect password and contact IT in the next 24 hours or try again.
        if (!sign_up) {

            // take Doctor password and save it in password variable
            System.out.println("Enter Your password: ");
            this.password = sc.nextLine();
            boolean passwordMatch = false;
            for (JsonElement element : doctorsArray) {

                String id = element.getAsJsonObject()
                        .get("ID")
                        .getAsString();

                if (id.equals(this.ID)) {
                    String storedPassword = element.getAsJsonObject()
                            .get("password")
                            .getAsString();

                    if (storedPassword.equals(this.password)) {
                        passwordMatch = true;
                        break;
                    }
                }
            }
                if (passwordMatch) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Incorrect password. contact IT in the next 24 hours or try again.");
                }
        }
    }
}
