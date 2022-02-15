package test.junit.ditest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServiceFactory {

    private final List<PaymentService> paymentServiceList;

    public PaymentService getPaymentService(String reqData){

        log.info("paymentServiceList = {}", paymentServiceList);
        return paymentServiceList.stream()
                .filter(payment -> payment.getPayment().name().equals(reqData))
                .findAny()
                .orElseThrow(NoSuchElementException::new);


    }
}
