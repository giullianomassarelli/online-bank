package br.com.geradordedevs.onlinebank.services;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;

import java.math.BigDecimal;

public interface NotificationService {

    void sendEmail (String payerEmail, String payeeEmail, BigDecimal paymentValue);
}
