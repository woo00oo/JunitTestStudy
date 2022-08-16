package test.junit.mockito.member;

import test.junit.mockito.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws InvalidMemberException;

    void validate(Long memberId) throws MemberNotFoundException;

}
