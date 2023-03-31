package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LoginExceptionEnum {

    EMAIL_OR_PASSWORD_INCORRECT("BNK_LGN_001", "user email or password is incorrect", 403),
    INVALID_TOKEN("BKN_LGN_002", "invalid token", 401),
    EXPIRED_EXCEPTION("BKN_LGN_003", "expired token", 401);

    private String code;
    private String message;
    private Integer statusCode;
}
