package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import br.com.geradordedevs.onlinebank.exceptions.TransactionException;
import br.com.geradordedevs.onlinebank.exceptions.enums.TransactionExceptionEnum;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import br.com.geradordedevs.onlinebank.mappers.TransactionMapper;
import br.com.geradordedevs.onlinebank.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private NotificationService notificationService;

    private final String AUTH_MESSAGE = "Autorizado";

    @Override
    public List<TransactionResponseDTO> getAll(String token) {
        String email = loginService.verifyToken(token);
        return transactionMapper.convertTransactionEntityListToTransactionResponseDTOList(transactionService.getAll(), email);
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO, String token) {
        log.info("create new transaction: {}", transactionRequestDTO);

        String email = loginService.verifyToken(token);

        UserEntity userEntity = userService.findByEmail(transactionRequestDTO.getPayeeEmail());
        userEntity.setAccountBalance(transactionRequestDTO.getPaymentValue().add(userEntity.getAccountBalance()));

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setPayerEmail(email);
        transactionEntity.setPayeeName(userEntity.getName());
        transactionEntity.setPayeeEmail(transactionRequestDTO.getPayeeEmail());
        transactionEntity.setPaymentValue(transactionRequestDTO.getPaymentValue());
        transactionEntity.setTime(LocalDateTime.now());

        checkAccount(email, transactionRequestDTO.getPaymentValue());

        if (paymentService.validatePayment().getMessage().equals(AUTH_MESSAGE)) {
            userService.updateBalance(userEntity);
            TransactionResponseDTO transactionResponseDTO = transactionMapper.convertTransactionEntityToTransactionResponseDTO(transactionService.save(transactionEntity));

            notificationService.sendEmail(transactionEntity.getPayerEmail(), transactionEntity.getPayeeEmail(), transactionEntity.getPaymentValue());

            return transactionResponseDTO;
        } else {
            throw new TransactionException(TransactionExceptionEnum.UNAUTHORIZED_PAYMENT);
        }
    }

    private void checkAccount(String email, BigDecimal paymentValue) {
        UserEntity userEntity = userService.findByEmail(email);

        if (userEntity.getUserType().equals(UserTypeEnum.STORE_USER)){
            throw new TransactionException(TransactionExceptionEnum.OPERATION_NOT_ALLOWED);
        }

        double balance = userEntity.getAccountBalance().doubleValue();
        double payment = paymentValue.doubleValue();
        if (payment > balance){
            throw new TransactionException(TransactionExceptionEnum.INSUFFICIENT_FUNDS);
        }
        else if (payment < 0){
            throw new TransactionException(TransactionExceptionEnum.INVALID_TRANSACTION_VALUE);
        }
        else {
            userEntity.setAccountBalance(userEntity.getAccountBalance().subtract(paymentValue));
            userService.updateBalance(userEntity);
        }
    }
}
