package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.exception.MemberNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;


    @BeforeEach
    void InitData() {
        Member member = Member.createMember("name", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        memberRepository.save(member);
    }

    @Test
    @DisplayName("memberRepository")
    void start_1() throws Exception {
        //given
        Member member1 = memberRepository.findById(1L).orElseThrow(() -> new MemberNotFoundException());
        member1.updateDeleted();
        assertTrue(member1.getIsDeleted());
        assertEquals(member1.getName(), "name");
    }
}