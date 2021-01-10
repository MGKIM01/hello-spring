package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


/* 동시성 문제를 실무에서는 ConcurrentHashMap과 AtomicLong 사용을 고려한다. */
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    // sequence는 key값, 0부터 차례로 증가한다.

    @Override
    public Member save(Member member)
    {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //store에서 id를 꺼내서 반환해준다!
        //store.get(id)가 null일 가능성이 있는 경우에 다음과 같이 return
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //loop
                .filter(member -> member.getName().equals(name))
                // member안의 Name이 name 파라미터와 일치하는지 확인
                .findAny(); //하나라도 찾으면 반환
    }

    public void clearStore()
    {
        store.clear();
    }
}
