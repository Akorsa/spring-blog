package ru.akorsa.springdata.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.akorsa.springdata.jpa.model.CurrentUser;
import ru.akorsa.springdata.jpa.model.User;
import ru.akorsa.springdata.jpa.repository.UserRepository;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CurrentUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }
        return new CurrentUser(user);
    }
}
