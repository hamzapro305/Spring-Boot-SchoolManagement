package com.SchoolManagement.system.model;

public enum UserType {
    ADMIN, STUDENT, TEACHER;

    public String getType() {
        return name();
    }
}
