package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.util.clock.ClockHolder;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shop.nucknuckshop.user.application.UserServiceHelper.*;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class UserSignService {

    private final UserRepository userRepository;
    private final ClockHolder clockHolder;

    @Transactional
    public void signup(String email, String password){
        duplicatedEmailCheck(userRepository, email);
        User user = User.builder().email(email).password(password).build();
        userRepository.save(user);
    }

    @Transactional
    public void signin(String email, String password){
        User user = findExistingUser(userRepository, email);
        user.verifyWrongPassword(password);
        user.updateLastLoginTimestamp(clockHolder);
    }

    @Transactional
    public void inactive(String email, String password){
        User user = findExistingUser(userRepository, email);
        user.verifyWrongPassword(password);
        user.inactive();
    }
}
