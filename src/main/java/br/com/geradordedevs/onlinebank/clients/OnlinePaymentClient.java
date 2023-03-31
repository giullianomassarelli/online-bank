package br.com.geradordedevs.onlinebank.clients;

import br.com.geradordedevs.onlinebank.configurations.FeignSimpleEncoderConfig;
import br.com.geradordedevs.onlinebank.dtos.responses.onlinePayment.OnlinePaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "online-payment-api", url = "${openfeign.url.online-payment-api}", configuration = FeignSimpleEncoderConfig.class)
public interface OnlinePaymentClient {

    @GetMapping(value = "/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    OnlinePaymentResponseDTO verifyPayment ();
}
