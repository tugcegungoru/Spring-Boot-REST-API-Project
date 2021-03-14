package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.management.modelmbean.ModelMBeanInfoSupport;

import com.example.demo.domain.BookDO;
import com.example.demo.domain.CommentDO;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.repository.BookRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDO createBook(BookDO book) {
        return bookRepository.save(book);
    }

    // @Override
    // public BookDTO updateBook(BookDO book) {
    //     long bookId = book.getId();
    //     Optional<BookDO> currentBook = bookRepository.findById(bookId);
    //     if (currentBook.isPresent()) {
    //         currentBook.get().setName(book.getName());
    //         currentBook.get().setAuthor(book.getAuthor());
    //         currentBook.get().setBarcode(book.getBarcode());
    //         currentBook.get().setPageNumber(book.getPageNumber());
    //         currentBook.get().setPublisher(book.getPublisher());
    //         currentBook.get().setSummary(book.getSummary());
            
    //         CommentDO bookComment = currentBook.get().getComment();
    //         if(bookComment == null){
    //             bookComment = new CommentDO();
    //         }
    //         bookComment.setTitle(book.getComment().getTitle());
    //         bookComment.setComment(book.getComment().getComment());
            
    //         BookDO savedBook = bookRepository.save(currentBook.get());
    //         bookComment.setId((savedBook.getComment().getId()));
    //         currentBook.get().setComment(bookComment);


    //         //BookDTO  bookDTO = new modelMapper.map(currentBook, BookDTO.class);
    //         //CommentDTO commentDTO = new modelMapper.map(bookComment, CommentDTO.class);
    //         //bookDTO.setCommentDTO(commentDDTO);
    //         return savedBook;

    //     }
    //     return null;
    // }

    @Override
    public BookDTO updateBook(BookDO book) {
        long bookId = book.getId();
        Optional<BookDO> currentBook = bookRepository.findById(bookId);
        if (currentBook.isPresent()) {
            currentBook.get().setName(book.getName());
            currentBook.get().setAuthor(book.getAuthor());
            currentBook.get().setBarcode(book.getBarcode());
            currentBook.get().setPageNumber(book.getPageNumber());
            currentBook.get().setPublisher(book.getPublisher());
            currentBook.get().setSummary(book.getSummary());
            
            CommentDO bookComment = currentBook.get().getComment();
            if(bookComment == null){
                bookComment = new CommentDO();
            }
            bookComment.setTitle(book.getComment().getTitle());
            bookComment.setComment(book.getComment().getComment());

            currentBook.get().setComment(bookComment);

            bookRepository.save(currentBook.get());
            BookDTO  bookDTO = new ModelMapper().map(currentBook, BookDTO.class);
            CommentDTO commentDTO = new ModelMapper().map(bookComment, CommentDTO.class);
            bookDTO.setCommentDTO(commentDTO);
            return bookDTO;

        }
        return null;
    }

    @Override
    public void deleteBook(Long bookId) {
        // TODO Auto-generated method stub
        Optional<BookDO> currentBook = bookRepository.findById(bookId);
        if (currentBook.isPresent()) {
            bookRepository.deleteById(bookId);
        }
    }

    @Override
    public BookDO getBook(Long bookId) {
        // TODO Auto-generated method stub
        Optional<BookDO> currentBook = bookRepository.findById(bookId);
        if (currentBook.isPresent()) {
            return currentBook.get();
        }
        return null;
    }

    @Override
    public List<BookDO> getAllBooks() {
        // TODO Auto-generated method stub
        return bookRepository.findAll();
    }

    @Override
    public BookDO getBook(String author) {
        // TODO Auto-generated method stub
        Optional<BookDO> currentBook = bookRepository.findByAuthor(author);
        if (currentBook.isPresent()) {
            return currentBook.get();
        }
        return null;
    }
    
}
