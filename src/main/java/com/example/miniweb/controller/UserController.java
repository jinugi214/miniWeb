package com.example.miniweb.controller;


import com.example.miniweb.dto.UserDto;
import com.example.miniweb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/newUser")
    public String createUserPage(){
        return "newUser";
    }

    @PostMapping("/newUser")
    public String createUser(UserDto userDto){
        log.info("{}", userDto);
        userService.create(userDto);

        return "login";


    }

}
