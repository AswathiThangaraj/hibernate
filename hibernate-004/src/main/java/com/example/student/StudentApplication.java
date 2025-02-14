package com.example.student;

public class StudentApplication {
    public static void main(String[] args) {
        ManageStudent ms = new ManageStudent();

        // Add Students
        ms.addStudent("Aswathi", "T", 1000);
        ms.addStudent("Bhava", "L", 5000);
        ms.addStudent("Tharini", "L", 10000);

        // List Students
        ms.listStudents();
    }
}
