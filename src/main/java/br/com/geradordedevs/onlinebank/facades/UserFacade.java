package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;

import java.util.List;

public interface UserFacade {

    UserResponseDTO create (UserRequestDTO userRequestDTO);
    UserResponseDTO findByEmail (String email);
    List<UserResponseDTO> populate ();

}
