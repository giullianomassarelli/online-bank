package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.LoginResponseDTO;
import br.com.geradordedevs.onlinebank.exceptions.LoginException;
import br.com.geradordedevs.onlinebank.exceptions.enums.LoginExceptionEnum;
import br.com.geradordedevs.onlinebank.facades.LoginFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
public class LoginControllerTest {

    private final String LOGIN_ROUTER = "/api/v1/login";
    private final String WRONG_LOGIN_ROUTER = "/api/v1/loginworong";
    private final String VALID_EMAIL= "email@teste.com.br";
    private final String VALID_PASSWORD = "Abcd@1234";
    private final String INVALID_EMAIL= "email";
    private final String INVALID_PASSWORD = "Ab";
    private final String JWT_TOKEN = "token";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginFacade loginFacade;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Before
    public void setupMock ()throws IOException {
        MockitoAnnotations.openMocks(this);
        when(loginFacade.generateToken(returnValidLoginRequestDTO())).thenReturn(returnValidLoginResponseDTO());
        when(loginFacade.generateToken(returnInvalidLoginRequestDTO())).thenThrow(new LoginException(LoginExceptionEnum.EMAIL_OR_PASSWORD_INCORRECT));
    }


    @Test
    public void loginWithValidBodyMustReturnIsAccepted() throws Exception {
        mockMvc.perform(post(LOGIN_ROUTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnValidLoginRequestDTO())))
                .andExpect(status().isAccepted());
    }

    @Test
    public void loginWithInvalidBodyMustReturnForbidden() throws Exception {
        mockMvc.perform(post(LOGIN_ROUTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnInvalidLoginRequestDTO())))
                .andExpect(status().isForbidden());
    }

    @Test
    public void loginWithInvalidRouterMustReturnNotFound() throws Exception {
        mockMvc.perform(post(WRONG_LOGIN_ROUTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnValidLoginRequestDTO())))
                .andExpect(status().isNotFound());
    }

    private LoginRequestDTO returnValidLoginRequestDTO () {
        return new LoginRequestDTO(VALID_EMAIL, VALID_PASSWORD);
    }

    private LoginRequestDTO returnInvalidLoginRequestDTO () {
        return new LoginRequestDTO(INVALID_EMAIL, INVALID_PASSWORD);
    }

    private LoginResponseDTO returnValidLoginResponseDTO () {
        return new LoginResponseDTO(JWT_TOKEN);
    }

}