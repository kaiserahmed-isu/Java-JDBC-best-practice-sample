package com.kaiser;

import com.kaiser.dao.StudentDao;
import com.kaiser.dao.StudentDaoYankImpl;
import com.kaiser.model.Student;

import java.sql.Connection;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("App started...");

        Connection connection = null;

        // Implementation using dbcp2
        //StudentDao studentDao = new StudentDaoDbcp2Impl();

        // Implementation using Yank
        StudentDao studentDao = new StudentDaoYankImpl();

        System.out.println("-------------------List all students------------------------");
        //List all current students
        for (Student student : studentDao.getAllStudents()) {
            System.out.println("Student: [ RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
        }

        System.out.println("-------------------Get a single student------------------------");

        //Get a single student
        Student singleStudent = studentDao.getStudent(101);
        System.out.println("Student: [ RollNo : " + singleStudent.getRollNo() + ", Name : " + singleStudent.getName() + " ]");


        System.out.println("-------------------Create a new student------------------------");
        //Create a new student
        Student aNewStudent = new Student(106, "Maria Akther");

        boolean created = studentDao.createStudent(aNewStudent);

        if (created){
            System.out.println("Student created successfully");
        }

        System.out.println("------------------Check the list again-------------------------");

        //List all current students
        for (Student student2 : studentDao.getAllStudents()) {
            System.out.println("Student: [ RollNo : " + student2.getRollNo() + ", Name : " + student2.getName() + " ]");
        }


        System.out.println("--------------------Delete a student-----------------------");

        //Delete a student
        studentDao.deleteStudent(aNewStudent);
        System.out.println("-----------------------Check the list again--------------------");

        //List all current students
        for (Student student2 : studentDao.getAllStudents()) {
            System.out.println("Student: [ RollNo : " + student2.getRollNo() + ", Name : " + student2.getName() + " ]");
        }

//
//        System.out.println("---------------Using Generic driver----------------------------");
//        //Get a single student
//        Student singleStudentUsingGenericDriver = studentDao.getStudentUsingGenericDriver(101);
//        System.out.println("Student: [ RollNo : " + singleStudentUsingGenericDriver.getRollNo() + ", Name : " + singleStudentUsingGenericDriver.getName() + " ]");





    }
}
