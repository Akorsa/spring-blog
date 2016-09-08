package ru.akorsa.springdata.jpa.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import ru.akorsa.springdata.jpa.model.Authority;
import ru.akorsa.springdata.jpa.model.User;
import ru.akorsa.springdata.jpa.model.validators.ExtendedEmailValidator;

import javax.persistence.Basic;
import java.util.Collection;

public class UserDTO {

    @Length(min= User.MIN_LENGTH_USERNAME, max=User.MAX_LENGTH_USERNAME)
    private String username = "";

    @Basic
    @ExtendedEmailValidator
    @Length(max = User.MAX_LENGTH_EMAIL_ADDRESS)
    private String email = "";

    @Length(min = User.MIN_LENGTH_PASSWORD, max=User.MAX_LENGTH_PASSWORD)
    private String password = "";

    @NotEmpty
    @Length(min = User.MIN_LENGTH_FIRST_NAME, max=User.MAX_LENGTH_FIRST_NAME)
    private String firstname = "";

    @NotEmpty
    @Length(min = User.MIN_LENGTH_LAST_NAME, max=User.MAX_LENGTH_LAST_NAME)
    private String lastname = "";

    private String repeatedPassword = "";

    private Collection<Authority> authorities;

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "UserCreateForm{" +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                "lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
