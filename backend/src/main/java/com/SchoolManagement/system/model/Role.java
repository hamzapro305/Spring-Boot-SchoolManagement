package com.SchoolManagement.system.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    ROLE_ADMIN, ROLE_STUDENT, ROLE_TEACHER;
    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(name());
    }


}
