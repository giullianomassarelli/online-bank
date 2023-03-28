package br.com.geradordedevs.onlinebank.dtos.responses.api;

import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String name;
    private String email;
    private DocumentTypeEnum documentType;
    private String documentNumbers;
    private UserTypeEnum userType;
}
