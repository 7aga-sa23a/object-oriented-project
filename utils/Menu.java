package utils;

import java.util.List;

public class Menu {

    // ─── Terminal size helpers ────────────────────────────────────────────────

    private static int terminalWidth() {
        try {
            String[] cmd = { "sh", "-c", "tput cols 2>/dev/tty" };
            Process p = Runtime.getRuntime().exec(cmd);
            int cols = Integer.parseInt(new String(p.getInputStream().readAllBytes()).trim());
            return cols > 0 ? cols : 80;
        } catch (Exception e) {
            return 80;
        }
    }

    private static int terminalHeight() {
        try {
            String[] cmd = { "sh", "-c", "tput lines 2>/dev/tty" };
            Process p = Runtime.getRuntime().exec(cmd);
            int lines = Integer.parseInt(new String(p.getInputStream().readAllBytes()).trim());
            return lines > 0 ? lines : 24;
        } catch (Exception e) {
            return 24;
        }
    }

    private static void printVerticalPadding(int boxHeight) {
        int termH = terminalHeight();
        int topPad = (termH - boxHeight) / 2;
        if (topPad > 0) {
            System.out.println("\n".repeat(topPad - 1));
        }
    }

    public static String hPad(int boxWidth) {
        int termW = terminalWidth();
        int left = (termW - boxWidth) / 2 + 35;
        return left > 0 ? " ".repeat(left) : "";
    }


    /**
     * Moves the cursor up {@code n} lines and clears each one.
     * Used to erase only the prompt + user-typed text, keeping the panel visible.
     */
    public static void eraseLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("\033[1A"); // cursor up 1
            System.out.print("\033[2K"); // erase entire line
        }
        System.out.flush();
    }

    /**
     * Prints a compact one-line error in red, padded to match the panel,
     * then a blank line so the next prompt has room.
     * The next call to eraseLines(2) will wipe both lines cleanly.
     */
    public static void printInlineError(String message) {
        String p = hPad(44);
        System.out.println(p
                + ConsoleColor.RED_BRIGHT + "✗ " + ConsoleColor.RESET
                + ConsoleColor.RED + message + ConsoleColor.RESET);
        System.out.println(); // blank spacer — erased by next eraseLines(2)
    }

    // ─── Pages ───────────────────────────────────────────────────────────────

    public static void showOnboardingMenu() {
        int width = 36;
        String line = "─".repeat(width - 2);
        String p = hPad(width);
        printVerticalPadding(9);

        String border  = ConsoleColor.CYAN_BRIGHT;
        String title   = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String option  = ConsoleColor.CYAN;
        String bracket = ConsoleColor.MAGENTA_BRIGHT;
        String reset   = ConsoleColor.RESET;

        System.out.println(p + border + "┌" + line + "┐" + reset);
        System.out.println(p + border + "│" + reset + title + padBoth("MERSAD SYSTEM", width - 2) + reset + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + bracket + "[0]" + reset + option + "  Back" + reset + " ".repeat(width - 13) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + bracket + "[1]" + reset + option + "  Login" + reset + " ".repeat(width - 14) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + bracket + "[2]" + reset + option + "  Sign Up" + reset + " ".repeat(width - 16) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + bracket + "[3]" + reset + option + "  Info" + reset + " ".repeat(width - 13) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + bracket + "[4]" + reset + option + "  Exit" + reset + " ".repeat(width - 13) + border + "│" + reset);
        System.out.println(p + border + "└" + line + "┘" + reset);
        System.out.print("\n" + p + ConsoleColor.MAGENTA_BRIGHT + "›" + reset + " Enter choice: ");
    }

    public static void showLoginMenu() {
        int width = 36;
        String line = "─".repeat(width - 2);
        String p = hPad(width);
        printVerticalPadding(10);

        String border  = ConsoleColor.CYAN_BRIGHT;
        String title   = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String label   = ConsoleColor.CYAN;
        String hint    = ConsoleColor.DIM + ConsoleColor.WHITE;
        String reset   = ConsoleColor.RESET;

        System.out.println(p + border + "┌" + line + "┐" + reset);
        System.out.println(p + border + "│" + reset + title + padBoth("LOGIN", width - 2) + reset + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Doctor ID or Email:" + reset + " ".repeat(width - 23) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Password:" + reset + " ".repeat(width - 13) + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + hint + "Forgot your password?" + reset + " ".repeat(width - 25) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + hint + "Contact the faculty IT support" + reset + " ".repeat(width - 34) + border + "│" + reset);
        System.out.println(p + border + "└" + line + "┘" + reset);
        System.out.print("\n" + p + ConsoleColor.MAGENTA_BRIGHT + "›" + reset + " Doctor ID or Email: ");
    }

    public static void showSignupMenu() {
        int width = 36;
        String line = "─".repeat(width - 2);
        String p = hPad(width);
        printVerticalPadding(9);

        String border  = ConsoleColor.CYAN_BRIGHT;
        String title   = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String label   = ConsoleColor.CYAN;
        String reset   = ConsoleColor.RESET;

        System.out.println(p + border + "┌" + line + "┐" + reset);
        System.out.println(p + border + "│" + reset + title + padBoth("SIGN UP", width - 2) + reset + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Name (First & Last):" + reset + " ".repeat(width - 24) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Email:" + reset + " ".repeat(width - 10) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Password:" + reset + " ".repeat(width - 13) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Repeat Password:" + reset + " ".repeat(width - 20) + border + "│" + reset);
        System.out.println(p + border + "└" + line + "┘" + reset);
        System.out.print("\n" + p + ConsoleColor.MAGENTA_BRIGHT + "›" + reset + " First & Last Name: ");
    }

    public static void showMainMenu() {
        clearScreen();
        Banner.printDashboard();

        int width = 36;
        String line = "─".repeat(width - 2);
        String h = hPad(width);
        printVerticalPadding(9);

        String border  = ConsoleColor.CYAN_BRIGHT;
        String title   = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String option  = ConsoleColor.CYAN;
        String bracket = ConsoleColor.MAGENTA_BRIGHT;
        String reset   = ConsoleColor.RESET;

        System.out.println(h + border + "┌" + line + "┐" + reset);
        System.out.println(h + border + "│" + reset + title + padBoth("MERSAD SYSTEM", width - 2) + reset + border + "│" + reset);
        System.out.println(h + border + "├" + line + "┤" + reset);
        System.out.println(h + border + "│" + reset + "  " + bracket + "[0]" + reset + option + "  Back" + reset + " ".repeat(width - 13) + border + "│" + reset);
        System.out.println(h + border + "│" + reset + "  " + bracket + "[1]" + reset + option + "  Take Attendance" + reset + " ".repeat(width - 24) + border + "│" + reset);
        System.out.println(h + border + "│" + reset + "  " + bracket + "[2]" + reset + option + "  Add Course" + reset + " ".repeat(width - 19) + border + "│" + reset);
        System.out.println(h + border + "│" + reset + "  " + bracket + "[3]" + reset + option + "  Show Courses" + reset + " ".repeat(width - 21) + border + "│" + reset);
        System.out.println(h + border + "│" + reset + "  " + bracket + "[4]" + reset + option + "  Exit" + reset + " ".repeat(width - 13) + border + "│" + reset);
        System.out.println(h + border + "└" + line + "┘" + reset);
        System.out.print("\n" + h + ConsoleColor.MAGENTA_BRIGHT + "›" + reset + " Enter choice: ");
    }

    // ─── Add Course Panel ─────────────────────────────────────────────────────

    public static void showAddCourseMenu() {
        clearScreen();
        Banner.printAddCourse();

        int width = 44;
        String line = "─".repeat(width - 2);
        String p = hPad(width);
        printVerticalPadding(13);

        String border  = ConsoleColor.CYAN_BRIGHT;
        String title   = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String label   = ConsoleColor.CYAN;
        String hint    = ConsoleColor.DIM + ConsoleColor.WHITE;
        String accent  = ConsoleColor.MAGENTA_BRIGHT;
        String reset   = ConsoleColor.RESET;

        System.out.println(p + border + "┌" + line + "┐" + reset);
        System.out.println(p + border + "│" + reset + title + padBoth("ADD NEW COURSE", width - 2) + reset + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Course Name:" + reset + " ".repeat(width - 16) + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + hint + "Format:  " + reset + accent + "name-courseCode-year" + reset + " ".repeat(width - 33) + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + hint + "Example: " + reset + accent + "DataStructures-CS201-2025" + reset + " ".repeat(width - 38) + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Number of Students:" + reset + " ".repeat(width - 23) + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + hint + "Enter the total enrolled students" + reset + " ".repeat(width - 37) + border + "│" + reset);
        System.out.println(p + border + "└" + line + "┘" + reset);
    }

    public static void promptCourseName() {
        String p = hPad(44);
        System.out.print("\n" + p + ConsoleColor.MAGENTA_BRIGHT + "›" + ConsoleColor.RESET + " Course Name: ");
    }

    public static void promptStudentCount() {
        String p = hPad(44);
        System.out.print(p + ConsoleColor.MAGENTA_BRIGHT + "›" + ConsoleColor.RESET + " Number of Students: ");
    }

    // ─── Attendance Panels ────────────────────────────────────────────────────

    public static void showStudentAttended(String id, String name, String time) {
        int width = 37;
        String line = "─".repeat(width - 2);
        String p = hPad(width);

        String border = ConsoleColor.GREEN_BRIGHT;
        String title  = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String label  = ConsoleColor.CYAN;
        String value  = ConsoleColor.WHITE;
        String status = ConsoleColor.GREEN_BRIGHT + ConsoleColor.BOLD;
        String reset  = ConsoleColor.RESET;

        System.out.println("\n" + p + border + "┌" + line + "┐" + reset);
        System.out.println(p + border + "│" + reset + title + padBoth("STUDENT ATTENDED", width - 2) + reset + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "ID     │ " + reset + value + padRight(id, width - 13) + reset + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Name   │ " + reset + value + padRight(name, width - 13) + reset + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Time   │ " + reset + value + padRight(time, width - 13) + reset + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Status │ " + reset + status + padRight("✓ RECORDED", width - 13) + reset + border + "│" + reset);
        System.out.println(p + border + "└" + line + "┘" + reset + "\n");
    }

    public static void showScanningStatus(int scanned, int total) {
        int barWidth = 20;
        int filled = (total > 0) ? (int) ((double) scanned / total * barWidth) : 0;
        String filledBar = ConsoleColor.CYAN_BRIGHT + "█".repeat(filled) + ConsoleColor.RESET;
        String emptyBar  = ConsoleColor.DIM + "░".repeat(barWidth - filled) + ConsoleColor.RESET;
        String p = hPad(barWidth + 20);

        System.out.print("\r" + p + "│  [" + filledBar + emptyBar + "]  " + scanned + "/" + total + "  (Y=Save  N=Exit): ");
    }

    public static void showCoursesMenu(List<String> courses) {
        clearScreen();
        Banner.printCourselist();

        int width = 37;
        String line = "─".repeat(width - 2);
        String h = hPad(width);

        String border = ConsoleColor.CYAN_BRIGHT;
        String title  = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String option = ConsoleColor.CYAN;
        String bracket= ConsoleColor.MAGENTA_BRIGHT;
        String reset  = ConsoleColor.RESET;

        System.out.println("\n" + h + border + "┌" + line + "┐" + reset);
        System.out.println(h + border + "│" + reset + title + padBoth("COURSE LIST", width - 2) + reset + border + "│" + reset);
        System.out.println(h + border + "├" + line + "┤" + reset);

        if (courses.isEmpty()) {
            System.out.println(h + border + "│" + reset + padBoth("No courses available", width - 2) + border + "│" + reset);
        } else {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(h + border + "│" + reset + "  " + bracket + "[" + (i + 1) + "]" + reset + "  " + option + padRight(courses.get(i), width - 9) + reset + border + "│" + reset);
            }
        }
        System.out.println(h + border + "└" + line + "┘" + reset + "\n");
    }

    public static void showMessage(String message) {
        clearScreen();
        int width = 37;
        String line = "─".repeat(width - 2);
        String p = hPad(width);

        System.out.println("\n" + p + ConsoleColor.CYAN_BRIGHT + "┌" + line + "┐" + ConsoleColor.RESET);
        System.out.println(p + ConsoleColor.CYAN_BRIGHT + "│" + ConsoleColor.RESET + padBoth(message, width - 2) + ConsoleColor.CYAN_BRIGHT + "│" + ConsoleColor.RESET);
        System.out.println(p + ConsoleColor.CYAN_BRIGHT + "└" + line + "┘" + ConsoleColor.RESET + "\n");
    }

    public static void showSuccess(String message) {
        clearScreen();
        int width = 37;
        String line = "─".repeat(width - 2);
        String p = hPad(width);

        System.out.println("\n" + p + ConsoleColor.GREEN_BRIGHT + "┌" + line + "┐" + ConsoleColor.RESET);
        System.out.println(p + ConsoleColor.GREEN_BRIGHT + "│" + ConsoleColor.RESET + ConsoleColor.success(padBoth("✓ " + message, width - 2)) + ConsoleColor.GREEN_BRIGHT + "│" + ConsoleColor.RESET);
        System.out.println(p + ConsoleColor.GREEN_BRIGHT + "└" + line + "┘" + ConsoleColor.RESET + "\n");
    }

    public static void showError(String message) {
        clearScreen();
        int width = 37;
        String line = "─".repeat(width - 2);
        String p = hPad(width);

        System.out.println("\n" + p + ConsoleColor.RED_BRIGHT + "┌" + line + "┐" + ConsoleColor.RESET);
        System.out.println(p + ConsoleColor.RED_BRIGHT + "│" + ConsoleColor.RESET + ConsoleColor.error(padBoth("✗ " + message, width - 2)) + ConsoleColor.RED_BRIGHT + "│" + ConsoleColor.RESET);
        System.out.println(p + ConsoleColor.RED_BRIGHT + "└" + line + "┘" + ConsoleColor.RESET + "\n");
    }

    public static void showScanningHeader(String courseName) {
        clearScreen();
        Banner.printScanning();

        int width = 37;
        String line = "─".repeat(width - 2);
        String h = hPad(width);

        String border = ConsoleColor.CYAN_BRIGHT;
        String title  = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String hint   = ConsoleColor.DIM + ConsoleColor.WHITE;
        String reset  = ConsoleColor.RESET;

        System.out.println("\n" + h + border + "┌" + line + "┐" + reset);
        System.out.println(h + border + "│" + reset + title + padBoth("SCANNING: " + courseName, width - 2) + reset + border + "│" + reset);
        System.out.println(h + border + "├" + line + "┤" + reset);
        System.out.println(h + border + "│" + reset + "  " + hint + "Point camera at QR code" + reset + " ".repeat(width - 27) + border + "│" + reset);
        System.out.println(h + border + "│" + reset + "  " + hint + "Press Y to save" + reset + " ".repeat(width - 19) + border + "│" + reset);
        System.out.println(h + border + "│" + reset + "  " + hint + "Press N to exit without saving" + reset + " ".repeat(width - 34) + border + "│" + reset);
        System.out.println(h + border + "└" + line + "┘" + reset + "\n");
    }

    public static void showAttendanceSaved(int count, String filename) {
        int width = 37;
        String line = "─".repeat(width - 2);
        String p = hPad(width);

        String border = ConsoleColor.GREEN_BRIGHT;
        String title  = ConsoleColor.BOLD + ConsoleColor.WHITE;
        String label  = ConsoleColor.CYAN;
        String value  = ConsoleColor.WHITE;
        String reset  = ConsoleColor.RESET;

        System.out.println("\n" + p + border + "┌" + line + "┐" + reset);
        System.out.println(p + border + "│" + reset + title + padBoth("ATTENDANCE SAVED", width - 2) + reset + border + "│" + reset);
        System.out.println(p + border + "├" + line + "┤" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "Total Students: " + reset + value + padRight(String.valueOf(count), width - 20) + reset + border + "│" + reset);
        System.out.println(p + border + "│" + reset + "  " + label + "File: " + reset + value + padRight(filename, width - 10) + reset + border + "│" + reset);
        System.out.println(p + border + "└" + line + "┘" + reset + "\n");
    }

    private static String padRight(String s, int length) {
        if (s == null) s = "";
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) sb.append(' ');
        return sb.toString();
    }

    private static String padBoth(String s, int length) {
        if (s == null) s = "";
        int padding = length - s.length();
        int leftPad = padding / 2;
        int rightPad = padding - leftPad;
        return " ".repeat(Math.max(0, leftPad)) + s + " ".repeat(Math.max(0, rightPad));
    }

    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[2J\033[H");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }

    public static String getPromptPrefix() {
        return hPad(36) + ConsoleColor.MAGENTA_BRIGHT + "›" + ConsoleColor.RESET + " ";
    }
}