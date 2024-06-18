package com.example.crud_db_project.dao;

import com.example.crud_db_project.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository: In Spring Data, a repository is an interface that extends CrudRepository
// or JpaRepository and is used to interact with the database.
// It abstracts DAO layers and automatically generates common CRUD operations.
@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    private EntityManager entityManager;

    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }
}

//EntityManager: The EntityManager interface is part of the Java Persistence API (JPA)
// and is used to create and manage database transactions with entities.