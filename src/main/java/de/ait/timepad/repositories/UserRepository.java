package de.ait.timepad.repositories;

import de.ait.timepad.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Optional<User> findByID(Long id);

    //TODO убрать метод как только подключим базу данных
    void clear();

    void delete(User user);
}

