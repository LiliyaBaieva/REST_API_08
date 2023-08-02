package de.ait.timepad.services;

import de.ait.timepad.dto.NewUserDto;
import de.ait.timepad.dto.UpdatedUserDto;
import de.ait.timepad.dto.UserDto;
import de.ait.timepad.dto.UsersDto;

public interface UserService {
    UserDto addUser(NewUserDto user);

    UsersDto getAllUsers();

    UserDto deleteUser(Long userId);

    UserDto updateUser(Long userId, UpdatedUserDto updatedUser);

    UserDto getUser(Long userId);
}
