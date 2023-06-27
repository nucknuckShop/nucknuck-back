package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.exception.NoSuchUserException;
import com.shop.nucknuckshop.user.domain.Email;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import com.shop.nucknuckshop.util.clock.MockClockHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Slf4j
class UserSignServiceTest {

    private final UserRepository userRepository;

    @Test
    @DisplayName("로그인 시간 체크")
    @Transactional
    void signIn(){
        //given
        String email = "closeup1202@naver.com";
        String password = "12345@@";

        User user = User.builder().email(email).password(password).build();
        userRepository.save(user);

        Clock clock = Clock.fixed(Instant.parse("2000-01-01T00:00:00Z"), ZoneId.of("UTC"));
        MockClockHolder mockClockHolder = new MockClockHolder(clock);
        UserSignService userSignService = new UserSignService(userRepository, mockClockHolder);

        //when
        userSignService.signin(email, password);
        User updatedUser = userRepository.findByEmail(new Email(email)).orElseThrow(NoSuchUserException::new);

        //then
        assertThat(updatedUser.getEmail().getValue()).isEqualTo(email);
        assertThat(updatedUser.getLastLoginTimestamp()).isEqualTo(946684800000L);
    }
}