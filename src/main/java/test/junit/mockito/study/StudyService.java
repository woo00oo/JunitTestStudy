package test.junit.mockito.study;

import test.junit.mockito.domain.Member;
import test.junit.mockito.domain.Study;
import test.junit.mockito.member.MemberService;

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

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> optionalMember = memberService.findById(memberId);
        study.setOwner(optionalMember
                .orElseThrow(() ->new IllegalArgumentException("Member doesn't exist for id " + memberId))
        );
        return repository.save(study);

    }
}
