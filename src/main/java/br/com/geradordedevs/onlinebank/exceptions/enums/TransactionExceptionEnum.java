package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TransactionExceptionEnum {

    INSUFFICIENT_FUNDS("BNK_USR_003", "insufficient funds", 406),
    UNAUTHORIZED_PAYMENT("BNK_USR_004", "unauthorized payment", 401),
    OPERATION_NOT_ALLOWED("BNK_USR_005", "operation not allowed to storeUser ", 405),
    INVALID_TRANSACTION_VALUE("BNK_USR_005", "invalid transcation value", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
