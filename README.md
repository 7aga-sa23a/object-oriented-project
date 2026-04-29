# Mersad - Student Attendance System

A console-based student attendance system with QR code scanning, camera integration, and Excel export capabilities.

## Features

- **QR Code Scanning** - Real-time camera-based QR code detection
- **Course Management** - Add and manage courses with student counts
- **Attendance Tracking** - Automatic student check-in via QR codes
- **Excel Export** - Export attendance records to Excel files
- **Auto Backup** - Automatic backup every 5 scanned students
- **Colorful Console UI** - ASCII art banner and colored menu system

## Requirements

- Java 21 or higher
- Webcam (for QR scanning)
- Command Prompt (Windows)

## Project Structure

```
Mersad/
├── Main.java                 # Application entry point
├── models/
│   ├── Course.java          # Course data model
│   ├── Student.java         # Student data model
│   └── AttendanceRecord.java # Attendance record model
├── services/
│   ├── CameraService.java   # Camera management
│   ├── QRScannerService.java # QR code scanning
│   ├── CourseService.java   # Course operations
│   ├── AttendanceService.java # Attendance tracking
│   ├── ExcelExportService.java # Excel file export
│   └── BackupService.java   # Auto-backup functionality
├── utils/
│   ├── Banner.java          # ASCII banner display
│   ├── Menu.java            # Menu and UI helpers
│   ├── ConsoleColor.java    # ANSI color codes
│   └── DateUtils.java       # Date formatting
├── lib/                      # External JAR dependencies
├── data/                    # Course and attendance data (generated)
│   └── attendance/           # Exported Excel files
├── run.bat                  # Build and run script
└── README.md
```

## Running the Application

### Windows

```batch
run.bat
```

**Note:** Run via Command Prompt (`cmd /c run.bat`) for proper input handling. PowerShell may cause issues with scanner input.

## Usage

1. **Take Attendance** - Select a course and start scanning QR codes
2. **Add Course** - Create new course (format: `name-code-year`, e.g., `Math-CS101-2026`)
3. **Show Courses** - View and manage existing courses
4. **Exit** - Close the application

### During Scanning

- Scan QR codes containing student ID and name
- Press `Y` to save and quit
- Press `N` to exit without saving
- Auto-backup triggers every 5 students

## Dependencies

- `webcam-capture` - Camera access
- `zxing-core` / `zxing-javase` - QR code decoding
- `opencsv` / `fastexcel` - Excel file handling
- `log4j` - Logging
- `bridj` - Native library bindings

## Data Storage

- Courses saved in `data/courses.json`
- Attendance records saved in `data/attendance/[course]_[timestamp].xlsx`
- Auto-backups in `data/backup/`