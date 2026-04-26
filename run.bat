@echo off

echo Compiling...

if not exist target\classes mkdir target\classes

javac -encoding UTF-8 -d target\classes -cp "lib\*" utils\DateUtils.java utils\Menu.java utils\Banner.java utils\ConsoleColor.java
if errorlevel 1 (
    echo Utils compilation failed!
    pause
    exit
)

javac -encoding UTF-8 -d target\classes -cp "lib\*;target\classes" models\Course.java models\Student.java models\AttendanceRecord.java
if errorlevel 1 (
    echo Models compilation failed!
    pause
    exit
)

javac -encoding UTF-8 -d target\classes -cp "lib\*;target\classes" services\CourseService.java services\CourseStudentJsonService.java services\AttendanceService.java services\CameraService.java services\QRScannerService.java services\ExcelExportService.java services\BackupService.java
if errorlevel 1 (
    echo Services compilation failed!
    pause
    exit
)

javac -encoding UTF-8 -d target\classes -cp "lib\*;target\classes" Main.java
if errorlevel 1 (
    echo Main compilation failed!
    pause
    exit
)

echo.
echo Running application...
echo.
java -cp "lib\*;target\classes" Main

pause