package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AttendanceRecord {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Student student;
    private LocalDateTime scanTime;
    private String status;

    public AttendanceRecord() {}

    public AttendanceRecord(Student student, LocalDateTime scanTime) {
        this.student = student;
        this.scanTime = scanTime;
        this.status = "Present";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormattedTime() {
        return scanTime != null ? scanTime.format(FORMAT) : "";
    }

    public String getStudentId() {
        return student != null ? student.getId() : "";
    }

    public String getStudentName() {
        return student != null ? student.getName() : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceRecord that = (AttendanceRecord) o;
        return Objects.equals(student, that.student) &&
               Objects.equals(scanTime, that.scanTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, scanTime);
    }

    @Override
    public String toString() {
        return student + " at " + getFormattedTime();
    }
}