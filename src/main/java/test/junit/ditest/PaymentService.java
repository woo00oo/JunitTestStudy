package test.junit.ditest;

import org.springframework.stereotype.Service;


@Service
public interface PaymentService {

    String payment(String reqData);

    Payment getPayment();

}
