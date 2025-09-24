package edu.ccrm.exception;

public class DuplicateStudentException extends Exception {
    public DuplicateStudentException(String message) {
        super(message);
    }
}