package com.shop.nucknuckshop.user.controller;

import com.shop.nucknuckshop.exception.NoSuchUserException;
import com.shop.nucknuckshop.user.application.ChangePasswordService;
import com.shop.nucknuckshop.user.controller.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserPasswordController {

    private final ChangePasswordService changePasswordService;

    @PostMapping("/changePassword")
    public void changePassword(ChangePasswordRequest request){
        try{
            changePasswordService.changePassword(request.getEmail(),
                    request.getCurrentPassword(), request.getNewPassword());
        } catch (NoSuchUserException e){
            log.info("e : {}", e.getMessage());
        }
    }
}
