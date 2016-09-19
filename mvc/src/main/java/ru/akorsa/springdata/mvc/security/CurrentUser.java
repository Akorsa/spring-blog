package ru.akorsa.springdata.mvc.security;

import ru.akorsa.springdata.jpa.model.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(ru.akorsa.springdata.jpa.model.User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getFullName() {
        return user.getFirstName() + ' ' + user.getLastName();
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                '}' + super.toString();
    }
}
