package br.com.geradordedevs.onlinebank.dtos.requests;

import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    private String payeeEmail;
    private BigDecimal paymentValue;

}
