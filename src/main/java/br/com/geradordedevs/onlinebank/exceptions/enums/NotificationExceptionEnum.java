package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum NotificationExceptionEnum {

    ERROR_TO_SEND_EMAIL("BNK_NTF_001", "erro to send email", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
