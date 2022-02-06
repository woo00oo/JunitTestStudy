package test.junit.thejavatest.mockito.member;

import test.junit.thejavatest.mockito.domain.Member;
import test.junit.thejavatest.mockito.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}
