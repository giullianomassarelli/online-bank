package br.com.geradordedevs.onlinebank.exceptions;

public class OnlineBankException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public OnlineBankException(String message) {
        super(message);
    }

}
