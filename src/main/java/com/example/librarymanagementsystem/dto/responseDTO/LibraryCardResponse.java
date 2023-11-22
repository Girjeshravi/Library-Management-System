package com.example.librarymanagementsystem.dto.responseDTO;


import com.example.librarymanagementsystem.Enum.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LibraryCardResponse {
    String cardNo;
    CardStatus cardStatus;
    Date issueDate;
}
