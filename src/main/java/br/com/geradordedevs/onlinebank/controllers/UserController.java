package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.BalanceResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.api.UserResponseDTO;
import br.com.geradordedevs.onlinebank.facades.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping
    @Operation(summary = "create new user")
    public ResponseEntity<UserResponseDTO> create (@RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userFacade.create(userRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/my-info")
    @Operation(summary = "get user info")
    public ResponseEntity<UserResponseDTO> findByToken (@RequestHeader(value = "token") String token){
        return new ResponseEntity<>(userFacade.findByToken(token), HttpStatus.OK);
    }

    @PostMapping ("/populate")
    @Operation(summary = "populate data base")
    public ResponseEntity<List<UserResponseDTO>> populate (){
        return new ResponseEntity<>(userFacade.populate(), HttpStatus.OK);
    }

    @GetMapping ("/account-balance")
    @Operation(summary = "consulting account balance")
    public ResponseEntity<BalanceResponseDTO> getAccountBalance (@RequestHeader(value = "token") String token){
        return new ResponseEntity<>(userFacade.getAccountBalance(token), HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(summary = "delete all in db")
    public ResponseEntity<Void> eraseDataBase (){
        userFacade.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
