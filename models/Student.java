package models;

import java.util.Objects;

public class Student {
    private String id;
    private String name;

    public Student() {}

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Student fromQRData(String qrData) {
        if (qrData == null || qrData.trim().isEmpty()) {
            return null;
        }

        qrData = qrData.trim();

        String[] parts = null;

        if (qrData.contains("|")) {
            parts = qrData.split("\\|", 2);
        } else if (qrData.contains(",")) {
            parts = qrData.split(",", 2);
        } else if (qrData.contains(";")) {
            parts = qrData.split(";", 2);
        }

        if (parts != null && parts.length >= 2) {
            String id = parts[0].trim();
            String name = parts[1].trim();

            return new Student(id, name);
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}