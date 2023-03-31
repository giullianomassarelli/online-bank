package br.com.geradordedevs.onlinebank.dtos.requests;

import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @Size(min = 3, max = 20, message = "invalid name size, min 3 & max 20")
    private String name;

    @NotBlank
    private DocumentTypeEnum documentType;

    @Size(min = 14, max = 18, message = "invalid document size, for CPF 14 (11.111.111-11) and for CNPJ 18 (11.111.111/1111-11)")
    private String documentNumbers;

    @Email
    @NotBlank
    private String email;

    @Size(min = 8, max = 20, message = "invalid password size, min 8 & max 20")
    private String password;

}
