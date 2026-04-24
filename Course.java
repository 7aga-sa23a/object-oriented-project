import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Course {
    public String ID;
    public String name;
    public int year;
    public int semester;

    public Course() {
    }

    public Course(String ID, String name, int year, int semester) {
        this.ID = ID;
        this.name = name;
        this.year = year;
        this.semester = semester;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void addCourse() throws Exception {
        List<Course> courses = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please Enter Course ID:");
        String courseID = in.nextLine();

        System.out.println("Please Enter Course Name:");
        String courseName = in.nextLine();

        System.out.println("Please Enter Year:");
        int courseYear = in.nextInt();

        System.out.println("Please Enter Semester:");
        int courseSemester = in.nextInt();

        courses.add(new Course(courseID, courseName, courseYear,courseSemester));
      
        ObjectMapper mapper = new ObjectMapper();

        new File("output").mkdirs();

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("output/courses.json"), courses);
    }

    public static void main(String[] args) throws Exception {

        Course s = new Course();
        s.addCourse();
    }
}