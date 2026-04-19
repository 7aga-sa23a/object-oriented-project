package utils.console;

public final class ConsoleBanner {

    private ConsoleBanner() {}

    private static final String RESET = "\u001B[0m";


    public static final String WELCOME = """
__        _______ _     ____ ___  __  __ _____
\\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____|
 \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _|
  \\ V  V / | |___| |__| |__| |_| | |  | | |___
   \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____|
""";

    public static final String LOGIN = """
 _     ___   ____ ___ _   _
| |   / _ \\ / ___|_ _| \\ | |
| |  | | | | |  _ | ||  \\| |
| |__| |_| | |_| || || |\\  |
|_____\\___/ \\____|___|_| \\_|
""";

    public static final String SIGNUP = """
 ____ ___ ____ _   _ _   _ ____ 
/ ___|_ _/ ___| \\ | | | | |  _ \\
\\___ \\| | |  _|  \\| | | | | |_) |
 ___) | | |_| | |\\  | |_| |  __/
|____/___\\____|_| \\_|\\___/|_|
""";

    public static final String ATTENDANCE = """
    _    ____  ____    __  __    _  _____ _____ ____  ___    _    _     
   / \\  |  _ \\|  _ \\  |  \\/  |  / \\|_   _| ____|  _ \\|_ _|  / \\  | |    
  / _ \\ | | | | | | | | |\\/| | / _ \\ | | |  _| | |_) || |  / _ \\ | |    
 / ___ \\| |_| | |_| | | |  | |/ ___ \\| | | |___|  _ < | | / ___ \\| |___ 
/_/   \\_\\____/|____/  |_|  |_/_/   \\_\\_| |_____|_| \\_\\___/_/   \\_\\_____|
""";

    public static final String MATERIAL = """
 __  __    _  _____ _____ ____  ___    _    _     
|  \\/  |  / \\|_   _| ____|  _ \\|_ _|  / \\  | |    
| |\\/| | / _ \\ | | |  _| | |_) || |  / _ \\ | |    
| |  | |/ ___ \\| | | |___|  _ < | | / ___ \\| |___ 
|_|  |_/_/   \\_\\_| |_____|_| \\_\\___/_/   \\_\\_____|
""";

    public static final String ADD_MATERIAL = """
    _  _____ _____ _____ _   _ ____    _    _   _  ____ _____ 
   / \\|_   _|_   _| ____| \\ | |  _ \\  / \\  | \\ | |/ ___| ____|
  / _ \\ | |   | | |  _| |  \\| | | | |/ _ \\ |  \\| | |   |  _|  
 / ___ \\| |   | | | |___| |\\  | |_| / ___ \\| |\\  | |___| |___ 
/_/   \\_\\_|   |_| |_____|_| \\_|____/_/   \\_\\_| \\_|\\____|_____|
""";


    public static void welcome() {
        print(WELCOME);
    }

    public static void login() {
        print(LOGIN);
    }

    public static void signup() {
        print(SIGNUP);
    }

    public static void attendance() {
        print(ATTENDANCE);
    }

    public static void material() {
        print(MATERIAL);
    }

    public static void addMaterial() {
        print(ADD_MATERIAL);
    }


    public static void print(String banner) {
        System.out.println(banner.stripIndent());
    }

    public static void print(String banner, String color) {
        System.out.println(color + banner.stripIndent() + RESET);
    }

    public static void printCentered(String banner) {
        printCentered(banner, "");
    }

    public static void printCentered(String banner, String color) {
        String[] lines = banner.stripIndent().split("\n");
        int width = 80;

        for (String line : lines) {
            int padding = Math.max(0, (width - line.length()) / 2);
            System.out.println(color + " ".repeat(padding) + line + RESET);
        }
    }
}