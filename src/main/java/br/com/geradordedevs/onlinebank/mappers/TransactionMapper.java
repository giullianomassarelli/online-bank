package br.com.geradordedevs.onlinebank.mappers;

import br.com.geradordedevs.onlinebank.dtos.responses.api.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
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
