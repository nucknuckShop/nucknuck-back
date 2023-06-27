package com.shop.nucknuckshop.user.controller;

import com.shop.nucknuckshop.user.application.UserSignService;
import com.shop.nucknuckshop.user.controller.request.UserSignInRequest;
import com.shop.nucknuckshop.user.controller.request.UserSignUpRequest;
import com.shop.nucknuckshop.user.controller.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserSignController {

    private final UserSignService userSignService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid UserSignUpRequest request){
        userSignService.signup(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(request.convertToResponse());
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponse> signIn(@RequestBody @Valid UserSignInRequest request){
        userSignService.signin(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(request.convertToResponse());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserResponse> inactive(@RequestBody @Valid UserSignInRequest request){
        userSignService.inactive(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(request.convertToResponse());
    }
}
