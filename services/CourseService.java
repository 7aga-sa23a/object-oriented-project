package services;

import models.Course;
import utils.DateUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private static final String DATA_FILE = "data/courses.json";
    private static final String DATA_DIR = "data/attendance";
    private final Gson gson;
    private final List<Course> courses;

    public CourseService() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new DateUtils.LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new DateUtils.LocalDateTimeAdapter())
                .create();
        this.courses = new ArrayList<>();
        ensureDataDirectory();
        loadCourses();
    }

    private void ensureDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        File attDir = new File(DATA_DIR);
        if (!attDir.exists()) {
            attDir.mkdirs();
        }
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public void addCourse(String name, String courseCode, int numberOfStudents) {
        Course course = new Course(name, courseCode, numberOfStudents);
        courses.add(course);
        saveCourses();
    }

    public Course getCourse(int index) {
        if (index >= 0 && index < courses.size()) {
            return courses.get(index);
        }
        return null;
    }

    public Course findCourse(String name) {
        LocalDate today = LocalDate.now();
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(name) && c.getDate().equals(today)) {
                return c;
            }
        }
        return null;
    }

    public Course findCourseByCode(String name, String courseCode) {
        LocalDate today = LocalDate.now();
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(name) && 
                c.getCourseCode().equalsIgnoreCase(courseCode) && 
                c.getDate().equals(today)) {
                return c;
            }
        }
        return null;
    }

    public void updateCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).equals(course)) {
                courses.set(i, course);
                saveCourses();
                return;
            }
        }
    }

    public boolean deleteCourse(int index) {
        if (index >= 0 && index < courses.size()) {
            courses.remove(index);
            saveCourses();
            return true;
        }
        return false;
    }

    public void loadCourses() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }
        try (java.io.Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Course>>() {}.getType();
            List<Course> loaded = gson.fromJson(reader, listType);
            if (loaded != null) {
                courses.clear();
                courses.addAll(loaded);
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    public void saveCourses() {
        try (java.io.Writer writer = new FileWriter(DATA_FILE)) {
            gson.toJson(courses, writer);
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    public int getCourseCount() {
        return courses.size();
    }
}