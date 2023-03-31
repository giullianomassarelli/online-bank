package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.BalanceResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.UserResponseDTO;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.enums.UserTypeEnum;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import br.com.geradordedevs.onlinebank.facades.UserFacade;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
public class UserControllerTest {

    private final String USER_ROUTER = "/api/v1/users";
    private final String USER_ROUTER_MY_INFO = "/api/v1/users/my-info";
    private final String USER_ROUTER_POPULATE = "/api/v1/users/populate";
    private final String USER_ROUTER_ACCOUNT_BALANCE = "/api/v1/users/account-balance";
    private final String USER_NAME = "teste";
    private final String USER_EMAIL = "email@email.com";
    private final String USER_DOCUMENT_NUMBERS_TO_CPF = "123.123.123-11";
    private final String USER_DOCUMENT_NUMBERS_TO_CNPJ = "11.111.111/1111-11";
    private final DocumentTypeEnum USER_DOCUMENT_TYPE_CPF = DocumentTypeEnum.CPF;
    private final DocumentTypeEnum USER_DOCUMENT_TYPE_CNPJ = DocumentTypeEnum.CNPJ;
    private final String USER_PASSWORD = "ABCD@1234";
    private final BigDecimal USER_BALANCE = new BigDecimal(100);
    private final String TOKEN = "SHASH123123basdnxwqe";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Before
    public void setupMock ()throws IOException {
        MockitoAnnotations.openMocks(this);
        when(userFacade.create(returnCommomUserRequestDTO())).thenReturn(returnCommomUserResponseDTO());
        when(userFacade.create(returnStoreUserRequestDTO())).thenReturn(returnStoreUserResponseDTO());
        when(userFacade.findByToken(TOKEN)).thenReturn(returnCommomUserResponseDTO());
    }

    @Test
    public void createCommomUserWithValidBodyMustReturnCreated () throws Exception {
        mockMvc.perform(post(USER_ROUTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnCommomUserRequestDTO())))
                .andExpect(status().isCreated());
    }

    @Test
    public void createStoreUserWithValidBodyMustReturnCreated () throws Exception {
        mockMvc.perform(post(USER_ROUTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnStoreUserRequestDTO())))
                .andExpect(status().isCreated());
    }

    @Test
    public void findUserByTokenMustReturnOk () throws Exception {
        mockMvc.perform(get(USER_ROUTER_MY_INFO)
                .header("token", TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    public void populateDataBaseMustReturnOk () throws Exception {
        mockMvc.perform(post(USER_ROUTER_POPULATE))
                .andExpect(status().isOk());
    }

    @Test
    public void consultingAccountBalanceMustReturnOk () throws Exception {
        mockMvc.perform(get(USER_ROUTER_ACCOUNT_BALANCE)
                .header("token", TOKEN))
                .andExpect(status().isOk());
    }

    private UserResponseDTO returnCommomUserResponseDTO () {
        return new UserResponseDTO(
                USER_NAME,
                USER_EMAIL,
                USER_DOCUMENT_TYPE_CPF,
                USER_DOCUMENT_NUMBERS_TO_CPF,
                UserTypeEnum.COMMON_USER
        );
    }

    private UserResponseDTO returnStoreUserResponseDTO () {
        return new UserResponseDTO(
                USER_NAME,
                USER_EMAIL,
                USER_DOCUMENT_TYPE_CNPJ,
                USER_DOCUMENT_NUMBERS_TO_CNPJ,
                UserTypeEnum.STORE_USER
        );
    }

    private UserRequestDTO returnCommomUserRequestDTO () {
        return new UserRequestDTO(
                USER_NAME,
                USER_DOCUMENT_TYPE_CPF,
                USER_DOCUMENT_NUMBERS_TO_CPF,
                USER_EMAIL,
                USER_PASSWORD
        );
    }

    private UserRequestDTO returnStoreUserRequestDTO () {
        return new UserRequestDTO(
                USER_NAME,
                USER_DOCUMENT_TYPE_CNPJ,
                USER_DOCUMENT_NUMBERS_TO_CNPJ,
                USER_EMAIL,
                USER_PASSWORD
        );
    }

    private BalanceResponseDTO returnBalanceResponseDTO (){
        return new BalanceResponseDTO(
                USER_NAME,
                USER_BALANCE);
    }

}