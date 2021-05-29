package com.pcloud.tinyspringboot.member.repository;

import com.pcloud.tinyspringboot.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Spring이 자동으로 구현체로 만들어주고 Bean으로 등록해준다.
public interface ISpringDataJpaMemberRepository extends JpaRepository<Member, Long>, IMemberRepository {

    // By, And, Or 등의 함수명 및 매개변수를 기준으로 쿼리를 자동으로 생성한다. 페이징도 된다!
    // selete m from Member m where m.id = ? and m.name = ?
    // Optional<Member> findByIdAndName(Long id, String name):

    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
