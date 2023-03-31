package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.TransactionResponseDTO;

import java.util.List;

public interface TransactionFacade {

    List<TransactionResponseDTO> getAll (String token);
    TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO, String token);
}
