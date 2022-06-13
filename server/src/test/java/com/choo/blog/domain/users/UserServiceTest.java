package com.choo.blog.domain.users;

import com.choo.blog.common.UserProperties;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.exceptions.InvalidVerifyCodeException;
import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.UserNotFoundException;
import com.choo.blog.util.VerifyCodeUtil;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("회원 관리")
class UserServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserProperties userProperties;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Nested
    @DisplayName("회원 가입은")
    class Descrive_join{
        UserRegistData registData;
        MockHttpSession session;

        @BeforeEach
        void setUp(){
            String code = "a1234";
            session = userProperties.generateVerifySession(passwordEncoder, code);
            registData = userProperties.prepareUserRegistData("");
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setSession(session);
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

            ReflectionTestUtils.setField(registData, "verifyCode", code);
        }

        @AfterEach
        void cleanUp(){
            userProperties.cleanUp();
        }

        @Nested
        @DisplayName("회원 정보를 입력받으면")
        class context_with_user_info{


            @Test
            @DisplayName("회원을 등록하고 등록된 회원 정보를 반환한다.")
            void it_return_user(){
                User user = userService.join(registData);

                assertThat(user.getEmail()).isEqualTo(registData.getEmail());
                assertThat(user.getBirthdate()).isEqualTo(registData.getBirthdate());
                assertThat(user.getIntroduction()).isEqualTo(registData.getIntroduction());
                assertThat(user.getNickname()).isEqualTo(registData.getNickname());
                assertThat(passwordEncoder.matches(registData.getPassword(), user.getPassword())).isTrue();
            }
        }

        @Nested
        @DisplayName("잘못된 인증번호가 주어지면")
        class Context_with_wrong_verify_code{

            @BeforeEach
            void setUp(){
                ReflectionTestUtils.setField(registData,"verifyCode", "wrong code");
            }

            @Test
            @DisplayName("유효하지 않은 인증번호라는 예외를 던진다.")
            void it_throw_invalidVerifyException(){
                assertThatThrownBy(() -> userService.join(registData))
                        .isInstanceOf(InvalidVerifyCodeException.class);
            }
        }


    }

    @Nested
    @DisplayName("회원 조회는")
    class Describe_get{
        @Nested
        @DisplayName("userId를 입력받으면")
        class Context_with_userId{
            User user;

            @BeforeEach
            void setUp(){
                user = prepareUser("");
            }

            @Test
            @DisplayName("id에 해당하는 user를 반환한다")
            void it_return_user(){
                User findUser = userService.getUser(this.user.getId());

                assertThat(findUser).isNotNull();
                assertThat(findUser.getId()).isNotZero();
            }
        }

        @Nested
        @DisplayName("존재하지 않는 userId를 입력받으면")
        class Context_with_non_exist_userId{
            @Test
            @DisplayName("user를 찾을 수 없다는 예외를 던진다.")
            void it_throw_userNotFoundException(){
                assertThatThrownBy(() -> userService.getUser(-1L))
                        .isInstanceOf(UserNotFoundException.class);
            }
        }
    }

    @Nested
    @DisplayName("인증번호 생성은")
    class Describe_create_verify_code{
        @Nested
        @DisplayName("이메일이 주어지면")
        class Context_with_email{
            String email;

            @BeforeEach
            void setUp(){
                email = "ddgg9511@naver.com";
            }
            @Test
            @DisplayName("인증번호를 생성하고 생성된 인증번호를 반환한다.")
            void it_create_and_return_verifyCode(){
                String code = userService.generateVerifyCode(email);
                assertThat(code).isNotEmpty();
            }
        }
    }

    @Nested
    @DisplayName("이메일 인증은")
    class Describe_verify_email{
        @Nested
        @DisplayName("인증번호가 주어지면")
        class Context_with_verify_code{
            String rawCode;
            String code;
            MockedStatic<VerifyCodeUtil> verifyCodeStaticMock;

            @BeforeEach
            void setUp(){
                rawCode = "AB1234";
                verifyCodeStaticMock = mockStatic(VerifyCodeUtil.class);
                when(VerifyCodeUtil.generateToken()).thenReturn(rawCode);

                code = userService.generateVerifyCode("ddgg9511@naver.com");
            }

            @AfterEach
            void cleanUp(){
                verifyCodeStaticMock.close();
            }

            @Test
            @DisplayName("true를 반환한다.")
            void it_return_true(){
                boolean result = userService.verifyEmail(rawCode, code);
                assertThat(result).isTrue();
            }
        }
    }

    private User prepareUser(String suffix){
        User user = modelMapper.map(userProperties.prepareUserRegistData(suffix), User.class);
        return userRepository.save(user);
    }
}