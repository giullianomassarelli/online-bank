package br.com.geradordedevs.onlinebank.services;


import br.com.geradordedevs.onlinebank.dtos.responses.onlinePayment.OnlinePaymentResponseDTO;

import java.math.BigDecimal;

public interface PaymentService {

    OnlinePaymentResponseDTO validatePayment ();
}
