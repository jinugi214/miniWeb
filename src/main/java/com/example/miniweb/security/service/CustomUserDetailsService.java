package com.example.miniweb.security.service;

import com.example.miniweb.domain.User;
import com.example.miniweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUserId(username)
                .orElseThrow(()-> new UsernameNotFoundException("Could not found user" + username));

        log.info("Success find member {}", user);

        return new CustomUserDetails(user.getUserId(), user.getPassword(), user.getNickname(), Arrays.asList(user.getRole()));

    }
}
