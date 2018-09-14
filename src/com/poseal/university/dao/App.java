package com.poseal.university.dao;

import java.util.List;

import com.poseal.university.dao.impl.StudentDaoImpl;
import com.poseal.university.model.Student;

public class App {

    public static void main(String[] args) {
        StudentDaoImpl sdi = new StudentDaoImpl();
        List<Student> list = sdi.findAll();

        for (Student student : list) {
            System.out.println(student);
        }
    }
}
