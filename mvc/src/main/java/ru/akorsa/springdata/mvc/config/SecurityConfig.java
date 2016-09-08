package ru.akorsa.springdata.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.akorsa.springdata.jpa.enums.DataConfigProfile;
import ru.akorsa.springdata.mvc.security.CurrentUserDetailsService;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = CurrentUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] IGNORED_RESOURCE_LIST = new String[]{"/resources/**",
            "/static/**", "/webjars/**"};
    private static final String[] PERMITALL_RESOURCE_LIST = new String[]{"/", "/contacts/**", "/list.html", "/register/**"};
    private static final String[] ADMIN_RESOURCE_LIST = new String[]{"/console/**"};

    @Autowired
    private UserDetailsService userDetailsService;

    @Order(1)
    @Configuration
    @Profile(DataConfigProfile.H2)
    static class H2WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .headers()
                    .cacheControl()
                    .contentTypeOptions()
                    .httpStrictTransportSecurity()
                    .and()
                    .requestMatchers()
                    .antMatchers(ADMIN_RESOURCE_LIST)
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .and()
                    .authorizeRequests()
                    .anyRequest().hasAuthority("ROLE_ADMIN")
                    .and().exceptionHandling().accessDeniedPage("/403");
        }
    }

    @Profile(DataConfigProfile.H2)
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    public void configure(WebSecurity web) throws Exception {
        web.
                ignoring()
                .antMatchers(IGNORED_RESOURCE_LIST);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PERMITALL_RESOURCE_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("remember-me")
                .permitAll()
                .and()
                .rememberMe()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }
}
