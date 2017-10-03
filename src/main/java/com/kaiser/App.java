package com.kaiser;

import com.kaiser.dao.StudentDao;
import com.kaiser.dao.StudentDaoImpl;
import com.kaiser.dbconnection.DBGenerator;
import com.kaiser.dbconnection.DataSource;
import com.kaiser.model.Student;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("App started...");

        Connection connection = null;

        // Create database, table and insert some dummy data
//        try {
//            connection = DataSource.getInstance().getConnection();
//            new DBGenerator(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }



        StudentDao studentDao = new StudentDaoImpl();

        //List all current students
        for (Student student : studentDao.getAllStudents()) {
            System.out.println("Student: [ RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
        }

        System.out.println("-------------------------------------------");

        //Create a new student
        Student aNewStudent = new Student(106, "Maria Akther");

        boolean created = studentDao.createStudent(aNewStudent);

        if (created){
            System.out.println("Student created successfully");
        }

        System.out.println("-------------------------------------------");

        //List all current students
        for (Student student2 : studentDao.getAllStudents()) {
            System.out.println("Student: [ RollNo : " + student2.getRollNo() + ", Name : " + student2.getName() + " ]");
        }

        System.out.println("-------------------------------------------");

        //Get a single student
        Student singleStudent = studentDao.getStudent(101);
        System.out.println("Student: [ RollNo : " + singleStudent.getRollNo() + ", Name : " + singleStudent.getName() + " ]");

        System.out.println("-------------------------------------------");

        //Delete a student
        studentDao.deleteStudent(aNewStudent);
        System.out.println("-------------------------------------------");

        //List all current students
        for (Student student2 : studentDao.getAllStudents()) {
            System.out.println("Student: [ RollNo : " + student2.getRollNo() + ", Name : " + student2.getName() + " ]");
        }


        System.out.println("---------------Using Generic driver----------------------------");
        //Get a single student
        Student singleStudentUsingGenericDriver = studentDao.getStudentUsingGenericDriver(101);
        System.out.println("Student: [ RollNo : " + singleStudentUsingGenericDriver.getRollNo() + ", Name : " + singleStudentUsingGenericDriver.getName() + " ]");





    }
}
