import models.Course;
import models.Student;
import services.CourseService;
import services.CameraService;
import services.QRScannerService;
import services.AttendanceService;
import services.ExcelExportService;
import services.BackupService;
import utils.Menu;
import utils.Banner;
import utils.ConsoleColor;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final CourseService courseService;
    private final CameraService cameraService;
    private final QRScannerService qrScannerService;
    private final AttendanceService attendanceService;
    private final ExcelExportService excelService;
    private final BackupService backupService;

    private final Scanner scanner;
    private volatile boolean running;

    public Main() {
        this.courseService = new CourseService();
        this.cameraService = new CameraService();
        this.qrScannerService = new QRScannerService(cameraService);
        this.attendanceService = new AttendanceService(courseService);
        this.excelService = new ExcelExportService();
        this.backupService = new BackupService(excelService);
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public static String getPromptPrefix() {
        return Menu.hPad(36) + ConsoleColor.YELLOW_BRIGHT + "›" + ConsoleColor.RESET + " ";
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        Menu.clearScreen();
        while (running) {
            Menu.showMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "0" -> running = false;
                case "1" -> selectCourse();
                case "2" -> addNewCourse();
                case "3" -> viewCourses();
                case "4" -> exitApp();
                default -> Menu.showError("Invalid choice");
            }
        }

        Menu.showMessage("Goodbye!");
    }

    private void addNewCourse() {
        Menu.showAddCourseMenu();

        // ─── Course Name — retry in-place on bad format ───────────────────
        String name, courseCode;
        int year;

        while (true) {
            Menu.promptCourseName();
            String input = scanner.nextLine().trim();

            Menu.eraseLines(2);

            if (input.isEmpty()) {
                Menu.printInlineError("Cannot be empty  —  use: name-courseCode-year");
                continue;
            }

            String[] parts = input.split("-");
            if (parts.length < 3) {
                Menu.printInlineError("Format: name-courseCode-year  (e.g. Math-CS101-2026)");
                continue;
            }

            name           = parts[0].trim();
            courseCode     = parts[1].trim();
            String yearStr = parts[2].trim();

            if (name.isEmpty() || courseCode.isEmpty()) {
                Menu.printInlineError("Name and course code cannot be empty");
                continue;
            }

            try {
                year = Integer.parseInt(yearStr);
                break;                          // valid ✓
            } catch (NumberFormatException e) {
                Menu.printInlineError("Year must be a number  (e.g. Math-CS101-2026)");
            }
        }

        // ─── Number of Students — retry in-place on bad value ─────────────
        int numberOfStudents;

        while (true) {
            Menu.promptStudentCount();
            String raw = scanner.nextLine().trim();

            Menu.eraseLines(2);

            try {
                numberOfStudents = Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                Menu.printInlineError("Must be a whole number greater than 0");
                continue;
            }

            if (numberOfStudents <= 0) {
                Menu.printInlineError("Number of students must be positive");
                continue;
            }

            break;                             // valid ✓
        }

// ─── Create or load course ───────────────────────────────────────
        Course course = courseService.findCourse(name);
        if (course != null && course.getCourseCode().equalsIgnoreCase(courseCode)) {
            Menu.showMessage("Course exists. Loading data...");
            attendanceService.setCurrentCourse(course);
        } else {
            courseService.addCourse(name, courseCode, numberOfStudents);
            Course newCourse = courseService.findCourseByCode(name, courseCode);
            if (newCourse != null) {
                attendanceService.setCurrentCourse(newCourse);
                Menu.showSuccess("Course created: " + newCourse.getDisplayName());
            }
        }

        startScanningSession();
    }

    private void selectCourse() {
        List<Course> courses = courseService.getCourses();
        if (courses.isEmpty()) {
            Menu.showError("No course available");
            return;
        }

        Menu.showCoursesMenu(courses.stream().map(Course::getDisplayName).toList());
        System.out.print(Menu.getPromptPrefix() + "Select (0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0)
                return;

            Course course = courseService.getCourse(choice - 1);
            if (course != null) {
                attendanceService.setCurrentCourse(course);
                startScanningSession();
            } else {
                Menu.showError("Invalid selection");
            }
        } catch (NumberFormatException e) {
            Menu.showError("Invalid input");
        }
    }

    private void viewCourses() {
        Banner.printCourselist();
        List<Course> courses = courseService.getCourses();
        if (courses.isEmpty()) {
            Menu.showError("No courses available");
            System.out.print(Menu.hPad(15) + ConsoleColor.YELLOW + "Press Enter to continue..." + ConsoleColor.RESET);
            scanner.nextLine();
            return;
        }

        Menu.showCoursesMenu(courses.stream().map(Course::getDisplayName).toList());
        System.out.print(Menu.hPad(15) + ConsoleColor.YELLOW + "[Enter] Start  [D] Delete  [0] Cancel: " + ConsoleColor.RESET);
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("d")) {
            System.out.print(Menu.hPad(15) + ConsoleColor.YELLOW + "Enter course number to delete: " + ConsoleColor.RESET);
            try {
                int delChoice = Integer.parseInt(scanner.nextLine().trim());
                if (delChoice > 0 && delChoice <= courses.size()) {
                    Course toDelete = courses.get(delChoice - 1);
                    System.out.print(Menu.hPad(15) + ConsoleColor.RED + "Confirm delete " + toDelete.getDisplayName() + "? (Y/N): " + ConsoleColor.RESET);
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("y")) {
                        courseService.deleteCourse(delChoice - 1);
                        Menu.showMessage("Course deleted");
                    }
                }
            } catch (NumberFormatException e) {
                Menu.showError("Invalid input");
            }
            return;
        }

        if (input.isEmpty()) {
            System.out.print(Menu.hPad(15) + ConsoleColor.YELLOW + "Select course to start (0 to cancel): " + ConsoleColor.RESET);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0) return;
                Course course = courseService.getCourse(choice - 1);
                if (course != null) {
                    attendanceService.setCurrentCourse(course);
                    startScanningSession();
                } else {
                    Menu.showError("Invalid selection");
                }
            } catch (NumberFormatException e) {
                Menu.showError("Invalid input");
            }
        }
    }

private void startScanningSession() {
        Course course = attendanceService.getCurrentCourse();
        if (course == null) {
            Menu.showError("No course selected");
            return;
        }

        Menu.clearScreen();
        Menu.showScanningHeader(course.getDisplayName());
        System.out.println(ConsoleColor.CYAN + "=== ATTENDANCE SESSION STARTED ===" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.CYAN + "Course: " + ConsoleColor.WHITE + course.getDisplayName() + ConsoleColor.RESET);
        System.out.println(ConsoleColor.CYAN + "Expected Students: " + ConsoleColor.WHITE + course.getNumberOfStudents() + ConsoleColor.RESET);
        System.out.println();

        if (!cameraService.openCamera()) {
            Menu.showError("Failed to open camera");
            return;
        }

        backupService.resetBackupCount();
        qrScannerService.startScanning();

        scanLoop();
    }

    // === SCAN LOOP: Continuous QR scanning ===
    private void scanLoop() {
        Course course = attendanceService.getCurrentCourse();

        while (qrScannerService.isRunning() && cameraService.isCameraOpen()) {
            Student student = qrScannerService.getLastScannedStudent();
            if (student != null) {
                boolean added = attendanceService.addStudent(student);
                if (added) {
                    String time = java.time.LocalDateTime.now().format(
                            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    Menu.showStudentAttended(student.getId(), student.getName(), time);
                    
                    // === BACKUP: Auto-save every 5 students ===
                    backupService.checkAndBackup(course);
                }
                qrScannerService.clearLastScanned();
            }

            Menu.showScanningStatus(qrScannerService.getScannedCount(), attendanceService.getTotalCount());

            try {
                if (System.in.available() > 0) {
                    String input = scanner.nextLine().trim();
                    if (input.equalsIgnoreCase("y")) {
                        saveAndQuit();
                        break;
                    } else if (input.equalsIgnoreCase("n")) {
                        exitWithoutSave();
                        break;
                    }
                }
            } catch (java.io.IOException e) {
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }

        qrScannerService.stopScanning();
        cameraService.closeCamera();
    }

    private void saveAndQuit() {
        qrScannerService.stopScanning();
        cameraService.closeCamera();

        Course course = attendanceService.getCurrentCourse();
        
        if (course != null && course.getRecordCount() > 0) {
            boolean success = excelService.exportFinal(course);
            if (success) {
                Menu.showAttendanceSaved(course.getRecordCount(),
                        "data/attendance/" + course.getExportFileName());
            } else {
                Menu.showError("Failed to save Excel file");
            }
        } else {
            Menu.showError("No attendance records to save");
        }
    }

    // === EXIT WITHOUT SAVE ===
    private void exitWithoutSave() {
        qrScannerService.stopScanning();
        cameraService.closeCamera();
        Menu.showMessage("Attendance not saved");
    }

    private void exitApp() {
        running = false;
    }
}