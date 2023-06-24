package com.shop.nucknuckshop.user.infrastructrue;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.nucknuckshop.user.controller.request.UserSearch;
import com.shop.nucknuckshop.user.domain.Email;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> getList(UserSearch userSearch) {
        return Collections.emptyList();
    }

    @Override
    public Optional<User> findByEmail(Email email){
        return userJpaRepository.findById(email);
    }

    public void save(User user){
        userJpaRepository.save(user);
    }
}
