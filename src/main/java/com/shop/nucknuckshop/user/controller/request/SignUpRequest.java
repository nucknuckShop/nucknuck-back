package com.shop.nucknuckshop.user.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotNull
    @Email
    private String email;

    @Size(max = 12)
    @NotNull
    private String password;

    private String name;

}
