package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//komunicira sa bazom za tablicu Book

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;


    //this method saves book to db (post method for db)
    public Book saveBook(Book book){
        return bookRepo.save(book);
    }

    //this method saves all books to db (post method for db)
    public List<Book> saveAllBooks(List<Book> books){
        return bookRepo.saveAll(books);
    }

    //fetch all books from db (get method for db)
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    //fetch one book from db (get method for db)
    public Book getBookById(int id ){
        return bookRepo.findById(id).orElse(null);
    }

    //fetch one book by name from db
    public Book getBookByTitle(String title ){
        return  bookRepo.findByTitle(title);
    }


    //delete one book from db
    public String deleteBook(int id){
        bookRepo.deleteById(id);
        return "Book is deleted!" +id;
    }

    //update book
    public Book updateBook(Book book){
        //select book by it's ID
        Book existingBook = bookRepo.findById(book.getId()).orElse(null);

        existingBook.setTitle(book.getTitle());
        existingBook.setISBN(book.getISBN());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIssuer(book.getIssuer());
        existingBook.setDateOfIssue(book.getDateOfIssue());
        existingBook.setDateOfIssue(book.getDateOfIssue());
        existingBook.setRented(book.getRented());

        return bookRepo.save(existingBook);
    }


}
