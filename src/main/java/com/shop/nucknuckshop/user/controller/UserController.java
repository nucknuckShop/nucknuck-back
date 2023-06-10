package com.shop.nucknuckshop.user.controller;

import com.shop.nucknuckshop.user.application.UserService;
import com.shop.nucknuckshop.user.controller.request.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String hello(){
        return "nuck nuck";
    }

//    @PostMapping("/signin")
//    public void signIn(@RequestBody @Valid SignInRequest request){
//        request.validate();
//        userService.signIn(request.getEmail());
//    }


}
