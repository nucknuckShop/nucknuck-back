package com.shop.nucknuckshop.user.domain;

import com.shop.nucknuckshop.user.controller.request.UserSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> getList(UserSearch userSearch);

    User findById(Long id);

    boolean existById(Long id);

    boolean existByEmail(String email);
}
