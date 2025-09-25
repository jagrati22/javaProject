package edu.ccrm.exception;

/**
 * Thrown when a student attempts to enroll in a course they are already enrolled in.
 */
public class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}