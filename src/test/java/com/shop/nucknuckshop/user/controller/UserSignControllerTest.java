package com.shop.nucknuckshop.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.nucknuckshop.exception.BadPasswordException;
import com.shop.nucknuckshop.exception.NoSuchUserException;
import com.shop.nucknuckshop.user.testconfigs.MockMvcRestDocumentationConfig;
import com.shop.nucknuckshop.user.controller.request.UserSignInRequest;
import com.shop.nucknuckshop.user.controller.request.UserSignUpRequest;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.infrastructrue.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.shop.nucknuckshop.user.testconfigs.ClearFieldDescriptor.parameter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@Import(MockMvcRestDocumentationConfig.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Slf4j
class UserSignControllerTest {

    private final ObjectMapper objectMapper;
    private final UserJpaRepository userJpaRepository;
    private final MockMvc mockMvc;
    private final RestDocumentationResultHandler resultHandler;

    @SneakyThrows
    String signUpRequestToJsonByEmailAndPassword(String email, String password){
        return objectMapper.writeValueAsString(UserSignUpRequest.builder().email(email).password(password).build());
    }

    @SneakyThrows
    String signInRequestToJsonByEmailAndPassword(String email, String password){
        return objectMapper.writeValueAsString(UserSignInRequest.builder().email(email).password(password).build());
    }

    @BeforeAll
    static void beforeAll(){
        log.info("user sign api test started...");
    }

    @BeforeEach
    void clear(){
        userJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    @SneakyThrows
    @WithMockUser
    void signUp() {
        //given
        String jsonRequest = signUpRequestToJsonByEmailAndPassword("closeup123@naver.com", "123456@!");

        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andDo(resultHandler.document(
                        requestFields(
                            parameter("email").description("user email"),
                            parameter("password").description("user password").attributes(key("constraint").value("특수문자 2개 포함")),
                            parameter("name").description("user name").optional()
                        )
                ));

        //when
        User user = userJpaRepository.findAll().get(0);

        //then
        assertThat(user.getEmail().getValue()).isEqualTo("closeup123@naver.com");
        assertThat(user.getPassword().match("123456@!")).isTrue();
    }

    @Test
    @DisplayName("회원가입 실패 - 유효성 검증 실패 : 비밀번호에 특수문자 없음")
    @SneakyThrows
    @WithMockUser
    void signUpFailNoPunctuation() {
        //given
        String jsonRequest = signUpRequestToJsonByEmailAndPassword("closeup1202@naver.com", "123456");

        //when-then
        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 실패 - 이미 가입된 이메일")
    @SneakyThrows
    @WithMockUser
    void signUpFailAlreadyExistEmail() {
        //given
        User user = User.builder().email("closeup1202@naver.com").password("12345@@").build();
        userJpaRepository.save(user);

        String jsonRequest = signUpRequestToJsonByEmailAndPassword("closeup1202@naver.com", "12345@@");

        //when-then
        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DuplicateKeyException))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 성공")
    @SneakyThrows
    @WithMockUser
    void signIn(){
        //given
        String jsonRequest = signInRequestToJsonByEmailAndPassword("closeup1234@naver.com", "1234567@@");
        User user = User.builder().email("closeup1234@naver.com").password("1234567@@").build();
        userJpaRepository.save(user);

        //when-then
        mockMvc.perform(post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 실패 - 등록된 회원 없음")
    @SneakyThrows
    @WithMockUser
    void signInFailNoSuchUser(){
        //given
        String jsonRequest = signInRequestToJsonByEmailAndPassword("closeup123@naver.com", "12345678@@");

        //when
        mockMvc.perform(post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchUserException))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    @SneakyThrows
    @WithMockUser
    void signInFailBadPassword(){
        //given
        String jsonRequest = signInRequestToJsonByEmailAndPassword("closeup1234@naver.com", "1234567@!");
        User user = User.builder().email("closeup1234@naver.com").password("1234567@@").build();
        userJpaRepository.save(user);

        //when
        mockMvc.perform(post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadPasswordException))
                .andDo(print());
    }
}