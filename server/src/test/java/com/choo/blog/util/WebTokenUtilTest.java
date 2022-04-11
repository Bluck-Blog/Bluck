package com.choo.blog.util;

import com.choo.blog.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("토큰 관리")
@SpringBootTest
class WebTokenUtilTest {
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.YtZGmX-aSi2J0hFNj4KT7E8yMid4h1iUSa6-bUODUj4";
    private static final String INVALID_TOKEN = VALID_TOKEN + "xxx";
    private static final Long VALID_ID = 1L;

    @Autowired
    private WebTokenUtil webTokenUtil;

    @Nested
    @DisplayName("인코딩은")
    class Describe_encode{
        @Nested
        @DisplayName("유효한 id가 주어지면")
        class Context_with_id{
            @Test
            @DisplayName("토큰을 반급한다.")
            void it_return_signed_token(){
                String token = webTokenUtil.encode(VALID_ID);
                assertThat(token).isEqualTo(VALID_TOKEN);
            }
        }
    }

    @Nested
    @DisplayName("디코딩은")
    class Describe_decode{
        @Nested
        @DisplayName("유효한 토큰이 주어지면")
        class Context_with_valid_token{
            @Test
            @DisplayName("해당 id를 반환한다")
            void it_return_id(){
                Claims claims = webTokenUtil.decode(VALID_TOKEN);
                assertThat(claims.get("userId", Long.class)).isEqualTo(VALID_ID);
            }
        }

        @Nested
        @DisplayName("유효하지 않은 토큰이 주어지면")
        class Context_with_invalid_token{
            @Test
            @DisplayName("서명되지 않았다는 예외를 던진다")
            void it_throw_InvalidTokenException(){
                assertThatThrownBy(() -> webTokenUtil.decode(INVALID_TOKEN))
                        .isInstanceOf(InvalidTokenException.class);
            }
        }
    }
}