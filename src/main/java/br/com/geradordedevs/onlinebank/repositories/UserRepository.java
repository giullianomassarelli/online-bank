package br.com.geradordedevs.onlinebank.repositories;

import br.com.geradordedevs.onlinebank.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail (String email);
    Optional<UserEntity> findByDocumentNumbers (String documentNumbers);

}
