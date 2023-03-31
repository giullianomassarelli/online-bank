package br.com.geradordedevs.onlinebank.exceptions;

import br.com.geradordedevs.onlinebank.exceptions.enums.TransactionExceptionEnum;
import br.com.geradordedevs.onlinebank.exceptions.enums.UserExceptionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransactionException extends OnlineBankException{

    private static final long serialVersionUID = -4589179341768493322L;

    public TransactionException(TransactionExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }
   private final TransactionExceptionEnum error;
}
