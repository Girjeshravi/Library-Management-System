package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String adBook(@RequestBody Book book){
         try{
             String response=bookService.addBook(book);
             return response;
         }
         catch (Exception e){
             return e.getMessage();
        }
    }

    // delet a book

    // give me the names of all the books of a particular genre

    // give me the names of all the book of a particular genre and cost grEATER THEN 500 RS
    @GetMapping("/get-by-genre-cost")
    public List<BookResponse> getBooksByGenreAndCostGreaterThan(@RequestParam("genre") String genre, @RequestParam("cost") double cost){
        List<BookResponse>response=bookService.getBooksByGenreAndCostGreaterThan(genre,cost);
        return response;
    }

    // same using hibernate query language(HQL)
    @GetMapping("/get-by-genre-cost-hql")
    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(@RequestParam("genre") Genre genre, @RequestParam("cost") double cost){
        List<BookResponse>response=bookService.getBooksByGenreAndCostGreaterThanHQL(genre,cost);
        return response;
    }

    // give me all the books having number of pages between 'a' and 'b'

    // give me the names of all the authors who write a particular genre
}
