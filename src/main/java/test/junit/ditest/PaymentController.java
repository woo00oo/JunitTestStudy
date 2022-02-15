package test.junit.ditest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final ServiceFactory serviceFactory;

    @GetMapping("/payment")
    public String Payment(
            @RequestParam("data") String reqData
    ) {
        PaymentService paymentService = serviceFactory.getPaymentService(reqData);
        log.info("paymentService = {}", paymentService);
        return paymentService.payment(reqData);
    }
}
