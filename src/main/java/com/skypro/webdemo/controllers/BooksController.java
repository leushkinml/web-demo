package com.skypro.webdemo.controllers;


import com.skypro.webdemo.model.Book;
import com.skypro.webdemo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping   // POST http://localhost:8080/books
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("{id}") // GET http://localhost:8080/books/23
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
        // return bookService.findBook(id);
    }

    @GetMapping   // GET http://localhost:8080/books
    public ResponseEntity getBooks(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String author,
                                   @RequestParam(required = false) String namePart,
                                   @RequestParam(required = false) Long id) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(bookService.findByName(name));
        }
        if (author != null && !author.isBlank()) {
            return ResponseEntity.ok(bookService.findByAuthor(author));
        }
        if (namePart != null && !namePart.isBlank()) {
            return ResponseEntity.ok(bookService.findAllByNameContains(namePart));
        }
        if (id > 0 ) {
            return ResponseEntity.ok(bookService.findBookById(id));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }


//    @GetMapping   // GET http://localhost:8080/books
//    public ResponseEntity<Collection<Book>> getAllBooks() {
//        return ResponseEntity.ok(bookService.getAllBooks());
//    }

    @PutMapping   // PUT http://localhost:8080/books
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            // return ResponseEntity.notFound().build();
            // return ResponseEntity.badRequest().build();
            // return ResponseEntity.status(400).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping("{id}")   // DELETE http://localhost:8080/books/23
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}

