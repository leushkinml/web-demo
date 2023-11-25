package com.skypro.webdemo.controllers;

import com.fasterxml.jackson.core.format.InputAccessor;
import com.skypro.webdemo.model.Book;
import com.skypro.webdemo.model.BookCover;
import com.skypro.webdemo.services.BookCoverService;
import com.skypro.webdemo.services.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("books")
public class BooksController {

    private final BookService bookService;
    private final BookCoverService bookCoverService;

    public BooksController(BookService bookService, BookCoverService bookCoverService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
    }

    @PostMapping   // POST http://localhost:8080/books
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PostMapping(value = "/{id}/cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCover(@PathVariable Long id, @RequestParam MultipartFile cover) throws IOException {
        if (cover.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        bookCoverService.uploadCover(id, cover);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}") // GET http://localhost:8080/books/23
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookService.findBook(id));
        // return bookService.findBook(id);
    }
//    @GetMapping("{id}") // GET http://localhost:8080/books/23
//    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
//        Book book = bookService.findBook(id);
//        if (book == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(book);
//        // return bookService.findBook(id);
//    }
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
        if (id != null && id > 0 ) {
            return ResponseEntity.ok(bookService.findBookById(id));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }

//    @GetMapping   // GET http://localhost:8080/books
//    public ResponseEntity<Collection<Book>> getAllBooks() {
//        return ResponseEntity.ok(bookService.getAllBooks());
//    }

    @GetMapping(value = "/{id}/cover/preview")
    public ResponseEntity<byte[]> downloadCover(@PathVariable Long id) {
        BookCover bookCover = bookCoverService.findBookCover(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(bookCover.getMediaType()));
        headers.setContentLength(bookCover.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookCover.getPreview());
    }

    @GetMapping(value = "/{id}/cover")
    public void downloadCover(@PathVariable Long id, HttpServletResponse response) throws IOException {
        BookCover bookCover = bookCoverService.findBookCover(id);

        Path path = Path.of(bookCover.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()){
            response.setStatus(200);
            response.setContentType(bookCover.getMediaType());
            response.setContentLength(Math.toIntExact(bookCover.getFileSize()));
            is.transferTo(os);
        }
    }

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

    @DeleteMapping()  // DELETE http://localhost:8080/books/
    public ResponseEntity<Book> deleteBookSecondMethod(@RequestBody Book book) {
        bookService.deleteBookSecondMethod(book);
        return ResponseEntity.ok().build();
    }
}

