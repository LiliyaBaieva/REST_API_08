package de.ait.timepad.services.impl;

import de.ait.timepad.dto.*;
import de.ait.timepad.exceptions.ForbiddenOperationException;
import de.ait.timepad.exceptions.NotFoundException;
import de.ait.timepad.models.Article;
import de.ait.timepad.models.User;
import de.ait.timepad.repositories.UserRepository;
import de.ait.timepad.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static de.ait.timepad.dto.UserDto.from;
import static de.ait.timepad.dto.ArticleDto.from;

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
                        .articles(new ArrayList<>())
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

    @Override
    public UserDto deleteUser(Long userId) {
//        Optional<User> user = userRepository.findByID(userId);
//
//        if(user.isEmpty()) {
//            throw new NotFoundException(
//                    "User with id <" + userId + "> not found"
//            );
//        }

        User user = getUserOrThrow(userId);

        userRepository.delete(user);

        return from(user);
    }

    private User getUserOrThrow(Long userId) {
        User user = userRepository.findByID(userId).orElseThrow(
                () -> new NotFoundException("User with id <" + userId + "> not found")
        );
        return user;
    }

    @Override
    public UserDto updateUser(Long userId, UpdatedUserDto updatedUser) {
        User user = getUserOrThrow(userId); //нашли пользователя

        if(updatedUser.getNewRole().equals("ADMIN")){
            throw new ForbiddenOperationException("Not allowed to make an administrator");
        }

        user.setState(User.State.valueOf(updatedUser.getNewState()));
        user.setRole(User.Role.valueOf(updatedUser.getNewRole()));

        userRepository.save(user);

        return from(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

    @Override
    public ArticlesDto getArticlesOfUser(Long userId) {
        User user = getUserOrThrow(userId);

        return ArticlesDto.builder()
                .articles(from(user.getArticles()))
                .count(user.getArticles().size())
                .build();
    }
}
