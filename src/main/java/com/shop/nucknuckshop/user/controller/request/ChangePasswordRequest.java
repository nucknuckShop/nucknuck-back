package com.shop.nucknuckshop.user.controller.request;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {

    private String email;
    private String currentPassword;
    private String newPassword;
}
