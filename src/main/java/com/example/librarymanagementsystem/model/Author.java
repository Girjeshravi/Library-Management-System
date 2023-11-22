package com.example.librarymanagementsystem.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.*;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true,nullable = false)
    String emailId;

    @UpdateTimestamp// wheneveer we perporm any operation on the database time will automatically update
    Date lastActivity;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    // int one to many or many to one we have to set like we required more then one entity datas
    List<Book> book=new ArrayList<>();
}
