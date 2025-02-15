package com.demo.SpringSecurity3.config.security.managers;

import com.demo.SpringSecurity3.config.security.providers.CustomAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public CustomAuthenticationManager(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(customAuthenticationProvider.supports(authentication.getClass())) {
            return customAuthenticationProvider.authenticate(authentication);
        }

        throw new BadCredentialsException("Oh No!");
    }

}
