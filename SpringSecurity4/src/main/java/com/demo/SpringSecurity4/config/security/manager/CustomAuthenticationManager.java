package com.demo.SpringSecurity4.config.security.manager;

import com.demo.SpringSecurity4.config.security.provider.ApiKeyAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

public class CustomAuthenticationManager implements AuthenticationManager {

    private final String key;

    public CustomAuthenticationManager(String key) {
        this.key = key;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthenticationProvider apiKeyAuthenticationProvider = new ApiKeyAuthenticationProvider(key);

        if(apiKeyAuthenticationProvider.supports(authentication.getClass())) {
            return apiKeyAuthenticationProvider.authenticate(authentication);
        }

        return authentication;
    }
}
