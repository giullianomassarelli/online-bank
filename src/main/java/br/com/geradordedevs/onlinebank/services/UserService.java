package br.com.geradordedevs.onlinebank.services;

import br.com.geradordedevs.onlinebank.entities.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity create (UserEntity userEntity);
    UserEntity findByEmail(String email);
    List<UserEntity> findAll ();
    void verifyUserExist (String email, String documentNumber);
    void updateBalance(UserEntity userEntity);
    void deleteAll();
}
