package de.ait.timepad.controllers;

import de.ait.timepad.controllers.api.UsersApi;
import de.ait.timepad.dto.*;
import de.ait.timepad.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class UserController implements UsersApi {

    //объявляем функционал (где лежит)
    private final UserService userService;

//    public UserDto addUsers(NewUserDto newUser){
//        return userService.addUser(newUser);
//    }

    public ResponseEntity<UserDto> addUsers(NewUserDto newUser){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.addUser(newUser));
    }

    public UsersDto getAllUsers(){
        return userService.getAllUsers();
    }

    @Override
    public UserDto deleteUser(Long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public UserDto updateUser(Long userId, UpdatedUserDto updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @Override
    public UserDto getUser(Long userId) {
        return userService.getUser(userId);
    }

    @Override
    public ArticlesDto getArticlesOfUser(Long userId) {
        return userService.getArticlesOfUser(userId);
    }

}





