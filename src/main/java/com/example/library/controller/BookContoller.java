package com.example.library.controller;


import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookContoller {

    @Autowired
    private BookService service;

    //GET method display all books
    @GetMapping("/books")
    public List<Book> findAllBooks(){
        return service.getAllBooks();
    }

    //GET method display one book by ID
    @GetMapping("/bookById/{id}")
    public Book findBookById(@PathVariable int id){
        return service.getBookById(id);
    }

    //GET method display one book by title
    @GetMapping("/book/{title}")
    public Book findBookByTitle(@PathVariable String title){
        return service.getBookByTitle(title);
    }

    //POST method for adding one book
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book){
        return service.saveBook(book);
    }

    //POST method for all books
    @PostMapping("/addAllBooks")
    public List<Book> addAllBooks(@RequestBody List<Book> books){
        return service.saveAllBooks(books);
    }

    //PUT method for update book
    @PutMapping("/updateBook")
    public Book updateBook(@RequestBody Book book){
        return service.updateBook(book);
    }


    //DELETE method for deleting book
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }


}
