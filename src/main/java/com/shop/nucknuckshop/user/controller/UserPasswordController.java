package com.shop.nucknuckshop.user.controller;

import com.shop.nucknuckshop.exception.NoSuchUserException;
import com.shop.nucknuckshop.user.application.UserPasswordService;
import com.shop.nucknuckshop.user.controller.request.UserPasswordChangeRequest;
import com.shop.nucknuckshop.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/user/password")
@RequiredArgsConstructor
@Slf4j
public class UserPasswordController {

    private final UserPasswordService userPasswordService;

    @PostMapping("/change")
    public void changePassword(@RequestBody @Valid UserPasswordChangeRequest request){
        userPasswordService.change(request.getEmail(),request.getCurrentPassword(), request.getNewPassword());
    }

    @PostMapping("/find")
    public void findPassword(@RequestBody @Valid @Email String email){
        userPasswordService.find(email);
    }
}
