package com.hamzacode.demo.repository;

import com.hamzacode.demo.exception.CustomException;
import com.hamzacode.demo.model.Student;
import com.hamzacode.demo.model.UserRole;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepo {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public StudentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student addStudent(Student std){
        String sql = "INSERT INTO student " +
                "(" +
                " studentId, " +
                " userName, " +
                " fullName, " +
                " email, " +
                " age, " +
                " gender, " +
                " password, " +
                " roles " +
                ")" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        UUID StudentId = UUID.randomUUID();

        String roles = this.rolesToString(std.getRoles());

        Object[] params = {StudentId, std.getUserName(), std.getFullName(), std.getEmail(), std.getAge(), std.getGender(), std.getPassword(), roles};

        jdbcTemplate.update(sql, params);

        std.setStudentId(StudentId);

        return std;
    }

    public void deleteStudent(UUID uid){
        String sql = "DELETE FROM student WHERE studentId = ?";

        Object[] args = new Object[] {uid};

        try {
            boolean resp = jdbcTemplate.update(sql, args) == 1;
            if(!resp) throw new CustomException("Student Not Found");
        } catch (Exception ex){
            throw new CustomException(ex.getMessage());
        }
    }

    public Student getStudentById(UUID id){
        String sql = "" +
                " SELECT " +
                " studentId, " +
                " userName, " +
                " fullName, " +
                " email, " +
                " age, " +
                " gender, " +
                " password, " +
                " roles " +
                " FROM student " +
                " WHERE studentId = ? ";
        Object[] params = {id};

        List<Student> students = jdbcTemplate.query(sql, params, mapStudentFromDb());

        return students.isEmpty() ? null : students.get(0);

    }

    public Student getStudentByEmail(String Email){
        String sql = "" +
                " SELECT " +
                " studentId, " +
                " userName, " +
                " fullName, " +
                " email, " +
                " age, " +
                " gender, " +
                " password, " +
                " roles " +
                " FROM student " +
                " WHERE email = ? ";
        Object[] params = { Email };

        List<Student> students = jdbcTemplate.query(sql, params, mapStudentFromDb());

        return students.isEmpty() ? null : students.get(0);

    }

    public String rolesToString(List<UserRole> roles) {
        List<String> roleNames = new ArrayList<>();
        if(roles.size() == 0) return "";
        for(UserRole role: roles) roleNames.add(role.name());
        return String.join(",", roleNames);
    }

    public List<UserRole> stringToRoles(String strRoles) {
        List<UserRole> roles = new ArrayList<>();
        if(strRoles.length() == 0) return roles;
        for (String role : strRoles.split(",")) roles.add(UserRole.valueOf(role));
        return roles;
    }

    public List<Student> selectAllStudents(Integer Page, Integer Size) {
        String sql = "" +
                " SELECT " +
                " studentId, " +
                " userName, " +
                " fullName, " +
                " email, " +
                " age, " +
                " gender, " +
                " password, " +
                " roles " +
                " FROM student " +
                " LIMIT ? OFFSET ?";
        Object[] params = { Size, Page * Size };
        return jdbcTemplate.query(sql, params, mapStudentFromDb());
    }

    public Integer getTotalPages(Integer Size){
        return Integer.valueOf(
                (int) Math.ceil(
                        this.getStudentCount() / Size
                )
        );
    }

    private Integer getStudentCount(){
        String sql = "SELECT COUNT(*) FROM student";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("studentId");
            UUID studentId = UUID.fromString(studentIdStr);

            String userName = resultSet.getString("userName");
            String fullName = resultSet.getString("fullName");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender").toUpperCase();
            String password = resultSet.getString("password");
            String strRoles = resultSet.getString("roles");
            List<UserRole> roles = this.stringToRoles(strRoles);

            return new Student(studentId, userName, fullName, email, age, gender, password, roles);
        };
    }

}
