package com.spring.test3;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    /**
     * RowMap映射Bean容器
     * jdbcTemplate.query(sql,new UserRowMapper)即可将查询的信息存入java Bean中，靠的是bean中的get/set方法。
     */
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        return student;
    }
}
