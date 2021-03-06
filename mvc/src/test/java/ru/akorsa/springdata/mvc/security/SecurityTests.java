package ru.akorsa.springdata.mvc.security;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.akorsa.springdata.jpa.model.CurrentUser;
import ru.akorsa.springdata.mvc.AbstractContext;
import ru.akorsa.springdata.mvc.controller.UserController;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.akorsa.springdata.mvc.security.SecurityRequestPostProcessors.csrf;
import static ru.akorsa.springdata.mvc.security.SecurityRequestPostProcessors.user;

@RunWith(SpringJUnit4ClassRunner.class)
public class SecurityTests extends AbstractContext {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private CurrentUserDetailsService currentUserDetailsService;

    private CurrentUser keith;
    private CurrentUser user;
    private CurrentUser admin;

    private MockMvc mvc;

    @Before
    public void setup() {

        keith =currentUserDetailsService.loadUserByUsername("keith");
        user =currentUserDetailsService.loadUserByUsername("user");
        admin =currentUserDetailsService.loadUserByUsername("admin");

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }


    @Test
    public void invalidUsernamePassword() throws Exception {
        RequestBuilder request = post("/login")
                .param("username", "user")
                .param("password", "invalid")
                .with(csrf());

        mvc
                .perform(request)
                .andExpect(invalidLogin());
    }

    @Test
    public void validUsernamePassword() throws Exception {
        RequestBuilder request = post("/login")
                .param("username", "user")
                .param("password", "password")
                .with(csrf());

        mvc
                .perform(request)
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void redirectOnContactDetails() throws Exception {
        RequestBuilder request = post("/contact/1")
                .with(csrf());

        mvc
                .perform(request)
                .andExpect(loginPage());
    }

    @Test
    public void userCannotAccessConsole() throws Exception {
        RequestBuilder request = get("/console")
                .with(user(keith));

        mvc
                .perform(request)
                .andExpect(status().isForbidden());
    }

    @Test
    public void userCannotAccessAnotherProfile() throws Exception {
        RequestBuilder request = get("/{username}", "user")
                .with(user(keith))
                .with(csrf());

        mvc
                .perform(request)
                .andExpect(status().isForbidden());
    }

    @Test
    public void userCanAccessOwnProfile() throws Exception {

        RequestBuilder request = get("/{username}", "user")
                .with(user(user))
                .with(csrf());

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name(UserController.USER_PROFILE_VIEW));
    }

    @Test
    public void adminCanAccessOwnProfile() throws Exception {

        RequestBuilder request = get("/{username}", "keith")
                .with(user(admin))
                .with(csrf());

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name(UserController.USER_PROFILE_VIEW));

    }

    @Test
    public void profileRequiresCsrf() throws Exception {
        RequestBuilder request = post("/")
                .with(user(keith));

        mvc
                .perform(request)
                .andExpect(invalidCsrf());
    }

    @Test
    public void validRegistration() throws Exception {
        RequestBuilder request = post("/register")
                .param("username", "bobby")
                .param("firstName", "Bob")
                .param("lastName", "Crachet")
                .param("email", "bob@aol.com")
                .param("password", "password")
                .param("repeatedPassword", "password")
                .with(csrf());

        mvc
                .perform(request)
                .andExpect(redirectedUrl("/contacts"));
    }

    @Test
    public void invalidRegistrationEmail() throws Exception {

        RequestBuilder request = post("/register")
                .param("username", "bobby")
                .param("firstName", "Bob")
                .param("lastName", "Crachet")
                .param("email", "user")
                .param("password", "password")
                .param("repeatedPassword", "password")
                .with(csrf());
        mvc
                .perform(request)
                .andExpect(invalidRegistration());
    }

    @Test
    public void preExistingUsernameRegistration() throws Exception {
        RequestBuilder request = post("/register")
                .param("username", "user")
                .param("firstName", "Bob")
                .param("lastName", "Crachet")
                .param("email", "bob@email.com")
                .param("password", "password")
                .param("repeatedPassword", "password")
                .with(csrf());
        mvc
                .perform(request)
                .andExpect(model().attributeHasErrors("userDTO"))
                .andExpect(invalidRegistration());
    }

    private static ResultMatcher loginPage() {
        return result -> {
            status().isFound().match(result);
            redirectedUrl("http://localhost/login").match(result);
        };
    }

    private static ResultMatcher invalidLogin() {
        return result -> {
            status().isFound().match(result);
            redirectedUrl("/login?error").match(result);
        };
    }

    private static ResultMatcher invalidRegistration() {
        return result -> {

            status().isOk().match(result);
            view().name("register").match(result);
        };
    }

    private static ResultMatcher invalidCsrf() {
        return result -> status().isForbidden().match(result);
    }

    @Test
    public void getContactForm() throws Exception {

        RequestBuilder request = get("/contact/update/1")
                .with(user(admin))
                .with(csrf());

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("contacts/contactform"))
                       .andDo(MockMvcResultHandlers.print());

    }

}
