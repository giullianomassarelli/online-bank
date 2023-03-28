package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.exceptions.UserException;
import br.com.geradordedevs.onlinebank.exceptions.enums.UserExceptionEnum;
import br.com.geradordedevs.onlinebank.repositories.UserRepository;
import br.com.geradordedevs.onlinebank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserEntity create(UserEntity userEntity) {
        log.info("create new user : {}",userEntity);
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity, String email) {
        log.info("update user info : {}", userEntity);
        userEntity.setEmail(email);
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

}
