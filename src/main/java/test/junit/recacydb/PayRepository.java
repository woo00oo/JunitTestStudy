package test.junit.recacydb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, PayId> {
}
