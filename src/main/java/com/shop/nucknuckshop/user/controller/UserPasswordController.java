package com.shop.nucknuckshop.user.controller;

import com.shop.nucknuckshop.user.application.UserPasswordService;
import com.shop.nucknuckshop.user.controller.request.UserPasswordChangeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
