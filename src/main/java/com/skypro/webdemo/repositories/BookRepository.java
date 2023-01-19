package com.skypro.webdemo.repositories;


import com.skypro.webdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByName(String name);   // А если мы захотим искать все позиции по любому другому полю, то достаточно в названии метода findByName заменить Name на название другого поля.

    List<Book> findBookByNameAndAuthorAndId(String name, String author, Long id); // вы можете искать по нескольким полям, используя логические операторы: AND или OR.

    List<Book> findBookByNameLike(String name); // А если мы захотим найти позицию, у которой в поле name присутствует слово Developer, то нам следует использовать методы со словом Like в конце



    Book findByNameIgnoreCase(String name);
    Book findByAuthorContainsIgnoreCase(String author);
    Collection<Book> findAllByNameContainsIgnoreCase(String part);
    Book findBookById(Long id);

    Collection<Book> findBookByNameAndAuthor(String name, String author);
}
