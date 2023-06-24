package com.shop.nucknuckshop.user.controller;

import com.shop.nucknuckshop.user.application.UserSignService;
import com.shop.nucknuckshop.user.controller.request.UserSignInRequest;
import com.shop.nucknuckshop.user.controller.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserSignController {

    private final UserSignService userSignService;

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid UserSignUpRequest request){
        userSignService.signUp(request.getEmail(), request.getPassword());
    }

    @PostMapping("/signin")
    public void signIn(@RequestBody @Valid UserSignInRequest request){
        userSignService.signIn(request.getEmail(), request.getPassword());
    }
}
