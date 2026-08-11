package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public final class InputValidator {

    private InputValidator() {
        // Prevent instantiation — use static methods only.
    }

    public static int parseInt(String input) throws InvalidInputException {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number entered. Please enter a valid integer.");
        }
    }

    public static int requirePositiveInt(String input, String fieldName) throws InvalidInputException {
        int value = parseInt(input);
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be a positive integer.");
        }
        return value;
    }

    public static void requireNonEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static String requireNonEmptyString(String value, String fieldName) throws InvalidInputException {
        requireNonEmpty(value, fieldName);
        return value.trim();
    }

    public static String requireValidEmail(String email) throws InvalidInputException {
        email = requireNonEmptyString(email, "Email");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new InvalidInputException("Email format is invalid.");
        }
        return email;
    }

    public static void requireNonNull(Object value, String fieldName) throws InvalidInputException {
        if (value == null) {
            throw new InvalidInputException(fieldName + " cannot be null.");
        }
    }
}
