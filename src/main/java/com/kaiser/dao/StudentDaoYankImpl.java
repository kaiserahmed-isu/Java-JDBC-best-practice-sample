package com.kaiser.dao;

import com.kaiser.model.Student;
import org.knowm.yank.PropertiesUtils;
import org.knowm.yank.Yank;

import java.util.List;
import java.util.Properties;

public class StudentDaoYankImpl implements StudentDao{

    public StudentDaoYankImpl() {
        // DB Properties
        Properties dbProps = PropertiesUtils.getPropertiesFromClasspath("DERBY_DB.properties");
        Yank.setupDefaultConnectionPool(dbProps);

        // SQL Statements in Properties file
        Properties sqlProps = PropertiesUtils.getPropertiesFromClasspath("SQL_COMMANDS.properties");
        Yank.addSQLStatements(sqlProps);
    }

    public List<Student> getAllStudents() {
        // Using Direct SQL statement
        //String SQL = "SELECT * FROM STUDENTS";
        //List<Student> students = Yank.queryBeanList(SQL, Student.class, null);

        //Using SQL from Properties file
        String sqlKey = "ALL_STUDENTS";
        List<Student> students = Yank.queryBeanListSQLKey(sqlKey,Student.class, null);
        checkPoolStatus();
        return students;
    }

    public boolean createStudent(Student student) {

        Object[] params = new Object[] { student.getRollNo(),student.getName() };
        String SQL = "INSERT INTO STUDENTS values(?,?)";
        Long id = Yank.insert(SQL, params);
        if (id >0 ) {
            System.out.println("id: "+ id);
            return true;
        }
        return false;
    }

    public Student getStudent(int rollNo) {
        String SQL = "SELECT * FROM STUDENTS  WHERE rollNo = ?";
        Object[] params = new Object[] { rollNo };
        Student student = Yank.queryBean(SQL, Student.class, params);
        checkPoolStatus();
        return student;
    }

    public void updateStudent(Student student) {
        Object[] params = new Object[] { student.getName(), student.getRollNo() };
        String SQL = "UPDATE Students SET Name = ? WHERE rollNo = ?";
        int id = Yank.execute(SQL, params);
        if (id >0 ) {
            System.out.println("Student: Roll No " + student.getRollNo() + ", updated in database");
        }
    }

    public void deleteStudent(Student student) {
        String SQL = "DELETE FROM STUDENTS  WHERE rollNo = ?";
        Object[] params = new Object[] { student.getRollNo() };
        int i = Yank.execute(SQL, params);
        if (i > 0){
            System.out.println("Student: Roll No " + student.getRollNo() + ", deleted from database, i: " + i);
        }

    }

    public Student getStudentUsingGenericDriver(int rollNo) {
        return null;
    }

    public void checkPoolStatus(){
        System.out.println("Active connection: " + Yank.getDefaultConnectionPool().getHikariPoolMXBean().getActiveConnections() + ", Idle Connection: " + Yank.getDefaultConnectionPool().getHikariPoolMXBean().getIdleConnections());
    }
}
