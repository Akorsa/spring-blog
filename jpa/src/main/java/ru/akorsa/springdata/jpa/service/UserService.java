package ru.akorsa.springdata.jpa.service;

import ru.akorsa.springdata.jpa.dto.UserDTO;
import ru.akorsa.springdata.jpa.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserDTO form);

    User getUserByUsername(String username);

    List<User> getUsersWithDetail();
}
