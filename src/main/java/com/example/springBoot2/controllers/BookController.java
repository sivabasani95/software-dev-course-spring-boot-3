package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Book;
import com.example.springBoot2.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    public BookController (BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // creating the CRUD oparations
  // 1 st one is CREATE == @ postMapping

    // CREATE
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // READ All oparation we are using @GetMapping
    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // Read one book by Id
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id){
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // UPDATE oparation for that we are using [@PutMapping]

    // UPDATE
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setName(updatedBook.getName());
        book.setAuthor(updatedBook.getAuthor());
        book.setPages(updatedBook.getPages());
        book.setYear(updatedBook.getYear());

        return bookRepository.save(book);
    }

    // DELETE oparation for that we are using [@DeleteMapping]
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully";

    }



}