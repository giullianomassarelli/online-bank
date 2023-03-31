package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TransactionExceptionEnum {

    INSUFFICIENT_FUNDS("BNK_TRS_001", "insufficient funds", 406),
    UNAUTHORIZED_PAYMENT("BNK_TRS_002", "unauthorized payment", 401),
    OPERATION_NOT_ALLOWED("BNK_TRS_003", "operation not allowed to storeUser ", 405),
    INVALID_TRANSACTION_VALUE("BNK_TRS_004", "invalid transcation value", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
