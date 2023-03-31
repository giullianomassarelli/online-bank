package br.com.geradordedevs.onlinebank.services;

public interface LoginService {

    String generateToken (String email);
    String verifyToken (String token);
}
