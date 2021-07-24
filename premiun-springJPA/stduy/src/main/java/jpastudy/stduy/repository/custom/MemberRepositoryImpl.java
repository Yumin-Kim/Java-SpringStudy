package jpastudy.stduy.repository.custom;

import jpastudy.stduy.domain.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * TODO 확장 기능을 사용하기 위해서는 ...RepositoryImpl이와 같이 클래스를 생성해야지만 동작 하게 된다.(인터페이스 명은 마음대로 )
 */
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findAllCustom() {
        return em.createQuery("select m from Member m ")
                .getResultList();
    }
}
