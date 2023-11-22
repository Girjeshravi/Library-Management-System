package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    StudentRepository studentRepository;

    //inbuilt bin for mail
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepo transactionRepo;
    public IssueBookResponse issueBook(int bookId,int studentId){
        Optional<Student>studentOptional=studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("invalid student id !!");
        }
        Optional<Book>optionalBook=bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new BookNotAvailableException("invalid book id");
        }
        Book book =optionalBook.get();
        if(book.isIssued()){
            throw new BookNotAvailableException("book already issued");
        }
        // issue the book
        Student student =studentOptional.get();

         // create transaction
        Transaction transaction= Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        Transaction savedTransaction=transactionRepo.save(transaction);

        //update book
        book.setIssued(true);
//        book.getTransactions().add(transaction); --> bcs of primary key generated use below code
        book.getTransactions().add(savedTransaction);

        // card changes
        student.getLibraryCard().getTransaction().add(transaction);
        Book savedBook =bookRepository.save(book); // book and transaction
        Student savedStudent=studentRepository.save(student); // student and transactio//

        //send and email
        String text="Hi "+student.getName()+" The below book has been issued to you\n"+
                book.getTitle()+" \nThis is the transaction number: "+savedTransaction.getTransactionNumber();

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("gwc.ravi52@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Congrats !! book issued");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        //prepare response
        return IssueBookResponse.builder()
                .bookName(savedBook.getTitle())
                .transactionStatus(savedTransaction.getTransactionStatus())
                .transactionTime(savedTransaction.getTransactionTime())
                .transactionNumber(savedTransaction.getTransactionNumber())
                .studentName(savedStudent.getName())
                .libraryCardNumber(savedStudent.getLibraryCard().getCardNo())
                .libraryCardNumber(savedStudent.getLibraryCard().getCardNo())
                .authorName(savedBook.getAuthor().getName())
                .build();
    }
}
