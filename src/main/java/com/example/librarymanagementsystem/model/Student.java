package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

// all this below 5 annotations are lombok annotations
@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="student_info ")
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it will give differents id in sequential order
    int regNo;

    @Column(name="student_name")
    String name;

    int age;

    @Column(unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "student",cascade=CascadeType.ALL)// cascade type.all means what we saved to parent table it will automatically saved to child class(child table) too//
    // used to connect parent class to child class here parent class class is student
    // mapped by student means its a name of key can be used as a name to name to foreign key from librarycard class... variable name written inside the library card class
    // cascade tells all the CRUD operations u applied to child class too

    LibraryCard libraryCard;
}
