package de.ait.timepad.repositories.impl;

import de.ait.timepad.models.User;
import de.ait.timepad.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        if(user.getId() == null){
            user.setId((long)users.size() + 1); //id - порядковый номер
            users.add(user);
        } else {
            //TODO:
            // обновляем, но тут этого делать не нужно, потому, что это список объектов
            // если бы это была база данных или файл, нужно было бы обновить данные в хранилище
        }
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public Optional<User> findByID(Long id) {
        for(User user : users){
            if(user.getId().equals(id)){
                return Optional.of(user); // всместо return user
            }
        }

        return Optional.empty(); // вместо return null
    }

    @Override
    public void clear() {
        users.clear();
    }

    @Override
    public void delete(User user){
        users.remove(user);
    }
}
