package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shop.nucknuckshop.user.application.UserServiceHelper.*;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void change(String email, String currentPassword, String newPassword){
        User user = findExistingUser(userRepository, email);
        user.changePassword(currentPassword, newPassword);
    }

    public void find(String email){
        User user = findExistingUser(userRepository, email);
        eventPublisher.publishEvent(user.getEmail());
    }

}
