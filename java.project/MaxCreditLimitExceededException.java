package edu.ccrm.exception;

/**
 * Thrown when an enrollment would cause a student to exceed their maximum allowed credits.
 */
public class MaxCreditLimitExceededException extends Exception {
    public MaxCreditLimitExceededException(String message) {
        super(message);
    }
}