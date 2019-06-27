package com.paulocezar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulocezar.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	

}
