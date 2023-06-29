package com.shop.nucknuckshop.user.application;

import com.shop.nucknuckshop.exception.NoSuchUserException;
import com.shop.nucknuckshop.user.domain.Email;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.domain.UserRepository;
import com.shop.nucknuckshop.user.domain.UserStatus;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Slf4j
class UserSignServiceTest {

    private final UserRepository userRepository;
    private final UserSignService userSignService;

    void saveTestUser(String email, String password){
        User user = User.builder().email(email).password(password).build();
        userRepository.save(user);
    }

    @BeforeEach
    void beforeEach(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인하면 로그인 시간이 업데이트된다")
    @Transactional
    void signinWithLastLoginTimestamp(){
        //given
        String email = "closeup1202@naver.com";
        String password = "12345@@";
        saveTestUser(email, password);

        Clock clock = Clock.fixed(Instant.parse("2000-01-01T00:00:00Z"), ZoneId.of("UTC"));
        MockClockHolder mockClockHolder = new MockClockHolder(clock);
        UserSignService userSignServiceMockClockHolder = new UserSignService(userRepository, mockClockHolder);

        //when
        userSignServiceMockClockHolder.signin(email, password);
        User updatedUser = userRepository.findByEmail(new Email(email)).orElseThrow(NoSuchUserException::new);

        //then
        assertThat(updatedUser.getEmail().getValue()).isEqualTo(email);
        assertThat(updatedUser.getLastLoginTimestamp()).isEqualTo(946684800000L);
    }

    @Test
    @DisplayName("회원 비활성화")
    @Transactional
    void inactive(){
        //given
        String email = "closeup1202@naver.com";
        String password = "12345@@";
        saveTestUser(email, password);

        //when
        userSignService.inactive(email, password);
        User updatedUser = userRepository.findByEmail(new Email(email)).orElseThrow(NoSuchUserException::new);

        //then
        assertThat(updatedUser.getUserStatus()).isEqualTo(UserStatus.INACTIVE);
        assertThat(updatedUser.getInactiveDate()).isNotNull();
    }

    @Test
    @DisplayName("비활성화되어 있는 회원이 로그인을 하면 활성화로 바뀐다")
    void signinSwitchInactiveUserStatus(){
        //given
        String email = "closeup1203@naver.com";
        String password = "12345@@";
        saveTestUser(email, password);

        //when
        userSignService.inactive(email, password);
        userSignService.signin(email, password);
        User updatedUser = userRepository.findByEmail(new Email(email)).orElseThrow(NoSuchUserException::new);

        //then
        assertThat(updatedUser.getUserStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(updatedUser.getInactiveDate()).isNull();
    }

}