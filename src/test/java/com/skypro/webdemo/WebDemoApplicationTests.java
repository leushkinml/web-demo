package com.skypro.webdemo;

import com.skypro.webdemo.controllers.BooksController;
import com.skypro.webdemo.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebDemoApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private BooksController booksController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions
                .assertThat(booksController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .isEqualTo("WebApp is working!");
                // .contains("WebApp is working!")
    }

    @Test
    public void testGetBooks() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/books", String.class))
                .isNotNull();
    }

    @Test
    public void testPostBooks() throws Exception {
        Book book = new Book();
        book.setName("Abcdif");
        book.setAuthor("Fghij");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/books", book, String.class))
                .isNotNull();
    }


}
