package br.com.geradordedevs.onlinebank.facades.impl;


import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.LoginResponseDTO;
import br.com.geradordedevs.onlinebank.facades.LoginFacade;
import br.com.geradordedevs.onlinebank.services.LoginService;
import br.com.geradordedevs.onlinebank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginFacadeImpl implements LoginFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Override
    public LoginResponseDTO generateToken(LoginRequestDTO loginRequestDTO) {
        userService.verifyEmailAndPassword(loginRequestDTO);
        return new LoginResponseDTO(loginService.generateToken(loginRequestDTO.getEmail()));
    }
}
