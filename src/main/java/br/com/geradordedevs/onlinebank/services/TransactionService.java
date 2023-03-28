package br.com.geradordedevs.onlinebank.services;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;

import java.util.List;

public interface TransactionService {

    TransactionEntity save (TransactionEntity transactionEntity);

    List<TransactionEntity> getAll();
}
