package services;

import models.AttendanceRecord;
import models.Course;
import models.Student;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private final CourseService courseService;
    private Course currentCourse;
    private final List<AttendanceRecord> currentSession;

    public AttendanceService(CourseService courseService) {
        this.courseService = courseService;
        this.currentCourse = null;
        this.currentSession = new ArrayList<>();
    }

    public void setCurrentCourse(Course course) {
        this.currentCourse = course;
        currentSession.clear();
        if (course != null) {
            currentSession.addAll(course.getRecords());
        }
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public boolean addStudent(Student student) {
        if (student == null || currentCourse == null) {
            return false;
        }

        if (isDuplicate(student)) {
            System.out.println("[DUPLICATE] Already recorded: " + student.getName());
            return false;
        }

        AttendanceRecord record = new AttendanceRecord(student, DateUtils.getNow());
        currentSession.add(record);
        currentCourse.addRecord(record);
        courseService.updateCourse(currentCourse);
        return true;
    }

    public boolean isDuplicate(Student student) {
        return currentSession.stream().anyMatch(r -> r.getStudent().equals(student));
    }

    public List<AttendanceRecord> getCurrentRecords() {
        return new ArrayList<>(currentSession);
    }

    public int getTotalCount() {
        return currentSession.size();
    }

    public void clearSession() {
        currentSession.clear();
    }
}