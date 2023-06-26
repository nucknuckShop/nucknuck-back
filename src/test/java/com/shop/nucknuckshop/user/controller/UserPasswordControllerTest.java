package com.shop.nucknuckshop.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.nucknuckshop.exception.BadPasswordException;
import com.shop.nucknuckshop.user.controller.request.UserPasswordChangeRequest;
import com.shop.nucknuckshop.user.domain.User;
import com.shop.nucknuckshop.user.infrastructrue.UserJpaRepository;
import com.shop.nucknuckshop.user.testconfigs.MockMvcRestDocumentationConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.shop.nucknuckshop.user.testconfigs.ClearFieldDescriptor.parameter;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
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
class UserPasswordControllerTest {

    private final ObjectMapper objectMapper;
    private final UserJpaRepository userJpaRepository;
    private final MockMvc mockMvc;
    private final RestDocumentationResultHandler resultHandler;

    @SneakyThrows
    String getPasswordChangeRequestToJson(String email, String currentPassword, String newPassword){
        return objectMapper.writeValueAsString(UserPasswordChangeRequest.builder().email(email).currentPassword(currentPassword).newPassword(newPassword).build());
    }

    @BeforeAll
    static void beforeAll(){
        log.info("user password api test started...");
    }

    @BeforeEach
    void clear(){
        userJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("비밀번호 변경 성공")
    @SneakyThrows
    void changePasswordSuccess(){
        //given
        User user = User.builder().email("closeup1202@naver.com").password("12345@@").build();
        userJpaRepository.save(user);

        String jsonRequest = getPasswordChangeRequestToJson("closeup1202@naver.com", "12345@@", "12345!!");

        //when
        mockMvc.perform(post("/user/password/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andDo(resultHandler.document(
                        requestFields(
                                parameter("email").description("user email"),
                                parameter("currentPassword").description("user current password"),
                                parameter("newPassword").description("user new password")
                        )
                ));

        //then
        User newPasswordUser = userJpaRepository.findAll().get(0);
        Assertions.assertThat(newPasswordUser.getPassword().match("12345!!")).isTrue();
    }

    @Test
    @DisplayName("비밀번호 변경 실패 - 기존 비밀번호 불일치")
    @SneakyThrows
    void changePasswordFail(){
        //given
        User user = User.builder().email("closeup1203@naver.com").password("123456@@").build();
        userJpaRepository.save(user);

        String jsonRequest = getPasswordChangeRequestToJson("closeup1203@naver.com", "123456##", "123456!!");

        //when
        mockMvc.perform(post("/user/password/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadPasswordException))
                .andDo(print());

        //then
        User newPasswordUser = userJpaRepository.findAll().get(0);
        Assertions.assertThat(newPasswordUser.getPassword().match("123456@@")).isTrue();
    }
    
    @Test
    @DisplayName("비밀번호 변경 실패 - 기존 비밀번호랑 같다")
    @SneakyThrows
    void changePasswordFailSamePassword(){
        User user = User.builder().email("closeup1202@naver.com").password("12345@@").build();
        userJpaRepository.save(user);

        String jsonRequest = getPasswordChangeRequestToJson("closeup1202@naver.com", "12345@@", "12345@@");

        //when
        mockMvc.perform(post("/user/password/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadPasswordException))
                .andDo(print());
    }

    @Test
    @DisplayName("비밀번호 찾기 성공")
    @SneakyThrows
    void findPasswordSuccess(){
        //given
        User user = User.builder().email("closeup1202@naver.com").password("12345@@").build();
        userJpaRepository.save(user);

        //when
        mockMvc.perform(post("/user/password/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("closeup1202@naver.com"))
                .andExpect(status().isOk())
                .andDo(print());

    }
}