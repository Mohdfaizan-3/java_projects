package com.project.library_app_react_springboot.dao;

import com.project.library_app_react_springboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
