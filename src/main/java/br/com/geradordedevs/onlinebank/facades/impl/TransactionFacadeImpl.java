package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import br.com.geradordedevs.onlinebank.mappers.TransactionMapper;
import br.com.geradordedevs.onlinebank.services.TransactionService;
import br.com.geradordedevs.onlinebank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TransactionFacadeImpl implements TransactionFacade {
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Override
    public List<TransactionResponseDTO> getAll(String email) {
        return transactionMapper.convertTransactionEntityListToTransactionResponseDTOList(transactionService.getAll(), email);
    }
}
