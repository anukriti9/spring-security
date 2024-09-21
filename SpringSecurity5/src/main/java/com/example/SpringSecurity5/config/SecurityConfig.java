package com.example.SpringSecurity5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .and()
                .authorizeRequests()
                .requestMatchers("/demo").permitAll()
                .anyRequest().hasAnyAuthority("write", "ROLE_ADMIN", "read")
                .and().build();

        // matcher method + authorization rule
        // 1. which matcher methods should you use and how  ( anyRequest(), mvcMatchers(), antMatchers(), regexMatchers() )
        // 2. how to apply different authorization rules
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var udm = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("bill")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
//                .authorities("read")
                .build();

        udm.createUser(user1);

        var user2 = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("write")
                .build();

        udm.createUser(user2);

        return udm;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
