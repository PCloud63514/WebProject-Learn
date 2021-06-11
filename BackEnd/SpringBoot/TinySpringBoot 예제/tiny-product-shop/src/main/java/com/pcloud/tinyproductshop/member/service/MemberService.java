package com.pcloud.tinyproductshop.member.service;

import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.dto.MemberDto;
import com.pcloud.tinyproductshop.member.repository.IMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final IMemberRepository memberRepository;

    /*회원 가입*/
    @Transactional
    public Long join(Member member) {
        //중복 검증
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    /*회원 전체 조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*회원 한명 조회*/
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id).get();
        member.setName(name);
    }

    private void validateDuplicateMember(Member member) {
        //서버가 여러대 동작하면 동시에 같은 이름을 가진 유저가 가입할 수 있으므로,
        //가급적이면 DB에서 유니크 옵션을 통해 최우선적으로 방지하고 2차검증하는 것이 좋다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
