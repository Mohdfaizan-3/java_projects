package com.example.crud_db_project.dao;

import com.example.crud_db_project.entity.Employee;

import java.util.List;

//DAO (Data Access Object): DAO is a design pattern that provides an abstract
// interface to some type of
// database or other persistence mechanism. It provides specific data operations
// without exposing details of the database.
public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
