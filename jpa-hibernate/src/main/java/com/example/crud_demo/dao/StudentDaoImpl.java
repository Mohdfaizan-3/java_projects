package com.example.crud_demo.dao;

import com.example.crud_demo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao{

    //define fields for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implements save
    @Transactional
    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }
}
