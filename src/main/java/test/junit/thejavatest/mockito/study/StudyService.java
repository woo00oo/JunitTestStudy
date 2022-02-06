package test.junit.thejavatest.mockito.study;

import test.junit.thejavatest.mockito.domain.Member;
import test.junit.thejavatest.mockito.domain.Study;
import test.junit.thejavatest.mockito.member.MemberNotFoundException;
import test.junit.thejavatest.mockito.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) throws MemberNotFoundException {
        Optional<Member> member = memberService.findById(memberId);

        study.setOwner(member.orElseThrow(
                () -> new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'"))
        );
        return repository.save(study);
    }
}
