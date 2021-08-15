package kr.co.home.dashboard.domain;

import kr.co.home.dashboard.dto.Req;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false)
@Transactional
class DashboardTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("변경 감지 테스트")
    void start_1() throws Exception{
        //given
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Dashboard dashboard = Dashboard.createDashboard("title", "subTitle", "content", member);
        entityManager.persist(member);
        entityManager.persist(dashboard);

        entityManager.flush();
        entityManager.clear();

        Dashboard dashboard1 = entityManager.find(Dashboard.class, 2L);
        dashboard1.modifyDashboard(new Req.DashboardDto("Hello", "subTItltasdalhflshdlf", null, null), null);

        assertNotEquals(dashboard1.getTitle(), dashboard.getTitle());
    }

}