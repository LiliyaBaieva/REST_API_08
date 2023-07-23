package de.ait.timepad.repositories;

import de.ait.timepad.models.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    void clear();
}

