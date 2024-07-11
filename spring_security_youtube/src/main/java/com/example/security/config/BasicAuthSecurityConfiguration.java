package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// this class is created to disable csrf for rest
@Configuration
public class BasicAuthSecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> {
            requests.anyRequest().authenticated();
        });

        // disabling session ie spring security will not create and not use session
        http.sessionManagement((session)-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // http.formLogin(); won't use formLogin

        http.httpBasic();

        // disabling csrf ie even without csrf token we can use POST.. req
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    // this is for storing multiple users in memory
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                // .password(passwordEncoder().encode("1234"))
                .password("1234")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("1234")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
