package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String name;
    private String courseCode;
    private int numberOfStudents;
    private LocalDate date;
    private transient List<AttendanceRecord> records;

    public Course() {
        this.records = new ArrayList<>();
    }

    public Course(String name) {
        this.name = name;
        this.date = LocalDate.now();
        this.records = new ArrayList<>();
    }

    public Course(String name, String courseCode, int numberOfStudents) {
        this.name = name;
        this.courseCode = courseCode;
        this.numberOfStudents = numberOfStudents;
        this.date = LocalDate.now();
        this.records = new ArrayList<>();
    }

    public Course(String name, LocalDate date) {
        this.name = name;
        this.date = date;
        this.records = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<AttendanceRecord> getRecords() {
        return records;
    }

    public void setRecords(List<AttendanceRecord> records) {
        this.records = records;
    }

    public void addRecord(AttendanceRecord record) {
        this.records.add(record);
    }

    public String getFormattedDate() {
        return date != null ? date.format(DATE_FORMAT) : "";
    }

    public String getBackupFileName() {
        return getDisplayName() + "_" + getFormattedDate() + "_backup.xlsx";
    }

    public String getExportFileName() {
        return getDisplayName() + "_" + getFormattedDate() + ".xlsx";
    }

    public String getDisplayName() {
        return name + "-" + courseCode + "-" + date.getYear();
    }

    public int getRecordCount() {
        return records.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course subject = (Course) o;
        return Objects.equals(name, subject.name) &&
               Objects.equals(date, subject.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date);
    }

    @Override
    public String toString() {
        return getDisplayName() + " - " + getRecordCount() + " records";
    }
}