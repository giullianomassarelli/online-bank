package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.clients.OnlinePaymentClient;
import br.com.geradordedevs.onlinebank.dtos.responses.onlinePayment.OnlinePaymentResponseDTO;
import br.com.geradordedevs.onlinebank.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OnlinePaymentClient onlinePaymentClient;
    @Override
    public OnlinePaymentResponseDTO validatePayment() {
        log.info("verify autorization to payment");
        return onlinePaymentClient.verifyPayment();
    }
}
