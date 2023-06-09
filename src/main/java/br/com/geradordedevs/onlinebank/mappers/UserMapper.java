package br.com.geradordedevs.onlinebank.mappers;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.UserResponseDTO;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserMapper {

    @Autowired
    private  ModelMapper modelMapper;

    public UserEntity convertUserRequestDTOTOUserEntity (UserRequestDTO userRequestDTO){
        UserRequestDTO cripPasswordUserRequestDTO = new UserRequestDTO();

        cripPasswordUserRequestDTO.setPassword("**********");
        log.info("convert UserRequestDTO : {}, to UserEntity", cripPasswordUserRequestDTO);

        UserEntity userEntity = modelMapper.map(userRequestDTO, UserEntity.class);
        switch (userEntity.getDocumentType()){
            case CPF -> userEntity.setUserType(UserTypeEnum.COMMON_USER);
            case CNPJ -> userEntity.setUserType(UserTypeEnum.STORE_USER);
        }
        return userEntity;
    }

    public UserResponseDTO convertUserEntityToUserResponseDTO (UserEntity userEntity){
        UserEntity cripPasswordUserEntity = new UserEntity();

        cripPasswordUserEntity.setPassword("**********");
        log.info("convert UserEntity: {}, to UserResponseDTO", cripPasswordUserEntity);

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    public List<UserResponseDTO> convertUserEntityListToUserResponseDTOList(List<UserEntity> userEntityList) {
        List<UserResponseDTO> list = new ArrayList<>();
        for(UserEntity userEntity : userEntityList){
            list.add(convertUserEntityToUserResponseDTO(userEntity));
        }
        return list;
    }
}
