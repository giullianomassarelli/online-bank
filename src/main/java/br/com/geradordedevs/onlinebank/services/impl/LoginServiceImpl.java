package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.exceptions.LoginException;
import br.com.geradordedevs.onlinebank.exceptions.enums.LoginExceptionEnum;
import br.com.geradordedevs.onlinebank.services.LoginService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${login.jwt.secret}")
    private String secret;


    @Override
    public String generateToken(String email) {
        log.info("try generate token to user : {}", email);

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("online-bank")
                    .withClaim("email", email)
                    .withExpiresAt(new Date(Instant.now().toEpochMilli() + TimeUnit.HOURS.toMillis(10)))
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            log.warn("error to generate token");
            throw new LoginException(LoginExceptionEnum.INVALID_TOKEN);
        }

    }

    @Override
    public String verifyToken(String token) {
        log.info("verify user token");

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("online-bank")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            String email = jwt.getClaim("email").asString();
            Date expiresAt = jwt.getExpiresAt();

            validExpireToken(token, expiresAt);

            return email;
        } catch (JWTCreationException ex){
            log.warn("expired token");
            throw new LoginException(LoginExceptionEnum.EXPIRED_EXCEPTION);
        }



    }

    private void validExpireToken(String token, Date expiresAt) {
        log.info("checking if token is expired - expiresAt: {}", expiresAt);
        if (expiresAt.before(new Date())) {
            log.warn("the {} token is expired", token);
            throw new LoginException(LoginExceptionEnum.EXPIRED_EXCEPTION);
        }
    }
}
