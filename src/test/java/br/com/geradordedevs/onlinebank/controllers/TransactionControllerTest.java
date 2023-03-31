package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.LoginResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.exceptions.LoginException;
import br.com.geradordedevs.onlinebank.exceptions.enums.LoginExceptionEnum;
import br.com.geradordedevs.onlinebank.facades.LoginFacade;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@WebMvcTest(value = TransactionController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
public class TransactionControllerTest {

    private final String TRANSACTION_ROUTER = "/api/v1/transaction";
    private final String TRANSACTION_SEND_MONEY_ROUTER = "/api/v1/transaction/send-money";
    private final String WRONG_TRANSACTION_ROUTER = "/api/v1/transactionwrong";
    private final String TOKEN = "AHCBDHBAS";
    private final String TRANSACTION_ID = "1";
    private final String TRANSACTION_PAYER_EMAIL = "test@test.com.br";
    private final String TRANSACTION_PAYEE_NAME = "teste";
    private final String TRANSACTION_PAYEE_EMAIL = "teste@teste.com.br";
    private final BigDecimal TRANSACTION_PAYMENT_VALUE = new BigDecimal(10);
    private final LocalDateTime TRANSACTION_TIME = null;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionFacade transactionFacade;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Before
    public void setupMock ()throws IOException {
        MockitoAnnotations.openMocks(this);
        when(transactionFacade.getAll(TOKEN)).thenReturn(returnListTransactionResponseDTO());
        when(transactionFacade.createTransaction(returnValidTransactionRequestDTO(), TOKEN)).thenReturn(returnTransactionResponseDTO());
    }

    @Test
    public void getAllTransactionsMustReturnIsOk() throws Exception {
        mockMvc.perform(get(TRANSACTION_ROUTER)
                        .header("token", TOKEN))
                        .andExpect(status().isOk());
    }

    @Test
    public void createTransactionWithValidBodyMustReturnAccepted () throws Exception {
        mockMvc.perform(post(TRANSACTION_SEND_MONEY_ROUTER)
                .header("token", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnValidTransactionRequestDTO())))
                .andExpect(status().isAccepted());
    }

    private List<TransactionResponseDTO> returnListTransactionResponseDTO() {
        List<TransactionResponseDTO> list = new ArrayList<>();
        list.add(returnTransactionResponseDTO());
        return list;
    }

    private TransactionResponseDTO returnTransactionResponseDTO() {
        return new TransactionResponseDTO(
                TRANSACTION_ID,
                TRANSACTION_PAYER_EMAIL,
                TRANSACTION_PAYEE_NAME,
                TRANSACTION_PAYEE_EMAIL,
                TRANSACTION_PAYMENT_VALUE,
                TRANSACTION_TIME);
    }

    private TransactionRequestDTO returnValidTransactionRequestDTO (){
        return new TransactionRequestDTO(
                TRANSACTION_PAYEE_EMAIL,
                TRANSACTION_PAYMENT_VALUE);
    }


}