package edu.ccrm.io;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {
    private final DataStore dataStore = DataStore.getInstance();
    private static final Path DATA_DIRECTORY = Paths.get("data");
    private static final Path BACKUP_DIRECTORY = Paths.get("backups");

    public void exportData() throws IOException {
        // Ensure the data directory exists
        if (Files.notExists(DATA_DIRECTORY)) {
            Files.createDirectories(DATA_DIRECTORY);
        }}
     // Inside the FileService class

     public void backupData() throws IOException {
            // 1. Ensure the main backup directory exists
            if (Files.notExists(BACKUP_DIRECTORY)) {
                Files.createDirectories(BACKUP_DIRECTORY);
            }

            // 2. Create a timestamp for the new backup folder's name
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            Path specificBackupPath = BACKUP_DIRECTORY.resolve("backup_" + timestamp);
            Files.createDirectory(specificBackupPath);

            // 3. Copy the exported files to the new backup folder
            try (Stream<Path> filesToBackup = Files.list(DATA_DIRECTORY)) {
                filesToBackup
                    .filter(Files::isRegularFile)
                    .forEach(sourceFile -> {
                        try {
                            Path destinationFile = specificBackupPath.resolve(sourceFile.getFileName());
                            Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            // This is where you'd use a real logger
                            System.err.println("Failed to back up file: " + sourceFile + " due to " + e.getMessage());
                        }
                    });
            }
            System.out.println("Data successfully backed up to: " + specificBackupPath);
        
        // --- 1. Export Students ---
        Path studentFile = DATA_DIRECTORY.resolve("students.csv");
        List<String> studentLines = dataStore.students.values().stream()
                .map(s -> String.join(",", s.getRegNo(), s.getFullName(), s.getEmail()))
                .collect(Collectors.toList());
        studentLines.add(0, "regNo,fullName,email"); // Add header
        Files.write(studentFile, studentLines);
        System.out.println("Exported " + (studentLines.size() - 1) + " students to " + studentFile);

        // --- 2. Export Courses ---
        Path courseFile = DATA_DIRECTORY.resolve("courses.csv");
        List<String> courseLines = dataStore.courses.values().stream()
                .map(c -> String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getDepartment()))
                .collect(Collectors.toList());
        courseLines.add(0, "code,title,credits,department"); // Add header
        Files.write(courseFile, courseLines);
        System.out.println("Exported " + (courseLines.size() - 1) + " courses to " + courseFile);
        
        // TODO: Implement export for enrollments (regNo, courseCode, grade)
    }
  // Inside the FileService class

     public void importData() {
         // Import courses first, as students will enroll in them.
         importCourses(DATA_DIRECTORY.resolve("courses.csv"));
         importStudents(DATA_DIRECTORY.resolve("students.csv"));
         // TODO: Implement import for enrollments
     }

     private void importStudents(Path path) {
         if (Files.notExists(path)) return;
         try (Stream<String> lines = Files.lines(path)) {
             lines.skip(1) // Skip header row
                  .map(line -> line.split(","))
                  .forEach(parts -> {
                      try {
                          Student student = new Student(parts[0], parts[1], parts[2], parts[0]);
                          DataStore.getInstance().students.put(student.getRegNo(), student);
                      } catch (Exception e) {
                          System.err.println("Skipping malformed student line: " + String.join(",", parts));
                      }
                  });
             System.out.println("Imported students from " + path);
         } catch (IOException e) {
             System.err.println("Failed to import students: " + e.getMessage());
         }
     }

     private void importCourses(Path path) {
         if (Files.notExists(path)) return;
         try (Stream<String> lines = Files.lines(path)) {
             lines.skip(1) // Skip header row
                  .map(line -> line.split(","))
                  .forEach(parts -> {
                      try {
                          Course course = new Course.Builder(parts[0])
                                  .withTitle(parts[1])
                                  .withCredits(Integer.parseInt(parts[2]))
                                  .withDepartment(parts[3])
                                  .build();
                          DataStore.getInstance().courses.put(course.getCode(), course);
                      } catch (Exception e) {
                          System.err.println("Skipping malformed course line: " + String.join(",", parts));
                      }
                  });
             System.out.println("Imported courses from " + path);
         } catch (IOException e) {
             System.err.println("Failed to import courses: " + e.getMessage());
         }
     } 
}