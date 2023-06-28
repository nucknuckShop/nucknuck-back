package com.shop.nucknuckshop.user.domain;

import com.shop.nucknuckshop.exception.BadPasswordException;
import com.shop.nucknuckshop.util.clock.ClockHolder;
import lombok.*;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "NNS_USER")
@Table(name = "NNS_USER")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @EmbeddedId
    private Email email;

    @Embedded
    private Password password;

    private long lastLoginTimestamp = Clock.systemUTC().millis();

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE;

    private LocalDate inactiveDate;

    @Builder
    public User(String email, String password) {
        this.email = new Email(email);
        this.password = new Password(password);
    }

    public void verifyWrongPassword(String password) {
        if(!this.password.match(password)){
            throw new BadPasswordException();
        }
    }

    public void verifySamePassword(String password) {
        if(this.password.match(password)){
            throw new BadPasswordException();
        }
    }

    public void changePassword(String currentPassword, String newPassword){
        verifyWrongPassword(currentPassword);
        verifySamePassword(newPassword);
        this.password = new Password(newPassword);
    }

    public void updateLastLoginTimestamp(ClockHolder clockHolder){
        this.lastLoginTimestamp = clockHolder.getMillis();
    }

    public void inactive(){
        this.userStatus = UserStatus.INACTIVE;
        this.inactiveDate = LocalDateTime.now().toLocalDate();
    }
}
