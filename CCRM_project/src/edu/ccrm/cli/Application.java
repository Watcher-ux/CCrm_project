package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.exception.*;
import edu.ccrm.io.FileService;
import edu.ccrm.service.*;
import edu.ccrm.util.FileUtils; // Make sure to import FileUtils
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Application {

    // Create instances of our services and a single scanner for input
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final FileService fileService = new FileService();
    private static final TranscriptService transcriptService = new TranscriptService();

    public static void main(String[] args) {
        System.out.println("Welcome to the Campus Course & Records Manager!");
        boolean exit = false;

        // The main application loop
        while (!exit) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine()); // Read integer and consume newline

             // In the main() method of Application.java
                switch (choice) {
                    case 1 -> manageStudents();
                    case 2 -> manageCourses();
                    case 3 -> enrollStudentInCourse();
                    case 4 -> exportAllData();
                    case 5 -> createBackup();
                    case 6 -> showBackupSize();
                    case 7 -> assignGrade();
                    case 8 -> printStudentTranscript();
                    case 10 -> importAllData(); // New case for importing
                    case 99 -> exit = true; // Updated exit choice
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            }
        }

        System.out.println("Thank you for using CCRM. Goodbye!");
        scanner.close();
    }

 // In Application.java
    private static void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Enroll Student in Course");
        System.out.println("---");
        System.out.println("4. Export Data");
        System.out.println("5. Create Backup");
        System.out.println("6. Show Backup Size");
        System.out.println("---");
        System.out.println("7. Assign Grade to Student");
        System.out.println("8. Print Student Transcript");
        System.out.println("---");
        System.out.println("10. Import Data from Files");
        System.out.println("---");
        System.out.println("99. Exit");
    }
 // In Application.java
    private static void importAllData() {
        System.out.println("\n--- Import Data ---");
        // Note: This directly modifies the in-memory data store.
        fileService.importData();
        System.out.println("Import process finished.");
    }
    private static void manageStudents() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add a new student");
        System.out.println("2. Find a student");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            addNewStudent();
        }
        // TODO: Add logic for finding a student
    }

    private static void manageCourses() {
        // TODO: Implement course management sub-menu
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add a new course");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            addNewCourse(); // We should create this method
        }
    }

    private static void enrollStudentInCourse() {
        try {
            System.out.println("\n--- Enroll Student in Course ---");
            System.out.print("Enter Student Registration Number: ");
            String regNo = scanner.nextLine();

            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();

            enrollmentService.enroll(regNo, courseCode);

        } catch (StudentNotFoundException | CourseNotFoundException | MaxCreditLimitExceededException | DuplicateEnrollmentException e) {
            System.err.println("Enrollment Failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void addNewStudent() {
        try {
            System.out.println("\n--- Add New Student ---");
            System.out.print("Enter Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Registration Number (e.g., S2025001): ");
            String regNo = scanner.nextLine();

            String id = regNo; // Use regNo as the unique ID

            Student newStudent = new Student(id, fullName, email, regNo);
            studentService.addStudent(newStudent);

            System.out.println("Student added successfully!");

        } catch (DuplicateStudentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    // You need a method to add courses
    private static void addNewCourse() {
        try {
            System.out.println("\n--- Add New Course ---");
            System.out.print("Enter Course Code (e.g., CS101): ");
            String code = scanner.nextLine();

            System.out.print("Enter Course Title: ");
            String title = scanner.nextLine();
            
            System.out.print("Enter Credits: ");
            int credits = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Department: ");
            String dept = scanner.nextLine();

            // Using the Builder pattern from the Course class
            Course newCourse = new Course.Builder(code)
                .withTitle(title)
                .withCredits(credits)
                .withDepartment(dept)
                .build();
            
            courseService.addCourse(newCourse);
            System.out.println("Course added successfully!");

        } catch (DuplicateCourseException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input for credits. Please enter a number.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Methods for File Operations
    private static void exportAllData() {
        try {
            System.out.println("Exporting all data...");
            fileService.exportData();
        } catch (IOException e) {
            System.err.println("Error: Failed to export data. " + e.getMessage());
        }
    }

    private static void createBackup() {
        try {
            System.out.println("Creating backup...");
            fileService.backupData();
        } catch (IOException e) {
            System.err.println("Error: Failed to create backup. " + e.getMessage());
        }
    }

    private static void showBackupSize() {
        try {
            Path backupDir = Paths.get("backups");
            long sizeInBytes = FileUtils.calculateDirectorySize(backupDir);
            double sizeInMB = sizeInBytes / (1024.0 * 1024.0);
            System.out.printf("Total size of backups directory is: %.2f MB\n", sizeInBytes);
        } catch (IOException e) {
            System.err.println("Error: Could not calculate backup size. " + e.getMessage());
        }
    }
 // Add these methods inside your Application class

    private static void assignGrade() {
        try {
            System.out.println("\n--- Assign Grade ---");
            System.out.print("Enter Student Registration Number: ");
            String regNo = scanner.nextLine();
            Student student = studentService.findStudentByRegNo(regNo);
            if (student == null) {
                throw new StudentNotFoundException("Student not found.");
            }

            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();

            // Find the specific enrollment
            Enrollment targetEnrollment = student.getEnrolledCourses().stream()
                .filter(e -> e.getCourse().getCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);

            if (targetEnrollment == null) {
                throw new Exception("Student is not enrolled in this course.");
            }

            System.out.print("Enter Letter Grade (S, A, B, C, D, E, F): ");
            String gradeStr = scanner.nextLine().toUpperCase();
            Grade grade = Grade.valueOf(gradeStr); // Converts the string "A" to Grade.A

            targetEnrollment.setGrade(grade);
            System.out.println("Grade assigned successfully.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printStudentTranscript() {
        try {
            System.out.println("\n--- Print Transcript ---");
            System.out.print("Enter Student Registration Number: ");
            String regNo = scanner.nextLine();
            Student student = studentService.findStudentByRegNo(regNo);
            if (student == null) {
                throw new StudentNotFoundException("Student not found.");
            }
            transcriptService.printTranscript(student);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}