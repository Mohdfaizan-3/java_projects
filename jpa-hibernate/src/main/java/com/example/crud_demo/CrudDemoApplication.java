package com.example.crud_demo;

import com.example.crud_demo.dao.StudentDao;
import com.example.crud_demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao) {//executed after the beans are loaded
		return runner -> {
			//createStudent(studentDao);
			readStudent(studentDao);
		};
	}

	private void readStudent(StudentDao studentDao) {
		Student student = new Student("ravi", "yadav", "ravi@gmail.com");
		int id = student.getId();
		Student student1 = studentDao.findById(id);
		System.out.println(student);
	}

	private void createStudent(StudentDao studentDao) {
		//create student obj
		Student student = new Student("rahul", "kumar", "rahul@gmail.com");

		//save student obj
		studentDao.save(student);

		//display id of saved student
		System.out.println(student.getId());

	}

}

