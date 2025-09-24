package edu.ccrm.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private Student student;
    private Course course;
    private Grade grade;
    private LocalDateTime enrollmentDate;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = Grade.NA; // Default to Not Available
        this.enrollmentDate = LocalDateTime.now();
    }
 // Inside the Enrollment class in Enrollment.java

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    // Also, add a getter for the grade if you don't have one
    public Grade getGrade() {
        return grade;
    }

    // TODO: Add getters for all fields and a setter for the grade
 // Inside the Enrollment class in Enrollment.java

    public Course getCourse() {
        return course;
    }
    @Override
    public String toString() {
        return String.format("Course: %s | Grade: %s", course.getTitle(), grade.name());
    }
}