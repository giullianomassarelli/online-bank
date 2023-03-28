package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;

import java.util.List;

public interface TransactionFacade {

    List<TransactionResponseDTO> getAll (String email);
}
