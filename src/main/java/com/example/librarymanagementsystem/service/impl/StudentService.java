package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.transformer.LibraryCardTransformer;
import com.example.librarymanagementsystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {
        // convert request dto --> model
//        Student student =new Student(); // instead of this we will use builder concept

//        student.setName(studentRequest.getName());
//        student.setAge(studentRequest.getAge());
//        student.setGender(studentRequest.getGender());
//        student.setEmail(studentRequest.getEmail());

        // replacement of above 5 line code
        //-->using builder
        // create object using builder
//        Student student =Student.builder()
//                .name(studentRequest.getName())
//                .age(studentRequest.getAge())
//                .email(studentRequest.getEmail())
//                .gender(studentRequest.getGender())
//                .build();
        // above code is shifted to function;
        // we can call simply from another package without creating object by creating satic that function that function s
        // Student student = StudentRequestToStudent(studentRequest);
        Student student=StudentTransformer.StudentRequestToStudent(studentRequest);


        //give a library card -->
//        LibraryCard libraryCard=new LibraryCard();
//        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
//        libraryCard.setCardStatus(CardStatus.ACTIVE);
//        libraryCard.setStudent(student); ---> below using builder

//        LibraryCard libraryCard =LibraryCard.builder()
//                .cardNo(String.valueOf(UUID.randomUUID()))
//                .cardStatus(CardStatus.ACTIVE)
//                .student(student)
//                .build();

        //LibraryCard card = LibraryCardTransformer.prepareCardFromStudent(student);
        LibraryCard card = LibraryCardTransformer.prepareLibraryCard();

        //student.setLibraryCard(libraryCard);// this line is still valid
        student.setLibraryCard(card);

        card.setStudent(student);

        Student savedStudent=  studentRepository.save(student); // will save the student entity to the database


        // save model to response date
//        StudentResponse studentResponse =new StudentResponse();
//        studentResponse.setName(savedStudent.getName());
//        studentResponse.setEmail(savedStudent.getEmail());
//        studentResponse.setMessage("you have been registered");
//

//        LibraryCardResponse cardResponse=new LibraryCardResponse();
//        cardResponse.setCardNo(savedStudent.getLibraryCard().getCardNo());
//        cardResponse.setIssueDate(savedStudent.getLibraryCard().getIssueDate());
//        cardResponse.setCardStatus(savedStudent.getLibraryCard().getCardStatus());
//        studentResponse.setLibraryCardResponse(cardResponse);

//         LibraryCardResponse cardResponse= LibraryCardResponse.builder()
//                .cardNo(savedStudent.getLibraryCard().getCardNo())
//                .issueDate(savedStudent.getLibraryCard().getIssueDate())
//                .cardStatus(savedStudent.getLibraryCard().getCardStatus())
//                .build();
        StudentResponse studentResponse =StudentTransformer.StudentToStudentResponse(savedStudent);
        //studentResponse.setLibraryCardResponse(cardResponse);
        return studentResponse;
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional=studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
           // return studentOptional.get();
            return StudentTransformer.StudentToStudentResponse(studentOptional.get());
        }
        return null;
    }
    public List<String>getAllMales(){
        List<String> names=new ArrayList<>();
        List<Student>students=studentRepository.findByGender(Gender.MALE);
        for(Student s:students){
            names.add(s.getName());
        }
        return names;
    }
//    public Student StudentRequestToStudent(StudentRequest studentRequest){
//        Student student =Student.builder()
//                .name(studentRequest.getName())
//                .age(studentRequest.getAge())
//                .email(studentRequest.getEmail())
//                .gender(studentRequest.getGender())
//                .build();
//        return student; ---> above line code gaya transformer package main

}
