package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.clients.OnlinePaymentClient;
import br.com.geradordedevs.onlinebank.dtos.responses.onlinePayment.OnlinePaymentResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.repositories.TransactionRepository;
import br.com.geradordedevs.onlinebank.services.PaymentService;
import br.com.geradordedevs.onlinebank.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionEntity save(TransactionEntity transactionEntity) {
        log.info("save new transaction in db: {}" , transactionEntity);
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public List<TransactionEntity> getAll() {
        log.info("find all transaction by ");
        return transactionRepository.findAll();
    }
}
