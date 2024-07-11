package com.example.security.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateCsrfToken {
    @GetMapping("/csrf-token")
    public CsrfToken generateCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}

// csrf is cross-site-request-forgery means a malicious site can access the cokiee and take the auth token from basic auth
// To prevent csrf we can use -
// 1 .synchronzer token pattern in which for every put post.. request a csrf token is needed.
// csrf token generation is not necessary when using thymeleaf or in web
// for stateless we can disable csrf

 // 2. same-site-cookie - in app.properties write server.servlet.session.cookie.same-site=strict
