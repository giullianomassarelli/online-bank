package br.com.geradordedevs.onlinebank.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorObject {

    private final String message;
    private final String field;
    private Object parameter;
}
