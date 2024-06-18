package com.example.crud_db_project.service;

import com.example.crud_db_project.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
}
