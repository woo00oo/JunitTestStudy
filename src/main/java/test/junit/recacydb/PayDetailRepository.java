package test.junit.recacydb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayDetailRepository extends JpaRepository<PayDetail, PayDetailId> {
}
