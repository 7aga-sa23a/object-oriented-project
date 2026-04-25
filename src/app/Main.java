package src.app;

// Run the program using the following command: java Main.java
// Or use this shortcut in VS Code: Ctrl + F5

/**
 * This program is a Java OOP Attendance System using QR code
 * simulation to manage students, materials, and attendance tracking.
 * 
 * @author 7aga-sa23a
 * @since 2026-04-24
 * @version v0.1.0
 */
public class Main {
    /**
     * The main method that handles the program flow.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Example usage of page routing
        Runnable nextPage = Main.loginPage();
        System.out.println("Redirecting to the next page.");
        nextPage.run();
    }

    /**
     * Onboarding page
     * - Info about the program
     * - - Clarify what the program does
     * - - Only for doctors
     * - Login option
     * - Sign up option
     * - Exit option
     */
    private static Runnable onboardingPage() {
        System.out.println("This is the onboarding page.");
        return null;
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