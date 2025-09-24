package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.exception.*;

public class EnrollmentService {
    // These services will find the objects for us.
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();

    // This method shows how to use 'throws' for your custom checked exceptions.
 // Inside the EnrollmentService class in EnrollmentService.java

    public void enroll(String regNo, String courseCode)
            throws StudentNotFoundException, CourseNotFoundException, MaxCreditLimitExceededException, DuplicateEnrollmentException {

        final int MAX_CREDITS_PER_SEMESTER = 20;

        // 1. Find the student using the StudentService
        Student student = studentService.findStudentByRegNo(regNo);
        if (student == null) {
            throw new StudentNotFoundException("Student not found with registration number: " + regNo);
        }

        // 2. Find the course using the CourseService
        Course course = courseService.findCourseByCode(courseCode);
        if (course == null) {
            throw new CourseNotFoundException("Course not found with code: " + courseCode);
        }

        // 3. Check for duplicate enrollment
        boolean isAlreadyEnrolled = student.getEnrolledCourses().stream()
                .anyMatch(enrollment -> enrollment.getCourse().getCode().equals(courseCode));
        if (isAlreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student is already enrolled in course " + courseCode);
        }

        // 4. Check credit limit using a stream to sum credits
        int currentCredits = student.getEnrolledCourses().stream()
                .mapToInt(enrollment -> enrollment.getCourse().getCredits())
                .sum();

        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException("Enrollment failed: Exceeds maximum credit limit of " + MAX_CREDITS_PER_SEMESTER);
        }

        // 5. If all checks pass, create the enrollment and add it to the student
        Enrollment newEnrollment = new Enrollment(student, course);
        student.addEnrollment(newEnrollment);
        System.out.println("Enrollment successful!"); // Confirmation message
    }}