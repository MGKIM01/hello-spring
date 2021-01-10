package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.*;
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("mingyeom");
        //given

        repository.save(member);
        //when


        //then
        Member result = repository.findById(member.getId()).get();
        //new에서 입력한 member와 DB에서 꺼낸 member가 일치하면 참.
        //System.out.println("result = " + (result ==member)); 이렇게 구현할 수도 있지만
        
        // Assertions.assertEquals(result, null);
        assertThat(member).isEqualTo(result);
        //member가 result와 같다. 
        //actual 값에 null을 설정해주면, Member값이 아닌 null값이 들어왔다고 에러
    }

    private JsonPathAssertions assertThat(Member member) {
        return null;
    }
    private JsonPathAssertions assertThat(int member) {
        return null;
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("mingyeom1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("mingyeom2");
        repository.save(member2);
        //given

        Member result = repository.findByName("mingyeom1").get();
        //when

        assertThat(result).isEqualTo(member1);
        //then
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("mingyeom1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("mingyeom2");
        repository.save(member2);
        //given

        List<Member> result2 = repository.findAll();
        //when

        assertThat(result2.size()).isEqualTo(2);
        //then
    }


}
