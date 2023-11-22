package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;

import java.util.UUID;

public class LibraryCardTransformer {
    public static LibraryCard prepareLibraryCard(){
        LibraryCard libraryCard =LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .build();
        return libraryCard;
    }
}
