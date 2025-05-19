package me.shinsunyoung.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Configuration("jwt")
public class JwtProperties {
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.secret_key}")
    private String secretKey;
}
