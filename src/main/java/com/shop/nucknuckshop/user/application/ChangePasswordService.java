package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shop.nucknuckshop.user.application.UserServiceHelper.*;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final UserRepository userRepository;

    @Transactional
    public void changePassword(String email, String currentPassword, String newPassword){
        User user = findExistingUser(userRepository, email);
        user.changePassword(currentPassword, newPassword);
    }

}
