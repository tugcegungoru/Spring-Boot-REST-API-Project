package com.example.demo.repository;
import java.util.Optional;

import  com.example.demo.domain.BookDO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDO, Long> {
    Optional<BookDO> findByAuthor(String author);
}
