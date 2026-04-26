package utils;

public final class Banner {

    private Banner() {}

    public static final String ADD_COURSE =
            "    _       _     _  ____                          \n" +
                    "   / \\   __| | __| |/ ___|___  _   _ _ __ ___  ___ \n" +
                    "  / _ \\ / _` |/ _` | |   / _ \\| | | | '__/ __|/ _ \\\n" +
                    " / ___ \\ (_| | (_| | |__| (_) | |_| | |  \\__ \\  __/\n" +
                    "/_/   \\_\\__,_|\\__,_|\\____\\___/ \\__,_|_|  |___/\\___|";

    public static final String CourseList =
            "  ____                          _     ___ ____ _____ \n" +
                    " / ___|___  _   _ _ __ ___  ___| |   |_ _/ ___|_   _|\n" +
                    "| |   / _ \\| | | | '__/ __|/ _ \\ |    | |\\___ \\ | |  \n" +
                    "| |__| (_) | |_| | |  \\__ \\  __/ |___ | | ___) || |  \n" +
                    " \\____\\___/ \\__,_|_|  |___/\\___||_____|___|____/ |_|  ";

    public static final String DASHBOARD =
            "  ____            _     _                         _ \n" +
                    " |  _ \\  __ _ ___| |__ | |__   ___   __ _ _ __ __| |\n" +
                    " | | | |/ _` / __| '_ \\| '_ \\ / _ \\ / _` | '__/ _` |\n" +
                    " | |_| | (_| \\__ \\ | | | |_) | (_) | (_| | | | (_| |\n" +
                    " |____/ \\__,_|___/_| |_|_.__/ \\___/ \\__,_|_|  \\__,_|";

    public static final String SCANNING =
            "  ____                        _             \n" +
                    " / ___|  ___ __ _ _ __  _ __ (_)_ __   __ _ \n" +
                    " \\___ \\ / __/ _` | '_ \\| '_ \\| | '_ \\ / _` |\n" +
                    "  ___) | (_| (_| | | | | | | | | | | | (_| |\n" +
                    " |____/ \\___\\__,_|_| |_|_| |_|_|_| |_|\\__, |\n" +
                    "                                       |___/ ";

    private static String padLines(String banner) {
        String pad = Menu.hPad(55);
        StringBuilder sb = new StringBuilder();
        for (String line : banner.split("\n")) {
            sb.append(pad).append(line).append("\n");
        }
        return sb.toString();
    }

    public static void printCourselist() {
        System.out.println(ConsoleColor.CYAN_BRIGHT + padLines(CourseList) + ConsoleColor.RESET);
    }

    public static void printAddCourse() {
        System.out.println(ConsoleColor.CYAN_BRIGHT + padLines(ADD_COURSE) + ConsoleColor.RESET);
    }

    public static void printDashboard() {
        System.out.println(ConsoleColor.CYAN_BRIGHT + padLines(DASHBOARD) + ConsoleColor.RESET);
    }

    public static void printScanning() {
        System.out.println(ConsoleColor.CYAN_BRIGHT + padLines(SCANNING) + ConsoleColor.RESET);
    }

    /** Legacy – kept for backward compatibility */
    public static void print() {
        printDashboard();
    }
}