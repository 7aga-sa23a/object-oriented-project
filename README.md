# ConsoleColor Utility for Java

A lightweight utility for coloring console output using ANSI escape codes. Make your console output visually distinct with minimal code.

## Overview

ConsoleColor provides simple static methods to add colors, backgrounds, and styles to console text. No external dependencies, just drop in and use.

## Features

- Basic colors (red, green, blue, yellow, purple, cyan)
- Bright variants for high visibility
- Background colors
- Text styles (bold, underline, italic)
- Smart helper methods (success, error, warning, info)
- RGB (true color) support
- Generic style combiner
- Automatic color reset

## Installation

1. Copy `ConsoleColor.java` to your project:

```
your-project/
└── utils/
    └── console/
        └── ConsoleColor.java
```

2. Import in your Java files:

```java
import utils.console.ConsoleColor;
```

## Usage

### Basic Colors

```java
System.out.println(ConsoleColor.red("Error"));
System.out.println(ConsoleColor.green("Success"));
System.out.println(ConsoleColor.yellow("Warning"));
System.out.println(ConsoleColor.blue("Info"));
System.out.println(ConsoleColor.purple("Magic"));
System.out.println(ConsoleColor.cyan("Cool"));
System.out.println(ConsoleColor.white("Light"));
```

### Smart Helper Methods

```java
// Pre-styled for common use cases
System.out.println(ConsoleColor.success("Operation completed!"));
System.out.println(ConsoleColor.error("Something went wrong!"));
System.out.println(ConsoleColor.warning("Check your input"));
System.out.println(ConsoleColor.info("System ready"));
```

### Text Styles

```java
System.out.println(ConsoleColor.bold("Bold Text"));
System.out.println(ConsoleColor.underline("Underlined Text"));
System.out.println(ConsoleColor.italic("Italic Text"));
```

### Background Colors

```java
System.out.println(ConsoleColor.redBg("Red Background"));
System.out.println(ConsoleColor.greenBg("Green Background"));
System.out.println(ConsoleColor.blueBg("Blue Background"));
System.out.println(ConsoleColor.yellowBg("Yellow Background"));
```

### RGB (True Color)

```java
// Full RGB color support (0-255 for each channel)
System.out.println(ConsoleColor.rgb("Custom Purple", 128, 50, 200));
System.out.println(ConsoleColor.rgb("Custom Orange", 255, 140, 0));
System.out.println(ConsoleColor.rgb("Custom Teal", 0, 180, 180));
```

### Custom Styles

```java
// Combine multiple styles
System.out.println(ConsoleColor.style("Styled", ConsoleColor.RED, ConsoleColor.BOLD));
System.out.println(ConsoleColor.style("Important", ConsoleColor.YELLOW, ConsoleColor.UNDERLINE));
```

## Full Example

```java
import utils.console.ConsoleColor;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== APPLICATION STARTED ===");
        System.out.println();

        // Log operations
        logSuccess("Database connected");
        logInfo("User authenticated: john_doe");
        logWarning("Session expiring in 5 minutes");

        System.out.println();

        // Process results
        System.out.println(ConsoleColor.success("User created successfully"));
        System.out.println(ConsoleColor.error("Connection refused"));

        System.out.println();

        // Custom colors
        System.out.println(ConsoleColor.rgb("Neon text", 255, 0, 255));
        System.out.println(ConsoleColor.style("Custom style", ConsoleColor.CYAN, ConsoleColor.BOLD));

        System.out.println();
        logSuccess("All operations completed!");
    }

    public static void logSuccess(String message) {
        System.out.println(ConsoleColor.success(message));
    }

    public static void logError(String message) {
        System.out.println(ConsoleColor.error(message));
    }

    public static void logWarning(String message) {
        System.out.println(ConsoleColor.warning(message));
    }

    public static void logInfo(String message) {
        System.out.println(ConsoleColor.info(message));
    }
}
```

## Project Structure

```
my-java-project/
├── src/
│   ├── utils/
│   │   └── console/
│   │       └── ConsoleColor.java
│   └── Main.java
├── README.md
└── (other files)
```

## Available Constants

If you need to combine styles manually:

```java
// Colors
ConsoleColor.RED, ConsoleColor.GREEN, ConsoleColor.BLUE, ...
ConsoleColor.RED_BRIGHT, ConsoleColor.GREEN_BRIGHT, ...

// Backgrounds
ConsoleColor.BG_RED, ConsoleColor.BG_GREEN, ...

// Styles
ConsoleColor.BOLD, ConsoleColor.UNDERLINE, ConsoleColor.ITALIC

// Reset
ConsoleColor.RESET
```

## Notes

- Colors reset automatically after each styled text
- No external libraries required
- Works on most modern terminals (Windows CMD, PowerShell, Linux, macOS)
- RGB requires a terminal that supports true color (most modern terminals do)

## Quick Reference

| Method | Use Case |
|--------|---------|
| `ConsoleColor.success(text)` | Success messages (green + bold) |
| `ConsoleColor.error(text)` | Error messages (red + bold) |
| `ConsoleColor.warning(text)` | Warning messages (yellow) |
| `ConsoleColor.info(text)` | Info messages (cyan) |
| `ConsoleColor.rgb(text, r, g, b)` | Custom RGB color |
| `ConsoleColor.style(text, styles...)` | Combine custom styles |

## License

Free to use in any project.