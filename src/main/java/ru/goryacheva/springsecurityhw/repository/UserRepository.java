package ru.goryacheva.springsecurityhw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.goryacheva.springsecurityhw.model.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
