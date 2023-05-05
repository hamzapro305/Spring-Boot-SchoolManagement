package com.hamzacode.demo.repository;

import com.hamzacode.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepo {
    private final JdbcTemplate jdbcTemplate;

    private static final Integer size = 20;

    @Autowired
    public StudentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student addStudent(Student std){
        String sql = "INSERT INTO student (student_id, first_name, last_name, email, age, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        UUID StudentId = UUID.randomUUID();

        Object[] params = {StudentId, std.getFirstName(), std.getLastName(), std.getEmail(), std.getAge(), std.getGender()};

        jdbcTemplate.update(sql, params);

        std.setStudentId(StudentId);

        return std;
    }

    public Student getStudentById(UUID id){
        String sql = "" +
                " SELECT " +
                " student_id, " +
                " first_name, " +
                " last_name, " +
                " email, " +
                " age, " +
                " gender " +
                " FROM student " +
                " WHERE student_id = ? ";
        Object[] params = {id};

        List<Student> students = jdbcTemplate.query(sql, params, mapStudentFromDb());

        return students.isEmpty() ? null : students.get(0);

    }

    public List<Student> selectAllStudents(Integer Page) {
        String sql = "" +
                " SELECT " +
                " student_id, " +
                " first_name, " +
                " last_name, " +
                " email, " +
                " age, " +
                " gender " +
                " FROM student " +
                " LIMIT ? OFFSET ?";
        Object[] params = { size, Page * size };
        return jdbcTemplate.query(sql, params, mapStudentFromDb());
    }

    public Integer getTotalPages(){
        return Integer.valueOf(
                (int) Math.ceil(
                        this.getStudentCount() / size
                )
        );
    }

    private Integer getStudentCount(){
        String sql = "SELECT COUNT(*) FROM student";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender").toUpperCase();

            return new Student(
                    studentId,
                    firstName,
                    lastName,
                    email,
                    age,
                    gender
            );
        };
    }

}
