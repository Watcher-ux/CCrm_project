package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Student;
// TODO: Import custom exceptions you will create later
import edu.ccrm.exception.DuplicateStudentException;

public class StudentService {
    private final DataStore dataStore = DataStore.getInstance();

 // Inside the StudentService class in StudentService.java

    public void addStudent(Student student) throws DuplicateStudentException {
        if (dataStore.students.containsKey(student.getRegNo())) {
            throw new DuplicateStudentException("A student with registration number " + student.getRegNo() + " already exists.");
        }
        dataStore.students.put(student.getRegNo(), student);
    }

    public Student findStudentByRegNo(String regNo) {
        // TODO: Logic to find a student.
        // 1. Get the student from the dataStore.students map.
        // 2. If the student is not found, it will return null. The CLI will handle this.
        return dataStore.students.get(regNo);
    }
    
    // TODO: Add methods for updateStudent, deactivateStudent, etc.
}