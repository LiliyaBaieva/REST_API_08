package de.ait.timepad.controllers;

import de.ait.timepad.dto.NewUserDto;
import de.ait.timepad.dto.UserDto;
import de.ait.timepad.dto.UsersDto;
import de.ait.timepad.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    //объявляем функционал (где лежит)
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody - назначили в классе @RestController, чтоб не писат для каждого метода
// показывает, что нужно вернуть данные в теле ответе в формате JSON
    public UserDto addUsers(@RequestBody NewUserDto newUser){ //нужно вытащить данные из тела в формате JSON
        return userService.addUser(newUser);
    }


    @GetMapping
    public UsersDto getAllUsers(){
        return userService.getAllUsers();
    }

}





