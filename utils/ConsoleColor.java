package utils;

public final class ConsoleColor {

    private ConsoleColor() {}

    public static final String RESET = "\033[0m";

    public static final String BOLD = "\033[1m";
    public static final String DIM = "\033[2m";
    public static final String ITALIC = "\033[3m";
    public static final String UNDERLINE = "\033[4m";

    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    public static final String RED_BRIGHT = "\033[91m";
    public static final String GREEN_BRIGHT = "\033[92m";
    public static final String YELLOW_BRIGHT = "\033[93m";
    public static final String BLUE_BRIGHT = "\033[94m";
    public static final String PURPLE_BRIGHT = "\033[95m";
    public static final String CYAN_BRIGHT = "\033[96m";
    public static final String MAGENTA_BRIGHT = "\033[95m";

    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_PURPLE = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";

    public static String black(String text) {
        return BLACK + text + RESET;
    }

    public static String red(String text) {
        return RED + text + RESET;
    }

    public static String green(String text) {
        return GREEN + text + RESET;
    }

    public static String yellow(String text) {
        return YELLOW + text + RESET;
    }

    public static String blue(String text) {
        return BLUE + text + RESET;
    }

    public static String purple(String text) {
        return PURPLE + text + RESET;
    }

    public static String cyan(String text) {
        return CYAN + text + RESET;
    }

    public static String white(String text) {
        return WHITE + text + RESET;
    }

    public static String blackBg(String text) {
        return BG_BLACK + text + RESET;
    }

    public static String redBg(String text) {
        return BG_RED + text + RESET;
    }

    public static String greenBg(String text) {
        return BG_GREEN + text + RESET;
    }

    public static String yellowBg(String text) {
        return BG_YELLOW + text + RESET;
    }

    public static String blueBg(String text) {
        return BG_BLUE + text + RESET;
    }

    public static String purpleBg(String text) {
        return BG_PURPLE + text + RESET;
    }

    public static String cyanBg(String text) {
        return BG_CYAN + text + RESET;
    }

    public static String whiteBg(String text) {
        return BG_WHITE + text + RESET;
    }

    public static String bold(String text) {
        return BOLD + text + RESET;
    }

    public static String underline(String text) {
        return UNDERLINE + text + RESET;
    }

    public static String italic(String text) {
        return ITALIC + text + RESET;
    }

    public static String success(String text) {
        return GREEN_BRIGHT + BOLD + text + RESET;
    }

    public static String error(String text) {
        return RED_BRIGHT + BOLD + text + RESET;
    }

    public static String warning(String text) {
        return YELLOW_BRIGHT + text + RESET;
    }

    public static String info(String text) {
        return CYAN_BRIGHT + text + RESET;
    }

    public static String rgb(String text, int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("RGB values must be between 0 and 255");
        }
        return "\033[38;2;" + r + ";" + g + ";" + b + "m" + text + RESET;
    }

    public static String style(String text, String... styles) {
        StringBuilder sb = new StringBuilder();
        for (String style : styles) {
            sb.append(style);
        }
        sb.append(text).append(RESET);
        return sb.toString();
    }
}