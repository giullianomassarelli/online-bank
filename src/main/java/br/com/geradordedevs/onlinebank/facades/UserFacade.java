package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.BalanceResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.UserResponseDTO;

import java.util.List;

public interface UserFacade {

    UserResponseDTO create (UserRequestDTO userRequestDTO);
    UserResponseDTO findByToken (String token);
    List<UserResponseDTO> populate ();
    BalanceResponseDTO getAccountBalance(String token);
    void deleteAll();
}
