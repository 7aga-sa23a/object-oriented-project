package src.model;

public class Attendance {
    String studentName;
    int attendedLectures;
    int totalLectures;

    // Constructor
    public Attendance(String name, int attended, int total) {
        studentName = name;
        attendedLectures = attended;
        totalLectures = total;
    }

    // حساب النسبة
    double calculatePercentage() {
        return ((double) attendedLectures / totalLectures) * 100;
    }

    // تحديد التقييم
    String getGrade() {
        double percent = calculatePercentage();

        if (percent >= 90)
            return "A";
        else if (percent >= 80)
            return "B";
        else if (percent >= 70)
            return "C";
        else if (percent >= 60)
            return "D";
        else
            return "F";
    }

    // عرض بيانات الطالب
    public void display() {
        double percent = calculatePercentage();

        System.out.println("Student Name: " + studentName);
        System.out.println("Attendance: " + attendedLectures + "/" + totalLectures);
        System.out.printf("Percentage: %.2f%%\n", percent);
        System.out.println("Grade: " + getGrade());
        System.out.println("----------------------------");
    }
}