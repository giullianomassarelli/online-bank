package br.com.geradordedevs.onlinebank.mappers;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
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
public class TransactionMapper {

    @Autowired
    private  ModelMapper modelMapper;


    public TransactionResponseDTO convertTransactionEntityToTransactionResponseDTO (TransactionEntity transactionEntity){
        log.info("convert TransactionEntity: {}, to TransactionResponseDTO", transactionEntity);
        return modelMapper.map(transactionEntity, TransactionResponseDTO.class);
    }

    public List<TransactionResponseDTO> convertTransactionEntityListToTransactionResponseDTOList(List<TransactionEntity> transactionEntityList, String email) {
        List<TransactionResponseDTO> list = new ArrayList<>();
        log.info("convert list entity to list response dto");
        for(TransactionEntity transactionEntity : transactionEntityList){
            if (transactionEntity.getPayerEmail().equals(email)) {
                list.add(convertTransactionEntityToTransactionResponseDTO(transactionEntity));
            }
        }
        return list;
    }
}
