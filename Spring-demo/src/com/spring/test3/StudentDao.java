package com.spring.test3;

import javax.sql.DataSource;
import java.util.List;

public interface StudentDao {
    void setDataSource(DataSource ds);

    void create(String name, int age, int marks, int year);

    List<StudentMarks> listStudents();
}
