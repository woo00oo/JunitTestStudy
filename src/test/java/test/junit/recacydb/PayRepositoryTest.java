package test.junit.recacydb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PayRepositoryTest {

    private PayRepository payRepository;

    @Autowired
    public PayRepositoryTest(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    @Test
    @DisplayName("insert_테스트")
    void insert_shop() {
        Long payNumber = 1L;
        Long paySeq = 1L;
        List<PayShop> payShopList = new ArrayList<>();
        payRepository.save(new Pay(payNumber, paySeq, payShopList));
    }

}