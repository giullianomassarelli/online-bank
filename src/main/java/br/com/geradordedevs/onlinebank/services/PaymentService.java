package br.com.geradordedevs.onlinebank.services;


import br.com.geradordedevs.onlinebank.dtos.responses.onlinePayment.OnlinePaymentResponseDTO;

public interface PaymentService {

    OnlinePaymentResponseDTO validatePayment ();
}
