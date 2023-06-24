package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.exception.NoSuchUserException;
import com.shop.nucknuckshop.user.domain.Email;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.dao.DuplicateKeyException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserServiceHelper {

    public static User findExistingUser(UserRepository repository, String email){
        return repository.findByEmail(new Email(email)).orElseThrow(NoSuchUserException::new);
    }

    public static void duplicatedEmailCheck(UserRepository repository, String email){
        repository.findByEmail(new Email(email)).ifPresent(user -> {
            throw new DuplicateKeyException(user.getEmail().getValue() + " already exists");
        });
    }

}
