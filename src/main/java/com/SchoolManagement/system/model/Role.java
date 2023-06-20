package com.SchoolManagement.system.model;

public enum Role {
    ROLE_ADMIN, ROLE_STUDENT, ROLE_TEACHER;
    public String getAuthority() {
        return name();
    }


}
