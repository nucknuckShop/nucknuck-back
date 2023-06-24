package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.exception.BadPasswordException;
import com.shop.nucknuckshop.exception.NoSuchUserException;
import com.shop.nucknuckshop.user.domain.Email;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shop.nucknuckshop.user.application.UserServiceHelper.*;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserSignService {

    private final UserRepository userRepository;

    @Transactional
    public void signUp(String email, String password){
        duplicatedEmailCheck(userRepository, email);
        User user = User.builder().email(email).password(password).build();
        userRepository.save(user);
    }

    public void signIn(String email, String password){
        User user = userRepository.findByEmail(new Email(email)).orElseThrow(NoSuchUserException::new);
        if(!user.getPassword().match(password)){
            throw new BadPasswordException("Wrong password");
        }
    }
}
