package com.spring3;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

public class StudentImpl implements StudentDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    public void create(String name, int age, int marks, int year){
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            String SQL1 = "insert into student (name, age) values (?, ?)";
            jdbcTemplateObject.update(SQL1, name, age);
            // Get the latest student id to be used in Marks table
            String SQL2 = "select max(id) from Student";
            int sid = jdbcTemplateObject.queryForObject(SQL2, Integer.class);
            String SQL3 = "insert into student_marks(sid, marks, year) " +
                    "values (?, ?, ?)";
            jdbcTemplateObject.update( SQL3, sid, marks, year);
            System.out.println("Created Name = " + name + ", Age = " + age);
            transactionManager.commit(status);
//            throw new RuntimeException("simulate Error condition");
        } catch (DataAccessException e) {
            System.out.println("Error in creating record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return;
    }
    public List<StudentMarks> listStudents() {
        String SQL = "select * from student, student_marks where student.id=student_marks.sid";
        List <StudentMarks> studentMarks = jdbcTemplateObject.query(SQL,
                new StudentMarksMapper());
        return studentMarks;
    }
}
