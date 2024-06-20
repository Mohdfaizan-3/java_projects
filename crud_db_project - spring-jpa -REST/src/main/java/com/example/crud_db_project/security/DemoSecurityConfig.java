package com.example.crud_db_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/*
@Bean is used on a method to indicate that it returns an object that
should be registered as a bean in the Spring application context.
*/
@Configuration
public class DemoSecurityConfig {

    //add support for jdbc..
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //define query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );
        //define query to retrieve the authority/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );
        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/magic-api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/magic-api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/magic-api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/magic-api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/magic-api/employees/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        // disabling csrf. in general not require for stateless rest apis that uses POST, PUT, DELETE PATCH.
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
  /*  @Bean
    public InMemoryUserDetailsManager InMemoryUserDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}Test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}Test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}Test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
*/

}
