package com.example.demo.controller;


import java.util.List;

import com.example.demo.domain.BookDO;
import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/hello")
    String hello(){
        return "hello tuçe";
    }

    @PostMapping(path = "/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDO> createBook(@RequestBody BookDO bookDO){
        BookDO createdBook = bookService.createBook(bookDO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping(path = "/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDO bookDO) {
        BookDTO updatedBook = bookService.updateBook(bookDO);
        return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
    }
    
    // @PutMapping(path = "/book", consumes = "application/json", produces = "application/json")
    // public ResponseEntity<BookDO> updateBook(@RequestBody BookDO bookDO) {
    //     BookDO updatedBook = bookService.updateBook(bookDO);
    //     return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
    // }
    @GetMapping(path = "/books/{bookId}")
    public ResponseEntity<BookDO> getBook(@PathVariable(value="bookId") Long bookId) {
        BookDO book = bookService.getBook(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(path = "/books-by-/{author}")
    public ResponseEntity<BookDO> getBook(@PathVariable(value="author") String author) {
        BookDO book = bookService.getBook(author);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }


    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDO>> getAllCustomers() {
        List<BookDO> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book with id: " + bookId + " is deleted.", HttpStatus.OK);
    }
}
