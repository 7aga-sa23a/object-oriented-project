package src.page;

public final class OnboardingPage extends Page {
    /**
     * Onboarding page
     * - Info about the program
     * - - Clarify what the program does
     * - - Only for doctors
     * - Login option
     * - Sign up option
     * - Exit option
     */
    public OnboardingPage() {
        
    }

    public String display() {
        // Dispaly the page details
        System.out.println("This is the onboarding page.");

        // ...

        // Return the next page to navigate to
        return "LoginPage";
    }
}
