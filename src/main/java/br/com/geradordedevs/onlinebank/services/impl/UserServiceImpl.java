package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.exceptions.LoginException;
import br.com.geradordedevs.onlinebank.exceptions.UserException;
import br.com.geradordedevs.onlinebank.exceptions.enums.LoginExceptionEnum;
import br.com.geradordedevs.onlinebank.exceptions.enums.UserExceptionEnum;
import br.com.geradordedevs.onlinebank.repositories.TransactionRepository;
import br.com.geradordedevs.onlinebank.repositories.UserRepository;
import br.com.geradordedevs.onlinebank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(UserEntity userEntity) {
        UserEntity cripPasswordUserEntity = new UserEntity();

        cripPasswordUserEntity.setPassword("*********");
        log.info("create new user : {}",cripPasswordUserEntity);

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        log.info("find user by email: {}", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException(UserExceptionEnum.USER_NOT_FOUND));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void verifyUserExist(String email, String documentNumber) {
        log.info("verify if user already exist");
        if (userRepository.findByEmail(email).isPresent() || userRepository.findByDocumentNumbers(documentNumber).isPresent()){
            throw new UserException(UserExceptionEnum.USER_ALREADY_EXIST);
        }
    }

    @Override
    public void updateBalance(UserEntity userEntity) {
        log.info("updante user balance");
        userRepository.save(userEntity);
    }

    @Override
    public void verifyEmailAndPassword(LoginRequestDTO loginRequestDTO) {
        log.info("verify email and password");
        UserEntity userEntity = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new LoginException(LoginExceptionEnum.EMAIL_OR_PASSWORD_INCORRECT));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userEntity.getPassword())){
            throw new LoginException(LoginExceptionEnum.EMAIL_OR_PASSWORD_INCORRECT);
        } else {
            log.info("successfull login, generate token to login");
        }
    }

    @Override
    public void deleteAll() {
        log.info("delete all in db");
        userRepository.deleteAll();
        transactionRepository.deleteAll();
    }

}
