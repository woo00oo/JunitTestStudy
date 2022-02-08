package test.junit.recacydb;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@IdClass(PayId.class)
@Entity
@NoArgsConstructor
public class Pay {

    @Id
    private Long payNumber;

    @Id
    private Long paySeq;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pay")
    private List<PayShop> payShopList = new ArrayList<>();

    public Pay(Long payNumber, Long paySeq, List<PayShop> payShopList) {
        this.payNumber = payNumber;
        this.paySeq = paySeq;
        this.payShopList = payShopList;
    }
}
