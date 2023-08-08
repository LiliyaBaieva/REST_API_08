package de.ait.timepad.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {

    private Long id;
    private String text;
    private User about;
    private LocalDate publishDate;

}
