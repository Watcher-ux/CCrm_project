package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import java.util.List;

public class TranscriptService {

    public double calculateGpa(Student student) {
        List<Enrollment> gradedCourses = student.getEnrolledCourses().stream()
                .filter(e -> e.getGrade() != Grade.NA) // Only consider graded courses
                .toList();

        if (gradedCourses.isEmpty()) {
            return 0.0;
        }

        double totalPoints = gradedCourses.stream()
                .mapToDouble(e -> e.getGrade().getGradePoint() * e.getCourse().getCredits())
                .sum();

        int totalCredits = gradedCourses.stream()
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();

        return totalPoints / totalCredits;
    }

    public void printTranscript(Student student) {
        System.out.println("\n--- OFFICIAL TRANSCRIPT ---");
        System.out.println("Student: " + student.getFullName() + " (Reg No: " + student.getRegNo() + ")");
        System.out.println("------------------------------------------");

        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (Enrollment enrollment : student.getEnrolledCourses()) {
                // This relies on the toString() methods you wrote earlier!
                System.out.println(enrollment);
            }
        }
        
        System.out.println("------------------------------------------");
        double gpa = calculateGpa(student);
        // We use printf for formatted output to 2 decimal places
        System.out.printf("Cumulative GPA: %.2f\n", gpa);
        System.out.println("--- END OF TRANSCRIPT ---");
    }
}