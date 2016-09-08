package ru.akorsa.springdata.jpa.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import ru.akorsa.springdata.jpa.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    User findByUsername(String username) throws DataAccessException;

    Collection<User> findAll() throws DataAccessException;

    User findById(Long id) throws DataAccessException;

    User save(User user) throws DataAccessException;

    User delete(User user) throws DataAccessException;

    boolean exists(Long userId) throws DataAccessException;

    @Query("select distinct u from User u left join fetch " +
    "u.authorities left join fetch u.userProfile p")
    List<User> getUsersWithDetail();

    Optional<User> findOneByEmail(String email);
}
