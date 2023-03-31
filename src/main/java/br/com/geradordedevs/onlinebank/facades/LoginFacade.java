package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.LoginResponseDTO;

public interface LoginFacade {

    LoginResponseDTO generateToken (LoginRequestDTO loginRequestDTO);
}
