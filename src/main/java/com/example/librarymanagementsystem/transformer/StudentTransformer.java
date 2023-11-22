package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.Student;
import lombok.experimental.UtilityClass;

@UtilityClass /// generalyy we donot use but its an anotation which is used to tell that this class has all variables and methods are static and cannot be instatntiated
public class StudentTransformer {
    public static Student StudentRequestToStudent(StudentRequest studentRequest) {
        Student student = Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
        return student;
    }
    public static StudentResponse StudentToStudentResponse(Student student){
        LibraryCardResponse cardResponse= LibraryCardResponse.builder()
                .cardNo(student.getLibraryCard().getCardNo())
                .issueDate(student.getLibraryCard().getIssueDate())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .build();

        return StudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .libraryCardResponse(cardResponse)
                .build();
        //.message nhi liya gaya kyuki hame har api me congrats show nhi krna hain
    }
}
