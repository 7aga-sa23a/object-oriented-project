package src.app;

import src.page.OnboardingPage;
import src.page.LoginPage;
import src.page.SignupPage;
import src.page.DashboardPage;
import src.page.AddCoursePage;
import src.page.ShowCoursesPage;
import src.page.TakeAttendancePage;
import src.page.EditAttendancePage;

// Run the program using the following command: java Main.java

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
     * Program flow.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Start the program flow
        final OnboardingPage onboardingPage = new OnboardingPage();
        final LoginPage loginPage = new LoginPage();
        final SignupPage signupPage = new SignupPage();
        final DashboardPage dashboardPage = new DashboardPage();
        final AddCoursePage addCoursePage = new AddCoursePage();
        final ShowCoursesPage showCoursesPage = new ShowCoursesPage();
        final TakeAttendancePage takeAttendancePage = new TakeAttendancePage();
        final EditAttendancePage editAttendancePage = new EditAttendancePage();
    }
}