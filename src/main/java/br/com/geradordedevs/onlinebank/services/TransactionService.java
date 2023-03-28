package br.com.geradordedevs.onlinebank.services;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;

public interface TransactionService {

    TransactionEntity save (TransactionEntity transactionEntity);
}
