package br.com.geradordedevs.onlinebank.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    @Email
    @NotBlank
    private String payeeEmail;

    @Min(value = 1, message = "min 1$ to do a new transaction")
    private BigDecimal paymentValue;

}
