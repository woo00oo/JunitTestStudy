package test.junit.ditest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayPalPaymentService implements PaymentService{

    @Override
    public String payment(String reqData) {
        log.info("페이팔 결제 진행");
        return "페이팔";
    }

    public Payment getPayment() {
        return Payment.PAYPAL;
    }

}
