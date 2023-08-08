package de.ait.timepad.repositories;

import de.ait.timepad.models.User;

public interface UserRepository extends CrudRepository<User>{

    //TODO убрать метод как только подключим базу данных
    void clear();

}

