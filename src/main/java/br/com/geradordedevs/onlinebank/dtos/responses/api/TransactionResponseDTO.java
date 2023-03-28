package br.com.geradordedevs.onlinebank.dtos.responses;

import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private String id;
    private String payerEmail;
    private String payeeName;
    private String payeeEmail;
    private BigDecimal paymentValue;
    private LocalDateTime time;
}
