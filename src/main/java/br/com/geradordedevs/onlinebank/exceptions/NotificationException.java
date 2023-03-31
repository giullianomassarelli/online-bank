package br.com.geradordedevs.onlinebank.exceptions;

import br.com.geradordedevs.onlinebank.exceptions.enums.NotificationExceptionEnum;
import br.com.geradordedevs.onlinebank.exceptions.enums.UserExceptionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class NotificationException extends OnlineBankException{

    private static final long serialVersionUID = -4589179341768493322L;

    public NotificationException(NotificationExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }
   private final NotificationExceptionEnum error;
}
