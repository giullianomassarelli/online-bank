package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.clients.OnlinePaymentClient;
import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.BalanceResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.onlinePayment.OnlinePaymentResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import br.com.geradordedevs.onlinebank.exceptions.TransactionException;
import br.com.geradordedevs.onlinebank.exceptions.UserException;
import br.com.geradordedevs.onlinebank.exceptions.enums.TransactionExceptionEnum;
import br.com.geradordedevs.onlinebank.exceptions.enums.UserExceptionEnum;
import br.com.geradordedevs.onlinebank.facades.UserFacade;
import br.com.geradordedevs.onlinebank.mappers.TransactionMapper;
import br.com.geradordedevs.onlinebank.mappers.UserMapper;
import br.com.geradordedevs.onlinebank.services.PaymentService;
import br.com.geradordedevs.onlinebank.services.TransactionService;
import br.com.geradordedevs.onlinebank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        validateUserAlreadyExist(userRequestDTO.getEmail(), userRequestDTO.getDocumentNumbers());
        return userMapper.convertUserEntityToUserResponseDTO(userService.create(userMapper.convertUserRequestDTOTOUserEntity(userRequestDTO)));
    }

    private void validateUserAlreadyExist(String email, String documentNumbers) {
        userService.verifyUserExist(email, documentNumbers);
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        return userMapper.convertUserEntityToUserResponseDTO(userService.findByEmail(email));
    }

    @Override
    public List<UserResponseDTO> populate() {
        userService.create(new UserEntity(null,
                "Giulliano PF",
                "massarelli47@gmail.com",
                new BigDecimal("1000"),
                DocumentTypeEnum.CPF,
                "366.299.458-59",
                UserTypeEnum.COMMON_USER));
        userService.create(new UserEntity(null,
                "Giulliano PJ",
                "gm.dev2022@gmail.com",
                new BigDecimal("1000"),
                DocumentTypeEnum.CNPJ,
                "47.216.351/0001-06",
                UserTypeEnum.STORE_USER));
        return userMapper.convertUserEntityListToUserResponseDTOList(userService.findAll());
    }

    @Override
    public BalanceResponseDTO getAccountBalance(String email) {
        log.info("consult account balance : {}", email);
        BalanceResponseDTO balanceResponseDTO = new BalanceResponseDTO();
        UserEntity userEntity = userService.findByEmail(email);

        balanceResponseDTO.setName(userEntity.getName());
        balanceResponseDTO.setBalance(userEntity.getAccountBalance());

        return balanceResponseDTO;
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO, String email) {
        log.info("create new transaction: {}, by user : {}", transactionRequestDTO, email);

        UserEntity userEntity = userService.findByEmail(transactionRequestDTO.getPayeeEmail());
            userEntity.setAccountBalance(transactionRequestDTO.getPaymentValue().add(userEntity.getAccountBalance()));

        TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setPayerEmail(email);
            transactionEntity.setPayeeName(userEntity.getName());
            transactionEntity.setPayeeEmail(transactionRequestDTO.getPayeeEmail());
            transactionEntity.setPaymentValue(transactionRequestDTO.getPaymentValue());
            transactionEntity.setTime(LocalDateTime.now());

        checkAccount(email, transactionRequestDTO.getPaymentValue());

        if (paymentService.validatePayment().getMessage().equals("Autorizado")) {
            userService.updateBalance(userEntity);
            return transactionMapper.convertTransactionEntityToTransactionResponseDTO(transactionService.save(transactionEntity));
        } else {
            throw new TransactionException(TransactionExceptionEnum.UNAUTHORIZED_PAYMENT);
        }
    }

    @Override
    public void deleteAll() {
        userService.deleteAll();
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
