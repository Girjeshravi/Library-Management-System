package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// we used here extends because jpa and studentrepository bothh are interface and relation between interface is extends
// we are extnding property not implementing it, if we will not creat interface we have to do that

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByGender(Gender gender);

    Student findByEmail(String email);

    Student findByGenderAndEmail(Gender gender,String email);
}
