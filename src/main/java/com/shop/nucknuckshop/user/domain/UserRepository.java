package com.shop.nucknuckshop.user.domain;

import com.shop.nucknuckshop.user.controller.request.UserSearch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    List<User> getList(UserSearch userSearch);

    Optional<User> findByEmail(Email email);

    void save(User user);

    void delete(User user);
}
