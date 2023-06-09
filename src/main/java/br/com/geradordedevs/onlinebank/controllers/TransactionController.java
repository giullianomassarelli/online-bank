package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<TransactionResponseDTO>> getAll (@RequestHeader(value = "token") String token){
        return new ResponseEntity<>(transactionFacade.getAll(token), HttpStatus.OK);
    }

    @PostMapping("/send-money")
    @Operation(summary = "create new transaction")
    public ResponseEntity<TransactionResponseDTO> createTransaction (@Valid @RequestBody TransactionRequestDTO transactionRequestDTO,
                                                                     @RequestHeader(value = "token") String token){
        return new ResponseEntity<>(transactionFacade.createTransaction(transactionRequestDTO, token), HttpStatus.ACCEPTED);
    }

}
