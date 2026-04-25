package src.app;

import java.util.Scanner;

// Run the program using the following command: java Main.java
// Or use this shortcut in VS Code: Ctrl + F5

/**
 * This program is a Java OOP Attendance System using QR code
 * simulation to manage students, materials, and attendance tracking.
 * 
 * @author 7aga-sa23a
 * @since 2026-04-24
 * @version v0.3.0
 */
public class Main {
    /**
     * The main method that handles the program flow.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Start the program flow with the onboarding page
        Runnable nextPage = onboardingPage();

        // Navigate to the next page
        System.out.println("\nNavigating the the next page...\n");
        nextPage.run();
    }

    /**
     * Displays the onboarding page for the Attendance System.
     * 
     * This method prompts the user to choose an option to proceed with the program
     * flow.
     * The available options are:
     * <ol>
     * <li>Login</li>
     * <li>Sign up</li>
     * <li>Exit program</li>
     * </ol>
     * 
     * The user's choice is validated, and the appropriate method is called based on
     * the chosen option.
     * 
     * @return A {@code Runnable} object representing the next page to navigate to.
     */
    private static Runnable onboardingPage() {
        // Display onboarding information
        System.out.println("Welcome to the Attendance System.\n" +
                "Manage your courses, students, and attendance records " +
                "efficiently through a simple and secure interface.\n");

        // Display onboarding options
        System.out.println("Good day doctor!");
        System.out.println("Please, choose an option:");
        System.out.println("-------------------------");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. Exit program");
        System.out.println();
        System.out.println("Enter your choice: ");

        // Keep prompting the user to choose an option, until they make a valid choice
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine().strip().toLowerCase();

            // Validate the user's choice
            // return class::method is like returning a pointer to the method

            // Option 1: Login
            if (option.startsWith("1") || option.contains("log")) {
                scanner.close();
                return Main::loginPage;
            }
            // Option 2: Sign up
            else if (option.startsWith("2") || option.contains("sign")) {
                scanner.close();
                return Main::signupPage;
            }
            // Option 3: Exit program
            else if (option.startsWith("3") || option.contains("exit")) {
                scanner.close();
                System.out.println("\nExiting the program...!\n");
                System.exit(0);
            }
            // Invalid option
            else {
                System.out.println("\nInvalid option.");
                System.out.println("Please enter the option number or name.\n");
            }

            // The loop will continue until the user makes a valid choice
        }
    }

    /**
     * Login page
     * - Doctor ID OR Email
     * - Password
     * - In case you forgot your password, please contact the faculty IT
     * - Signup instead
     */
    private static Runnable loginPage() {
        System.out.println("This is the login page.");
        return Main::signupPage;
    }

    /**
     * Signup page
     * - Name (First & last only)
     * - Email
     * - Password
     * - Repeat password
     * - EXTRA: Simulate email and IT confirmation process
     * - - Example:
     * - - Your registration will be evaluated by the IT shortly…
     * - - Registration was successful!
     */
    private static Runnable signupPage() {
        System.out.println("This is the signup page.");
        return null;
    }

    /**
     * Dashboard
     * - Add course
     * - Show courses
     * - Take attendance for today
     * - Edit attendance for a day
     * - - Shows databases for that day
     * - Exit program
     */
    private static Runnable dashboardPage() {
        System.out.println("This is the dashboard page.");
        return null;
    }

    /**
     * Add course
     * - name
     * - Year
     * - Semester
     * - Code
     */
    private static Runnable addCoursePage() {
        System.out.println("This is the add courses page.");
        return null;
    }

    /**
     * Show courses
     * - Displays a list of courses added
     * - - Number of registered students
     * - - Other course details…
     * - Edit course
     * - Delete course
     * - Option to view course attendance on a specific day
     * - Option to view student attendance percentage
     * - - 35% ≥ Red (Danger)
     * - - 35% - 70% Yellow (Normal)
     * - - 70% ≤ Green (Safe)
     */
    private static Runnable showCoursesPage() {
        System.out.println("This is the show courses page.");
        return null;
    }

    /**
     * Take attendance
     * - Code scanner class
     * - Connected with JSON database
     */
    private static Runnable takeAttendancePage() {
        System.out.println("This is the take attendance page.");
        return null;
    }

    /**
     * Edit attendance for a day
     * - Shows databases for that day
     */
    private static Runnable editAttendancePage() {
        System.out.println("This is the edit attendance page.");
        return null;
    }
}