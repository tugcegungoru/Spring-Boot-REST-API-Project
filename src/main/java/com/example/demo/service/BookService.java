package com.example.demo.service;

import com.example.demo.domain.BookDO;
import com.example.demo.dto.BookDTO;
import java.util.List;

public interface BookService {
    BookDO createBook(BookDO book);
    // BookDO updateBook(BookDO book);
    BookDTO updateBook(BookDO book);
    void deleteBook(Long bookId);
    BookDO getBook(Long bookId);
    List<BookDO> getAllBooks();
    BookDO getBook(String author);
}
