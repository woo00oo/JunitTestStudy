package test.junit.thejavatest.mockito.member;

import test.junit.thejavatest.mockito.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

    void validate(Long memberId);
}
