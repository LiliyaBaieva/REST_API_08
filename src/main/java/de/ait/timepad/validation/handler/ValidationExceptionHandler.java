package de.ait.timepad.validation.handler;


import de.ait.timepad.validation.dto.ValidationErrorDto;
import de.ait.timepad.validation.dto.ValidationErrorsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleException(MethodArgumentNotValidException e){
        List<ValidationErrorDto> validationErrors = new ArrayList<>(); //

        BindingResult bindingResult = e.getBindingResult(); // Получаем результат валидации
        List<ObjectError> errors = bindingResult.getAllErrors(); //получаем все ошибки валидации
        // Распечатаем все отдельно
        for(ObjectError error : errors){
            FieldError fieldError = (FieldError)error; // усли ошибка связанна с кокретным полем

            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .field(fieldError.getField())
                    .message(error.getDefaultMessage())
                    .rejectedValue(fieldError.getRejectedValue().toString())
                    .build();

            validationErrors.add(errorDto); //кладём ошибки в общий список

//            System.out.println(fieldError.getField() + " " + error.getDefaultMessage() + " the bad value is - " + fieldError.getRejectedValue());
        }

        // возвращаем список всех ошибок в виде JSON
        // подготовилил тело ответа
        ValidationErrorsDto body = ValidationErrorsDto.builder()
                                                    .errors(validationErrors)
                                                    .build();
        // создали ответ
        ResponseEntity<ValidationErrorsDto> response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        return response; // вернулил ответ
    }

}
