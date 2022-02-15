package test.junit.ditest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceFactoryTest {

    @Autowired
    private ServiceFactory serviceFactory;

    @Test
    @DisplayName("Toss 의존성 주입 테스트")
    void getPaymentService() {

        //given
        String data = "TOSS";

        //when
        PaymentService paymentService = serviceFactory.getPaymentService(data);

        //then
        assertThat(paymentService).isInstanceOf(TossPaymentService.class);
    }

}