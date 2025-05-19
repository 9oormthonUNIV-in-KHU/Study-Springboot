package me.shinsunyoung.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Jwts;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken()")
    @Test
    void generateToken() {
        //given
        User testUser = userRepository.save(User.builder()
                .email("user@email.com")
                .password("test")
                .build()
        );
        //when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));
        //then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }

    @DisplayName("validToken()- 만료된 토큰")
    @Test
    public void validToken_invalidToken() {
        //given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isFalse();

    }

    @DisplayName("validToken()- 유효한 토큰")
    @Test
    public void validToken_validToken() {
        //given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isTrue();

    }

    @DisplayName("getAuthentication()")
    @Test
    public void getAuthentication() {
        //given
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);
        //when
        Authentication authentication = tokenProvider.getAuthentication(token);

        //then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }

    @DisplayName("getUserId()")
    @Test
    public void getUserId(){
        //given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id",userId))
                .build()
                .createToken(jwtProperties);

        //when
        Long userIdByToken = tokenProvider.getUserId(token);

        //then
        assertThat(userIdByToken).isEqualTo(userId);

    }
}
