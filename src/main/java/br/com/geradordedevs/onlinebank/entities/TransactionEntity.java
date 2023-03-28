package br.com.geradordedevs.onlinebank.entities;

import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {

    @Id
    private String id;
    private String payerEmail;
    private String payeeName;
    private String payeeEmail;
    private BigDecimal paymentValue;
    private LocalDateTime time;

}
