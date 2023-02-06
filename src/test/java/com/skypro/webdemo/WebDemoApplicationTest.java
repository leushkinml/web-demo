package com.skypro.webdemo;

import com.skypro.webdemo.controllers.BooksController;
import com.skypro.webdemo.model.Book;
import com.skypro.webdemo.repositories.BookCoverRepository;
import com.skypro.webdemo.repositories.BookRepository;
import com.skypro.webdemo.services.BookCoverService;
import com.skypro.webdemo.services.BookService;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class WebDemoApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookCoverRepository bookCoverRepository;

    @SpyBean
    private BookService bookService;

    @SpyBean
    private BookCoverService bookCoverService;

    @InjectMocks
    private BooksController booksController;

    @Test
    public void saveBookTest() throws Exception{
        final String name = "123123";
        final String author = "Weweedws";
        final Long id = 1L;


        JSONObject bookObject = new JSONObject();
        bookObject.put("name", name);
        bookObject.put("author", author);

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);

        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
        // when(bookRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/books")
                .content(bookObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));
    }
}