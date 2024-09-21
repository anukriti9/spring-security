package com.demo.SpringSecurity2.service;

import com.demo.SpringSecurity2.entities.User;
import com.demo.SpringSecurity2.repository.UserRepository;
import com.demo.SpringSecurity2.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findUserByUsername(username);

        return u.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
    }

}
