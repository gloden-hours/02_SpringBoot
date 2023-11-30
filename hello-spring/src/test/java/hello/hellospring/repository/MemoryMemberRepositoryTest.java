package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메소드가 실행이 끝날때마다 실행되는 어노테이션. 콜백
    //실행을 하면 테스트 순서는 랜덤이다.
    //이미 한솔1이 저장이 된 후에 다른 메소드를 테스트 실행하면 오류가 뜸
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    //save 테스트
    @Test
    public void save() {
        Member member = new Member();
        member.setName("한솔");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();  //optional에서 값을 꺼낼 때엔 .get()

        //System.out.println("result = " + (result == member));
        Assertions.assertEquals(member, result);    //Assertions.assertEquals(A, B)
                                                    //A : 기대값 , B : 실제값
                                                    // 값이 서로 다르면 오류가 뜬다
        //Assertions.assertThat(member).isEqualTo(result);  //Assertions.assertThat(A).isEqualTo(B)
                                                            //A : 실제값, B : 기대값
    }

    //findByName 테스트
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("한솔1");
        repository.save(member1);

        Member member2 = new Member();  //shifh + F6 => 해당하는 변수 이름 한번에 수정
        member2.setName("한솔2");
        repository.save(member2);

        Member result =  repository.findByName("한솔1").get();
        assertThat(result).isEqualTo(member1);
    }

    //findAll
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("한솔1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("한솔2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }




}
