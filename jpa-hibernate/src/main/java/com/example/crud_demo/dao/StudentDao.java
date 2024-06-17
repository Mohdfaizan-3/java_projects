package com.example.crud_demo.dao;

import com.example.crud_demo.entity.Student;

public interface StudentDao {
    void save(Student student);
    Student findById(Integer id);
}
