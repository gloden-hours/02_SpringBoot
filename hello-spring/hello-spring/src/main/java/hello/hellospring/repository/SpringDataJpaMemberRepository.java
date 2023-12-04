package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPOL select m from Member m where m.name = ?
    //메서드 이름만으로 조회가 가능하다
    //페이징 기능도 제공해준다.
    @Override
    Optional<Member> findByName(String name);
}
