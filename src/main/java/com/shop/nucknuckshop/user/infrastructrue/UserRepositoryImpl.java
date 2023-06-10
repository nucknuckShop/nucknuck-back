package com.shop.nucknuckshop.user.infrastructrue;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.nucknuckshop.user.controller.request.UserSearch;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> getList(UserSearch userSearch) {
        return Collections.emptyList();
    }

    @Override
    public User findById(Long id){
        return userJpaRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existById(Long id){
        return userJpaRepository.existsById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

}
