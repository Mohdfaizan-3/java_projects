package com.lg.electronic_store.config;

import com.lg.electronic_store.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.lg.electronic_store.entity.enums.Role.ADMIN;
import static com.lg.electronic_store.entity.enums.Role.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()

                        // Admin only endpoints
                        .requestMatchers(HttpMethod.POST, "/products/create").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/products/uploadImage/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/categories/create").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, "/categories/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole(ADMIN.name())

                        // User and Admin endpoints
                        .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole(USER.name(), ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/orders/getOrder/**").hasAnyRole(USER.name(), ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole(USER.name(), ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/users/*/uploadImage").hasAnyRole(USER.name(), ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, "/users/**").hasAnyRole(USER.name(), ADMIN.name())

                        .anyRequest().authenticated()
                );
        httpSecurity.sessionManagement((session)-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}