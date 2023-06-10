package com.shop.nucknuckshop.user.controller.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class SignInRequest implements UserRequestValidate {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(max = 20, min = 8)
    private String password;

    @Override
    public void validate() {
        Pattern pattern = Pattern.compile("[ !@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = pattern.matcher(this.password);

        if(!matcher.find()){
            throw new IllegalArgumentException("하나 이상의 특수문자가 포함되어야 합니다");
        }
    }
}
