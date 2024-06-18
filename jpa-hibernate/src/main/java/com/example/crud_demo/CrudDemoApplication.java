package com.example.crud_demo;

import com.example.crud_demo.dao.StudentDao;
import com.example.crud_demo.entity.Student;
import com.example.crud_demo.rest.RestController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(CrudDemoApplication.class, args);
//		context.getBean(RestController.class);
	}


	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao) {//executed after the beans are loaded
		return runner -> {
			//createStudent(studentDao);
			//readStudent(studentDao);
			//queryForStudents(studentDao);
			//queryForStudentsByLastName(studentDao);
			//updateStudent(studentDao);
			//deleteStudent(studentDao);
			//deleteAllStudent(studentDao);
		};
	}

	private void deleteAllStudent(StudentDao studentDao) {
		int rowDeleted = studentDao.deleteAll();
		System.out.println(rowDeleted);
	}


	private void deleteStudent(StudentDao studentDao) {
		int studentId = 2;
		studentDao.delete(studentId);
	}

	private void updateStudent(StudentDao studentDao) {
		int studentId = 1;
		Student student = studentDao.findById(studentId);
		student.setFirstName("prakash");
		studentDao.update(student);
		System.out.println(student);
	}

	private void queryForStudentsByLastName(StudentDao studentDao) {
		List<Student> students = studentDao.findByLastName("yadav");
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDao studentDao) {
		List<Student> students = studentDao.findAll();
		for (Student student : students) {
			System.out.println(student);
		}

	}

	private void readStudent(StudentDao studentDao) {
		Student student = new Student("ravi", "yadav", "ravi@gmail.com");
		int id = student.getId();
		Student student1 = studentDao.findById(id);
		System.out.println(student);
	}

	private void createStudent(StudentDao studentDao) {
		//create student obj
		Student student = new Student("ravi", "yadav", "ravi@gmail.com");

		//save student obj
		studentDao.save(student);

		//display id of saved student
		System.out.println(student.getId());

	}

}

