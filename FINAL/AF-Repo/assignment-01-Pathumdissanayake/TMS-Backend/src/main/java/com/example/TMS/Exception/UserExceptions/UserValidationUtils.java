package com.example.TMS.Exception.UserExceptions;

import com.example.TMS.Exception.InvalidEmailException;
import com.example.TMS.Exception.InvalidStudentIdException;

public class UserValidationUtils {
    public static void validateEmail(String email) {
        if (!email.startsWith("it") || !email.contains("@my.sliit.lk")) {
            throw new InvalidEmailException("Invalid email. Must start with 'it' and contain '@my.sliit.lk'");
        }
    }

    public static void validateStudentId(String studentId) {
        if (!studentId.startsWith("it") || studentId.length() != 10 || !studentId.substring(2).matches("\\d{8}")) {
            throw new InvalidStudentIdException("Invalid student ID. Must start with 'it' and have 10 characters.");
        }
    }

    public static void validateNIC(String nic) {
        if (nic.length() != 12 || !nic.matches("\\d{12}")) {
            throw new InvalidNICException("Invalid NIC. Must contain exactly 12 digits.");
        }
    }

    public static void validatePassword(String password) {
        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()].*")) {
            throw new InvalidPasswordException("Invalid Password. Password must be at least 8 characters and contain upper case, lower case, numbers, and special characters.");
        }
    }

    public static void validateAcademicYear(String academicYear) {
        int currentYear = java.time.Year.now().getValue();
        int year;
        try {
            year = Integer.parseInt(academicYear);
        } catch (NumberFormatException e) {
            throw new InvalidAcademicYearException("Academic year must be a 4-digit number.");
        }
        if (year < currentYear - 6 || year > currentYear) {
            throw new InvalidAcademicYearException("Academic year must be the current year or one of the previous six years.");
        }
    }

    public static void validateDOB(String dob) {
        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDOBException("Date of Birth must be in the format YYYY-MM-DD.");
        }
    }
}
