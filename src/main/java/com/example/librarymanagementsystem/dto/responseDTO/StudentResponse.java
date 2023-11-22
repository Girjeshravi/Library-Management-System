package com.example.librarymanagementsystem.dto.responseDTO;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.interceptor.CacheEvictOperation;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponse {
    String name;
    String email;

    LibraryCardResponse libraryCardResponse;
}
