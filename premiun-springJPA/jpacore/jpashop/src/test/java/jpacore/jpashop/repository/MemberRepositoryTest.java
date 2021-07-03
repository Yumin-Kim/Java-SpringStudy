package jpacore.jpashop.repository;

import jpacore.jpashop.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 해당 테스트는 일대일 관계에서 주인key를 가지고 있지 않은 테이블에서 주인 테이블을 조인하여 찾는 시나리오를 생각하고 작성하게 되었다.
 * Order <OneToOne> Delivery 관계를 이루고 있다.
 * 기존 테이블은 지연 로딩으로 세팅이 되어 있지만 아래와 같은 문제가 발생한다.
 * Order에서 Delivery 정보를 가지고올떄는 select query가 한번 발생하며 지연 로딩이 무시되고 즉시 로딩으로 동작을 하게 된다.
 * Delivery에서 Order를 조회 하는 방식으로 접근하였는데
 *
 * JPQL를 사용하여 fetch join을 사용하지 않는 경우
 * select query가 두번 실행되게 된다. >> 현재로써는 가볍게 생각할 수 있지만 향후에 다양한 테이블과 일대일 관게를 이루는 구조를 접할수 있게 되는데
 * 그 상황에서는 성능문제를 발생할 수 있다고 생각한다.
 * 첫번째 select query에서는 delivery id를 찾는 query
 * 두번째 select query에서는 첫번째 query에서 delivery id 값을 통해 Order 테이블을 동일한 아이디 값을 찾는 query가 실행되게 된다.
 *
 * select fetch join을 사용하게 된다면
 * 위와 같은 상황이지만 select query가 한번만 실행되게 된다.
 * 정확하게는 어떻게 동작하는지는 아직 모르겠지만 성능 최적화할시는 적합할거 같다.
 *
 * 다양한 포스팅 글을 확인해본결과
 * JPA hibernate 프록시 객체는 연관 관계를 지어야만 동작을 할 수 있다고 하는 글도 보았고
 * 애초에 일대일 관계에서는 주인 테이블을 통해 하위 테이블을 조회화는 경우가 빈번하지 하위 테이블을 통해 주인 테이블을 조회하는 경우가 거의 드물고
 * 생각을 조금하게 되면 Delivery에거 Order정보를 확인하기 위해서는 Delivery에는 Order관련된 정보가 존재하지 않기 때문에 Order테이블을 무조건 확인해야하는것도 맞다.
 * 어차피 쿼리를 사용해야한다는 뜻이며 굳이 프록시 객체를 활용할 이유가 없고 hibernate도 이와 똑같이 생각하여 지연로딩으로 세팅해도 그것이 작동하지 않는것로 글들은 설멸해 놓은것을 보게 되었다.
 *
 * 프록시 객체를 사용하게되는 이유를 정확히 알게 되면 이 예제는 이행가 쉬울것이라고 생각한다.
 */
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private EntityManager entityManager;

    @BeforeAll
    @DisplayName("일대일 관계 - 프록시 객체 한계를 확인 하기 위한 테스트 입니다")
    static void StartOneToOneTest(){
        System.out.println("Start OneToOne Test");
    }

    @Test
    @DisplayName("Orders >> Delivery")
    @Transactional
    public void OnoToOneProxyLimit() throws Exception {
        //given
        Order one = orderRepository.findOne(7L);
        //when
//        String name = one.getMember().getName();
        Long id = one.getDelivery().getId();
        //then
//        System.out.println("Member.name = " + name);
        Assertions.assertThat(id).isEqualTo(8L);
    }

    @Test
    @DisplayName("Delivery >> Order fetch join을 활용하여 진행")
    @Transactional
    public void FetchJoinSelectDelivery() throws Exception{
        //given
        List<Delivery> findAllDeliveryInfos = entityManager.createQuery("select d from Delivery d join fetch d.order where d.id =:orderId", Delivery.class)
                .setParameter("orderId", 8L)
                .getResultList();
        //when
        List<OrderStatus> collect = findAllDeliveryInfos.stream()
                .map((findAllInfo) -> findAllInfo.getOrder().getOrderStatus())
                .collect(Collectors.toList());
        //then
        Assertions.assertThat(findAllDeliveryInfos.size()).isEqualTo(1);
        Assertions.assertThat(collect.size()).isEqualTo(1);
        Assertions.assertThat(collect.get(collect.size()-1)).isEqualTo(OrderStatus.ORDER);

    }

    @Test
    @DisplayName("Delivery >> Order fetch join 활용하지 않고")
    @Transactional
    void NotFetchJoinSelectDelivery() throws Exception{
        //given
        Delivery delivery = entityManager.find(Delivery.class, 8L); // Delivery테이블 select query 발생
        //when
        OrderStatus orderStatus = delivery.getOrder().getOrderStatus(); // Order 테이블을 select query 발생
        //then
        Assertions.assertThat(orderStatus).isEqualTo(OrderStatus.ORDER);
    }


}