package com.example.crud_demo.dao;

import com.example.crud_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName asc", Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
        theQuery.setParameter("theData", lastName);
        return theQuery.getResultList();
    }

    @Transactional // since we are updating
    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);

    }

    @Transactional
    @Override
    public int deleteAll() {
        int rowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return rowsDeleted;
    }

//    @Override
//    public List<Student> findAll() {
//        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
//        return theQuery.getResultList();
//    }
}
