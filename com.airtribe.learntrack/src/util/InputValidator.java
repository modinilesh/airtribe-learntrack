package util;

import exception.InvalidInputException;

public class InputValidator {

    public static int parseInt(String input) throws InvalidInputException {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number entered. Please enter a valid integer.");
        }
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
}
