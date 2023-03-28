package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.BalanceResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.UserResponseDTO;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import br.com.geradordedevs.onlinebank.facades.UserFacade;
import br.com.geradordedevs.onlinebank.mappers.UserMapper;
import br.com.geradordedevs.onlinebank.services.TransactionService;
import br.com.geradordedevs.onlinebank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Component
@Slf4j
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserMapper userMapper;


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
    public void deleteAll() {
        userService.deleteAll();
    }
}
