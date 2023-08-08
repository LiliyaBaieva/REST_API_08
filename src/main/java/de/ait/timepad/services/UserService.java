package de.ait.timepad.services;

import de.ait.timepad.dto.*;

public interface UserService {
    UserDto addUser(NewUserDto user);

    UsersDto getAllUsers();

    UserDto deleteUser(Long userId);

    UserDto updateUser(Long userId, UpdatedUserDto updatedUser);

    UserDto getUser(Long userId);

    ArticlesDto getArticlesOfUser(Long userId);
}
