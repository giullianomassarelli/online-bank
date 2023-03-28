package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.BalanceResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import br.com.geradordedevs.onlinebank.facades.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @GetMapping
    @Operation(summary = "get all transaction by email")
    public ResponseEntity<List<TransactionResponseDTO>> getAll (@RequestHeader(value = "email") String email){
        return new ResponseEntity<>(transactionFacade.getAll(email), HttpStatus.OK);
    }

}
