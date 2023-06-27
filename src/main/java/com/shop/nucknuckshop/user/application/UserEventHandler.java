package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.user.domain.Email;
import com.shop.nucknuckshop.user.email.EmailService;
import com.shop.nucknuckshop.user.email.EmailServiceJavaMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventHandler {

    private final EmailService emailService;

    @EventListener(Email.class)
    public void onFindPasswordEvent(Email email){
        emailService.sendTempPassword(email);
    }
}
