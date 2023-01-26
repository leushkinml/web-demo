package com.skypro.webdemo.services;


import com.skypro.webdemo.model.Book;
import com.skypro.webdemo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // private final HashMap<Long, Book> books = new HashMap<>();
    // private long lastId = 0;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
//    public Book createBook(Book book) {
//        book.setId(++lastId);
//        books.put(lastId, book);
//        return book;
//    }

    public Book findBook(Long lastId) {
        return bookRepository.findById(lastId).get();
    }
//    public Book findBook(long lastId) {
//        return books.get(lastId);
//    }

    public Book editBook(Book book) {
        return bookRepository.save(book);
    }


//    public Book editBook(Book book) {
//        if (books.containsKey(book.getId())) {
//            books.put(book.getId(), book);
//            return book;
//        }
//        return null;
//    }

    public void deleteBook(Long lastId) {
        bookRepository.deleteById(lastId);
    }

    public void deleteBookSecondMethod(Book book) {
        bookRepository.delete(book);
    }

//    public Book deleteBook(long lastId) {
//        return books.remove(lastId);
//    }

    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }
//    public Collection<Book> getAllBooks() {
//        return books.values();
//    }

//    public  List<Book> findBookByName(String name) {
//        return bookRepository.findBookByName(name);
//    }
//
//    public List<Book> findBookByNameAndAuthorAndId(String name, String author, long id) {
//        return bookRepository.findBookByNameAndAuthorAndId(name, author, id);
//    }
//
//    public  List<Book> findBookByNameLike(String name) {
//        return bookRepository.findBookByNameLike(name);
//    }



    public Book findByName(String name) {
        return bookRepository.findByNameIgnoreCase(name);
    }
    public Book findByAuthor (String author) {
        return bookRepository.findByAuthorContainsIgnoreCase(author);
    }
    public Collection<Book> findAllByNameContains(String part) {
        return bookRepository.findAllByNameContainsIgnoreCase(part);
    }
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }
}
