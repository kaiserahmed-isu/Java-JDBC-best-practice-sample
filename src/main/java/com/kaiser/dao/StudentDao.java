package com.kaiser.dao;

import com.kaiser.model.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> getAllStudents();
    public boolean createStudent(Student student);
    public Student getStudent(int rollNo);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
    public Student getStudentUsingGenericDriver(int rollNo);
}
