package com.illustration.illustrationproject.Utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.illustration.illustrationproject.Requests.RegisterRequest;
import com.illustration.illustrationproject.Services.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        RegisterRequest user = new RegisterRequest();
        user.setUsername("admin");
        user.setEmail("aymen.omri@istic.ucar.tn");
        user.setPassword("123456789");

        userService.createAdmin(user);
    }

}
