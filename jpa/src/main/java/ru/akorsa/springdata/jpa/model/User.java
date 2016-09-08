package ru.akorsa.springdata.jpa.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import ru.akorsa.springdata.jpa.model.validators.ExtendedEmailValidator;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 2002390446280945447L;
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public static final int MAX_LENGTH_EMAIL_ADDRESS = 100;
    public static final int MAX_LENGTH_FIRST_NAME = 25;
    public static final int MAX_LENGTH_LAST_NAME = 50;
    public static final int MAX_LENGTH_USERNAME = 15;
    public static final int MAX_LENGTH_PASSWORD = 20;

    public static final int MIN_LENGTH_USERNAME = 3;
    public static final int MIN_LENGTH_PASSWORD = 6;
    public static final int MIN_LENGTH_FIRST_NAME = 2;
    public static final int MIN_LENGTH_LAST_NAME = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    @NotEmpty
    @Length(min = MIN_LENGTH_USERNAME, max = MAX_LENGTH_USERNAME)
    private String username;

    @Column
    @NotEmpty
    @Length(min = MIN_LENGTH_PASSWORD)
    private String password;

    @Basic
    @ExtendedEmailValidator
    @NotEmpty
    @Length(max = MAX_LENGTH_EMAIL_ADDRESS)
    @Column(unique = true, nullable = false)
    private String email;

    @Column
    @NotEmpty
    @Length(max = MAX_LENGTH_FIRST_NAME)
    private String firstname;

    @Column
    @NotEmpty
    @Length(max = MAX_LENGTH_LAST_NAME)
    private String lastname;

    @Column(name = "account_expired")
    private boolean accountExpired = false;

    @Column(name = "account_locked")
    private boolean accountLocked = false;

    @Column(name = "credentials_expired")
    private boolean credentialsExpired = false;

    @Column
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Collection<Authority> authorities;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    public User() {
        this.authorities = new LinkedHashSet<>();
    }

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public boolean hasAuthority(String targetAuthority) {
        if (targetAuthority == null) {
            return false;
        }
        if (authorities == null) {
            logger.info("authorities is null for user " + this);
        }

        for (Authority authority : authorities) {
            if (targetAuthority.equals(authority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", accountExpired=" + accountExpired +
                ", accountLocked=" + accountLocked +
                ", credentialsExpired=" + credentialsExpired +
                ", enabled=" + enabled +
                '}';
    }
}
