package de.ait.timepad.validation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorDto {

    private String field; // с каким полем возникла ошибка
    private String message; // сообщение об ошибке
    private String rejectedValue; // какое значение от клинета было плохим

}
