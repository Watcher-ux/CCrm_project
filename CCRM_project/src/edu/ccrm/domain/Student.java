package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private String regNo;
    private LocalDate registrationDate;
    private List<Enrollment> enrolledCourses; // We will create Enrollment next

    public Student(String id, String fullName, String email, String regNo) {
        // Call the constructor of the parent class (Person)
        super(id, fullName, email);
        this.regNo = regNo;
        this.registrationDate = LocalDate.now(); // Using the new Date/Time API
        this.enrolledCourses = new ArrayList<>();
    }

    @Override
    public String getDetails() {
        return String.format("Student: %s (Reg No: %s)", fullName, regNo);
    }
    // Inside the Student class in Student.java

    public void addEnrollment(Enrollment enrollment) {
        this.enrolledCourses.add(enrollment);
    }
    // TODO: Add getters for all fields. In Eclipse:
    // Right-click -> Source -> Generate Getters and Setters...

    // TODO: Add a method to enroll in a course, e.g., addEnrollment(Enrollment e)
    // Inside the Student class in Student.java

    public String getRegNo() {
        return regNo;
    }

    public List<Enrollment> getEnrolledCourses() {
        return enrolledCourses;
    }
    @Override
    public String toString() {
        // A good toString() is essential for clean console output
        return getDetails();
    }
}