package com.demo.SpringSecurity4.config.security.filters;

import com.demo.SpringSecurity4.config.security.authentication.ApiKeyAuthentication;
import com.demo.SpringSecurity4.config.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    public ApiKeyFilter(String key) {
        this.key = key;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestKey = request.getHeader("x-api-key");

        if(requestKey == null || "null".equals(requestKey)) {
            filterChain.doFilter(request, response);
        }

        var apiKeyAuthentication = new ApiKeyAuthentication(requestKey);

        CustomAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(key);

        try {
            var a = customAuthenticationManager.authenticate(apiKeyAuthentication);

            if(a.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
