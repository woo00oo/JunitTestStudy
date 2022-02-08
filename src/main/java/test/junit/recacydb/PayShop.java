package test.junit.recacydb;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@IdClass(PayShopId.class)
@NoArgsConstructor
public class PayShop {

    @Id
    private Long payNumber;

    @Id
    private Long paySeq;

    @Id
    private Long payDetailId;

    @Id
    private String shopNumber;

    private String shopName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pay pay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "payNumber", updatable = false, insertable = false),
            @JoinColumn(name = "paySeq", updatable = false, insertable = false),
            @JoinColumn(name = "payDetailId", updatable = false, insertable = false)
    }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private PayDetail payDetail;

    public PayShop(Long payNumber, Long paySeq, Long payDetailId, String shopNumber, String shopName) {
        this.payNumber = payNumber;
        this.paySeq = paySeq;
        this.payDetailId = payDetailId;
        this.shopNumber = shopNumber;
        this.shopName = shopName;
    }

    public void setPay(Pay pay) {
        if (pay != null) {
            pay.getPayShopList().remove(this);
        }
        this.pay = pay;
        this.pay.getPayShopList().add(this);
    }

    public void setPayDetail(PayDetail payDetail) {
        if (payDetail != null) {
            payDetail.getPayShopList().remove(this);
        }
        this.payDetail = payDetail;
        this.payDetail.getPayShopList().add(this);
    }
}
