package com.shop.nucknuckshop.user.infrastructure;

import com.shop.nucknuckshop.user.domain.Email;
import com.shop.nucknuckshop.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Email> {

}
