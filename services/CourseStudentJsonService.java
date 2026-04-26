package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import models.Course;
import models.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CourseStudentJsonService {
    private final Gson gson;

    public CourseStudentJsonService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static class CourseStudentsJson {
        public String Coursename;
        public String courseCode;
        public List<StudentData> Students;

        public CourseStudentsJson() {
            this.Students = new ArrayList<>();
        }

        public CourseStudentsJson(String courseName, String courseCode) {
            this.Coursename = courseName;
            this.courseCode = courseCode;
            this.Students = new ArrayList<>();
        }
    }

    public static class StudentData {
        public String name;
        public String id;

        public StudentData() {}

        public StudentData(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    private String getJsonFileName(Course course) {
        return course.getName() + " student.json";
    }

    private String getJsonFilePath(Course course) {
        return "data/attendance/" + getJsonFileName(course);
    }

    private Path getJsonDirectory() {
        return Paths.get("data/attendance");
    }

    private CourseStudentsJson loadExistingJson(Course course) {
        File file = new File(getJsonFilePath(course));
        if (!file.exists()) {
            return null;
        }
        try {
            String json = new String(Files.readAllBytes(file.toPath()));
            return gson.fromJson(json, CourseStudentsJson.class);
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("[JSON] Error reading file: " + e.getMessage());
            return null;
        }
    }

    private void ensureDirectoryExists() {
        try {
            Files.createDirectories(getJsonDirectory());
        } catch (IOException e) {
            System.out.println("[JSON] Error creating directory: " + e.getMessage());
        }
    }

    public boolean addStudentToJson(Course course, Student student) {
        if (course == null || student == null) {
            return false;
        }

        ensureDirectoryExists();
        String filePath = getJsonFilePath(course);

        CourseStudentsJson jsonData = loadExistingJson(course);
        boolean isNewFile = false;

        if (jsonData == null) {
            jsonData = new CourseStudentsJson(course.getName(), course.getCourseCode());
            isNewFile = true;
        }

        if (isStudentAlreadyInJson(jsonData, student)) {
            System.out.println("[JSON] Student already exists: " + student.getName());
            return false;
        }

        jsonData.Students.add(new StudentData(student.getName(), student.getId()));

        return saveJsonToFile(filePath, jsonData, isNewFile);
    }

    private boolean isStudentAlreadyInJson(CourseStudentsJson jsonData, Student student) {
        return jsonData.Students.stream()
                .anyMatch(s -> s.id.equals(student.getId()));
    }

    private boolean saveJsonToFile(String filePath, CourseStudentsJson jsonData, boolean isNewFile) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(jsonData, writer);
            String action = isNewFile ? "Created" : "Updated";
            String fileName = new File(filePath).getName();
            System.out.println("[" + action + " JSON] " + fileName);
            System.out.println("[JSON] Saved: " + filePath + " (Total students: " + jsonData.Students.size() + ")");
            return true;
        } catch (IOException e) {
            System.out.println("[JSON] Error saving file: " + e.getMessage());
            return false;
        }
    }

    public boolean syncWithExistingRecords(Course course) {
        if (course == null || course.getRecords() == null) {
            return false;
        }

        ensureDirectoryExists();
        String filePath = getJsonFilePath(course);

        CourseStudentsJson jsonData = loadExistingJson(course);
        boolean isNewFile = false;

        if (jsonData == null) {
            jsonData = new CourseStudentsJson(course.getName(), course.getCourseCode());
            isNewFile = true;
        }

        int addedCount = 0;
        for (var record : course.getRecords()) {
            Student student = record.getStudent();
            if (!isStudentAlreadyInJson(jsonData, student)) {
                jsonData.Students.add(new StudentData(student.getName(), student.getId()));
                addedCount++;
            }
        }

        if (addedCount > 0 || isNewFile) {
            return saveJsonToFile(filePath, jsonData, isNewFile);
        }

        return true;
    }

    public boolean deleteJsonFile(Course course) {
        File file = new File(getJsonFilePath(course));
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public List<StudentData> getStudentsFromJson(Course course) {
        CourseStudentsJson jsonData = loadExistingJson(course);
        return jsonData != null ? jsonData.Students : new ArrayList<>();
    }
}