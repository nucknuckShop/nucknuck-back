package com.shop.nucknuckshop.user.email;

import com.shop.nucknuckshop.user.domain.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendTempPassword(Email email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getValue()));
            mimeMessage.addFrom(new InternetAddress[]{new InternetAddress("nnsadmin@gmail.com")});
            mimeMessage.setSubject("[넉넉샵] 비밀번호 재설정을 위한 안내입니다");
            mimeMessage.setText("테스트", "UTF-8", "html");
            log.info("mail send : {}", mimeMessage);
            //javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.info("e : {}", e.getMessage());
        }
    }
}
