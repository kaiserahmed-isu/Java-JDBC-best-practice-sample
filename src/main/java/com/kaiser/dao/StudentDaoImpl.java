package com.kaiser.dao;

import com.kaiser.dbconnection.DataSource;
import com.kaiser.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    List<Student> students;

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public StudentDaoImpl(){

//        Student student1 = new Student("Robert",0);
//        Student student2 = new Student("John",1);
//        students.add(student1);
//        students.add(student2);
    }


    //retrive list of students from the database
    public List<Student> getAllStudents() {
        students = new ArrayList<Student>();

        try {
            connection = DataSource.getInstance().getConnection();
            DataSource.getInstance().printDbStatus();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Students");
            while (resultSet.next()) {
                Student student = new Student();
                student.setRollNo(resultSet.getInt("rollNo"));
                student.setName(resultSet.getString("Name"));

                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }

        return students;
    }

    public boolean createStudent(Student student){
        boolean success = false;
        try {
            connection = DataSource.getInstance().getConnection();
            //DataSource.getInstance().printDbStatus();
            PreparedStatement stmt=connection.prepareStatement("insert into Students values(?,?)");
            stmt.setInt(1,student.getRollNo());//1 specifies the first parameter in the query
            stmt.setString(2, student.getName());

            int i=stmt.executeUpdate();
            if (i > 0){
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }

        return success;
    }

    public Student getStudent(int rollNo) {
        Student student = new Student();
        try {
            connection = DataSource.getInstance().getConnection();
            //DataSource.getInstance().printDbStatus();
            PreparedStatement stmt=connection.prepareStatement("SELECT * FROM Students WHERE rollNo = ?");
            stmt.setInt(1,rollNo);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                student.setRollNo(resultSet.getInt("rollNo"));
                student.setName(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }
        return student;
    }

    public void updateStudent(Student student) {
        try {
            connection = DataSource.getInstance().getConnection();
            //DataSource.getInstance().printDbStatus();
            PreparedStatement stmt=connection.prepareStatement("UPDATE Students SET Name = ? WHERE rollNo = ?");
            stmt.setString(1, student.getName());
            stmt.setInt(2,student.getRollNo());

            int i=stmt.executeUpdate();
            if (i > 0){
                System.out.println("Student: Roll No " + student.getRollNo() + ", updated in database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }

    }

    public void deleteStudent(Student student) {
        try {
            connection = DataSource.getInstance().getConnection();
            //DataSource.getInstance().printDbStatus();
            PreparedStatement stmt=connection.prepareStatement("Delete FROM Students WHERE rollNo = ?");
            stmt.setInt(1,student.getRollNo());//1 specifies the first parameter in the query

            int i=stmt.executeUpdate();
            if (i > 0){
                System.out.println("Student: Roll No " + student.getRollNo() + ", deleted from database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }

    }


    public Student getStudentUsingGenericDriver(int rollNo) {
        Student student = new Student();
        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            Connection connection=DriverManager.getConnection("jdbc:derby:StudentDB;create=true","","");

            PreparedStatement stmt=connection.prepareStatement("SELECT * FROM Students WHERE rollNo = ?");
            stmt.setInt(1,rollNo);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                student.setRollNo(resultSet.getInt("rollNo"));
                student.setName(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }
        return student;
    }

}
