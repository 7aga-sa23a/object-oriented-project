package src.app;

import src.model.Attendance;

public class Main {
    public static void main(String[] args) {

        Attendance s1 = new Attendance("Ahmed", 18, 20);
        Attendance s2 = new Attendance("Sara", 15, 20);
        Attendance s3 = new Attendance("Mona", 10, 20);

        s1.display();
        s2.display();
        s3.display();
    }
}