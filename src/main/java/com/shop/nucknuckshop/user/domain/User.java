package com.shop.nucknuckshop.user.domain;

import com.shop.nucknuckshop.exception.BadPasswordException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "NNS_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @EmbeddedId
    private Email email;

    @Embedded
    private Password password;

    @Builder
    public User(String email, String password) {
        this.email = new Email(email);
        this.password = new Password(password);
    }

    public void changePassword(String currentPassword, String newPassword){
        if(this.password.match(currentPassword)){
            throw new BadPasswordException("not current password");
        }
        this.password = new Password(newPassword);
    }
}
