package com.shop.nucknuckshop.user.controller.request;

import com.shop.nucknuckshop.annotation.Punctuation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserPasswordChangeRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(max = 20, min = 6)
    @Punctuation(requireCount = 2)
    private String currentPassword;

    @NotNull
    @Size(max = 20, min = 6)
    @Punctuation(requireCount = 2)
    private String newPassword;

    @Builder
    public UserPasswordChangeRequest(String email, String currentPassword, String newPassword) {
        this.email = email;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
