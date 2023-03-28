package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserExceptionEnum {

    USER_NOT_FOUND("BNK_USR_001", "user not found in data base", 404),
    USER_ALREADY_EXIST("BNK_USR_002", "user already in data base", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
