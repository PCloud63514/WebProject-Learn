package com.pcloud.tinyspringboot.member.service;

import com.pcloud.tinyspringboot.member.domain.Member;
import com.pcloud.tinyspringboot.member.repository.IMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//jpa를 쓸꺼면 Transactional 이 필요. 특정함수에만 동작하면 거기에 붙여도 됨
@Transactional
public class MemberService {
    private IMemberRepository memoryMemberRepository;

    public MemberService(IMemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memoryMemberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 한명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memoryMemberRepository.findById(memberId);
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memoryMemberRepository.findAll();
    }

    private void validateDuplicateMember(Member member) {
        memoryMemberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
