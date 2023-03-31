package br.com.geradordedevs.onlinebank.entities;

import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private BigDecimal accountBalance;
    private DocumentTypeEnum documentType;
    private String documentNumbers;
    private UserTypeEnum userType;
}
