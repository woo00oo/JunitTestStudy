package test.junit.recacydb;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PayDetailId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Long payNumber;

    @EqualsAndHashCode.Include
    @Id
    private Long paySeq;

    @EqualsAndHashCode.Include
    @Id
    private Long payDetailId;
}
