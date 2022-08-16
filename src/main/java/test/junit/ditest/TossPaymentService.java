package test.junit.ditest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TossPaymentService implements PaymentService {

    @Override
    public String payment(String reqData) {
        log.info("토스 결제 진행");
        return "토스";
    }

    public Payment getPayment() {
        return Payment.TOSS;
    }

}
