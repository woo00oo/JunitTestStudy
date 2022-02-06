package test.junit.thejavatest.mockito.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import test.junit.thejavatest.mockito.domain.Member;
import test.junit.thejavatest.mockito.domain.Study;
import test.junit.thejavatest.mockito.domain.StudyStatus;
import test.junit.thejavatest.mockito.member.MemberNotFoundException;
import test.junit.thejavatest.mockito.member.MemberService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    /**
     * 모든 Mock 객체의 행동
     * - Null을 리턴한다. (Optional 타입은 Optional.empty 리턴)
     * - Primitive 타입은 기본 Primitive 값.
     * - 콜렉션은 비어있는 콜렉션.
     * - Void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않음.
     */

    /**
     * Given -> 어떠한 상황이 주어졌을 때
     *
     * When -> 어떠한 행동을 하게 되면
     *
     * Then -> 어떠한 결과가 주어질 것이다.
     */

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService() throws MemberNotFoundException {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("test@test.com");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member)) // 해당 메소드가 처음 호출됐을 때,
                .thenThrow(new RuntimeException()) // 2
                .thenReturn(Optional.empty()); // 3

        Study study = new Study(10, "java");
        when(studyRepository.save(study)).thenReturn(study);

        Study newStudy = studyService.createNewStudy(1L, study);

        assertThat(newStudy).isEqualTo(study);

    }

    @Test
    @DisplayName("Mcok 객체 Stubbing 연습 문제")
    void createNewStudyService() throws MemberNotFoundException {
        //Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("test@email.com");

        Study study = new Study(10, "테스트");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);
        //BDD Mockito
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        //When
        studyService.createNewStudy(1L, study);

        //Then
        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());

        verify(memberService, times(1)).notify(study); //해당 메소드를 실행하였는지, 검증
        verify(memberService, times(1)).notify(member);
        verify(memberService, never()).validate(any());

        //BDD Mockito
        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoMoreInteractions();

        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        //given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더자바, 테스트");
        assertThat(study.getOpenedDateTime()).isNull();
        given(studyRepository.save(study)).willReturn(study);

        //when
        studyService.openStudy(study);

        //then
        assertThat(study.getStatus()).isEqualTo(StudyStatus.OPENED);
        assertThat(study.getOpenedDateTime()).isNotNull();
        //memberService.notify 메소드가 호출 되었는지 확인.
        then(memberService).should().notify(study);

    }

}