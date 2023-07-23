package de.ait.timepad.services.impl;

import de.ait.timepad.dto.NewUserDto;
import de.ait.timepad.dto.UserDto;
import de.ait.timepad.dto.UsersDto;
import de.ait.timepad.models.User;
import de.ait.timepad.repositories.UserRepository;
import de.ait.timepad.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.timepad.dto.UserDto.from;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserDto addUser(NewUserDto newUser) {
        User user = User.builder()
                        .email(newUser.getEmail())
                        .password(newUser.getPassword())
                        .role(User.Role.USER)
                        .state(User.State.NOT_CONFIRMED)
                        .build();

        userRepository.save(user);

        return from(user);
    }

    @Override
    public UsersDto getAllUsers() {
//        List<User> users = userRepository.findAll();
//        List<UserDto> userDtoList = new ArrayList<>();
//
//        for(User user : users){
//            UserDto userDto = from(user);
//            userDtoList.add(userDto);
//        }

//        List<UserDto> userDtoList = userRepository.findAll().stream()
//                .map(UserDto::from)//user -> from(user)
//                .collect(Collectors.toList());
//
//        return UsersDto.builder()
//                .users(userDtoList)
//                .build();

        List<User> users = userRepository.findAll();

        return UsersDto.builder()
                .users(from(users))//найди обычных юзеров и с помощью метода from переведи в usersDto
                .count(users.size())
                .build();
    }
}
