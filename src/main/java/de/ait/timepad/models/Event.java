package de.ait.timepad.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    private Long id;
    private String title;
    private String date;

}
