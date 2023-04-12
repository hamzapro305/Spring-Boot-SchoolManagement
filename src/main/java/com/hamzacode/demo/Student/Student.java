package com.hamzacode.demo.Student;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public class Student {
    public final UUID studentId;
    @NotBlank(message = "First name is required")
    private final String firstName;
    @NotBlank(message = "Last name is required")
    private final String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email address")
    private final String email;
    @NotNull(message = "Age is required")
    @PositiveOrZero(message = "Age cannot be negative")
    private final Integer age;
    @NotNull(message = "Gender is required")
    private final Gender gender;

    public Student(@JsonProperty("studentId") UUID studentId,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("email") String email,
                   @JsonProperty("age") Integer age,
                   @JsonProperty("gender") Gender gender) {

        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

//    public static boolean isValidEmail(String email) {
//        Pattern VALID_EMAIL_ADDRESS_REGEX =
//                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
//        return matcher.matches();
//    }

    enum Gender {
        MALE, FEMALE
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
