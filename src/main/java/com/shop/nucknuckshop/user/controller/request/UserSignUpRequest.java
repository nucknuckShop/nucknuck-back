package com.shop.nucknuckshop.user.controller.request;

import com.shop.nucknuckshop.annotation.Punctuation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(max = 20, min = 6)
    @Punctuation(requireCount = 2)
    private String password;

    @Nullable
    private String name;

    @Builder
    public UserSignUpRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
