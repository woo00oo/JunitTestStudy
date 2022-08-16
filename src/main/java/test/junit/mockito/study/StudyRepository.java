package test.junit.mockito.study;

import org.springframework.data.jpa.repository.JpaRepository;
import test.junit.mockito.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
