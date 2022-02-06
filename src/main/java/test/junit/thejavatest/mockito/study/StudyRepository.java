package test.junit.thejavatest.mockito.study;


import org.springframework.data.jpa.repository.JpaRepository;
import test.junit.thejavatest.mockito.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
