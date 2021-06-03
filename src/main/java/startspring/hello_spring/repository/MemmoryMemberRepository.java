package startspring.hello_spring.repository;

import startspring.hello_spring.domain.Member;

import java.util.*;

public class MemmoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제로 인해 concurrentHashMap사용 https://pplenty.tistory.com/17 해당 포스팅 참고
    private static long sequence = 0L; // 시스템상에서 지정해주는 키 값이라고 생각 동시성 문제로 atomLong사용 https://codechacha.com/ko/java-atomic-long/

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //store.get(id)와 같은 방식으로 진행하면 null로 반환 해줄 가능성이 있기 때문에 Optional.ofNullAble()을 통해서 null로 반환하여도 클라이언트에서 부가적인 동작을 할 수 있도록 진행
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 자바 자료 구조 공부 !! 데이터를 가공하기 위해 필요 TypeScript 생각하면서 공부!StreamAPI가장 용이함
        //Optionnal 사용하는 방법
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearInstanceTest(){
        store.clear();
    }

}
