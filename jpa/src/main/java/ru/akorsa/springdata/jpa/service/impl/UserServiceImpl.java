package ru.akorsa.springdata.jpa.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akorsa.springdata.jpa.dto.UserDTO;
import ru.akorsa.springdata.jpa.model.Authority;
import ru.akorsa.springdata.jpa.model.User;
import ru.akorsa.springdata.jpa.repository.AuthorityRepository;
import ru.akorsa.springdata.jpa.repository.UserRepository;
import ru.akorsa.springdata.jpa.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public Optional<User> getUserById(long id) {
        logger.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findById(id));
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public User getUserByUsername(String username) {
        logger.debug("Getting user={}", username);
        return userRepository.findByUsername(username);
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public Optional<User> getByEmail(String email) {
        logger.debug("Getting user by email={}", email);
        return userRepository.findOneByEmail(email);
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public Collection<User> getAllUsers() {
        logger.debug("Getting all users");
        return userRepository.findAll();
    }

    @Transactional
    public User create(UserDTO form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        User saved = userRepository.save(user);

        for (Authority authority : form.getAuthorities()) {
            Authority _authority = authorityRepository.findByAuthority(authority.getAuthority());
            saved.getAuthorities().add(_authority);
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(saved, saved.getPassword(), saved.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersWithDetail() {
        return userRepository.getUsersWithDetail();
    }
}
