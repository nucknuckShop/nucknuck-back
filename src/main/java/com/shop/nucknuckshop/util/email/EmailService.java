package com.shop.nucknuckshop.util.email;

import com.shop.nucknuckshop.user.domain.Email;

public interface EmailService {

    void sendTempPassword(Email email);
}
