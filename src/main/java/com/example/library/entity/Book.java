package com.example.library.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String ISBN;
    private String author;
    private String issuer;
    private Integer dateOfIssue;
    private Boolean IsRented;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rent> rent = new ArrayList<>();

    public Book(){}

    public Book(Integer id, String title, String ISBN, String author, String issuer, Integer dateOfIssue, Boolean isRented) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.issuer = issuer;
        this.dateOfIssue = dateOfIssue;
        IsRented = isRented;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Integer getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Integer dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Boolean getRented() {
        return IsRented;
    }

    public void setRented(Boolean rented) {
        IsRented = rented;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", issuer='" + issuer + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", IsRented=" + IsRented +
                '}';
    }
}
