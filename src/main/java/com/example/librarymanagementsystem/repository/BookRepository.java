package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    // its a sql query : is used to take the variable from function
    @Query(value="select * from book where genre=:genre and cost >:cost",nativeQuery = true)
    // native query = true means u r not writing a hibernate query u r just writing a normal sql query
    // here we can do big mistake is in database genre is column name which will give data in varchar and here also genre is enum so we cannot compare to avoid that we have to convert the enum to string
    List<Book> getBooksByGenreAndCostGreaterThan(String genre,double cost);// we cannot write Genre genre here in this function

    //HQL QUERY // here Book is class
    @Query(value="select b from Book b where b.genre=:genre and b.cost>:cost")
    List<Book>getBooksByGenreAndCostGreaterThanHQL(Genre genre,double cost);
}
