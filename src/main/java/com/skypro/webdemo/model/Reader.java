package com.skypro.webdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Reader {
    @Id
    @GeneratedValue
    private Long id;

    private int personalNumber;
    private String firstName;
    private String secondName;
    private String middleName;

    @OneToMany(mappedBy = "reader")
    private Collection<Book> books;


    public Reader(int personalNumber, String firstName, String secondName, String middleName, Collection<Book> books) {
        this.personalNumber = personalNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", personalNumber=" + personalNumber +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader reader)) return false;
        return personalNumber == reader.personalNumber && Objects.equals(id, reader.id) && Objects.equals(firstName, reader.firstName) && Objects.equals(secondName, reader.secondName) && Objects.equals(middleName, reader.middleName) && Objects.equals(books, reader.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalNumber, firstName, secondName, middleName, books);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
