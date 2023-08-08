package de.ait.timepad.repositories;

import de.ait.timepad.models.User;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    void save(T entity);

    List<T> findAll();

    Optional<T> findByID(Long id);

    void delete(T entity);

}
