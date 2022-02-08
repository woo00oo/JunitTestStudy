package test.junit.recacydb;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@IdClass(PayDetailId.class)
@NoArgsConstructor
public class PayDetail {

    @Id
    private Long payNumber;

    @Id
    private Long paySeq;

    @Id
    private Long payDetailId;

    @OneToMany(mappedBy = "payDetail", fetch = FetchType.LAZY)
    private List<PayShop> payShopList = new ArrayList<>();

    public PayDetail(Long payNumber, Long paySeq, Long payDetailId) {
        this.payNumber = payNumber;
        this.paySeq = paySeq;
        this.payDetailId = payDetailId;
    }
}
