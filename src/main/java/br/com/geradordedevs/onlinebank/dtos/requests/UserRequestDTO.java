package br.com.geradordedevs.onlinebank.dtos.requests;

import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;
    private DocumentTypeEnum documentType;
    private String documentNumbers;
    private String email;

}
