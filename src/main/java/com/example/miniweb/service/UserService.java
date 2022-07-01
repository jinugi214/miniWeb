package com.example.miniweb.service;

import com.example.miniweb.domain.User;
import com.example.miniweb.dto.UserDto;
import com.example.miniweb.repository.BoardRepository;
import com.example.miniweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void create(UserDto userDto){

        User user = User.builder()
                .userId((userDto.getUserId()))
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name((userDto.getName()))
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .role("ROLE_USER")
                .point(0)
                .createdDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public Optional<User> read(Long id){

        Optional<User> user = userRepository.findById(id);

        return user;
    }
}
