package test.junit.recacydb;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PayShopId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Long payNumber;

    @EqualsAndHashCode.Include
    @Id
    private Long paySeq;

    @EqualsAndHashCode.Include
    @Id
    private Long payDetailId;

    @EqualsAndHashCode.Include
    @Id
    private String shopNumber;
}
