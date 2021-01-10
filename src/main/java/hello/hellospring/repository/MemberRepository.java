package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    //저장된 모든 회원 리스트를 반환
} //회원 리포지토리 인터페이스
