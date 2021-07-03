package jpacore.jpashop.repository;

import jpacore.jpashop.api.OrderSimpleCApiCotroller;
import jpacore.jpashop.domain.Order;
import jpacore.jpashop.dto.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        return em.createQuery("select o from Order o join o.member m" +
                        "where o.status =:status" +
                        " and m.name like :name"
                , Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }

    /**
     * 동적쿼리를 생성 또는 JPQL을 생성하기 위한 JPA 표준 스팩에 기제 되어 있는 방식으로 일단 해결 향후는 MyBatis 또는 QueryDSL 사용하여 줄이기
     */
    public List<Order> findByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("orderStatus"), orderSearch.getOrderStatus());
            criteria.add(status);
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> orderTypedQuery = em.createQuery(cq).setMaxResults(1000);
        return orderTypedQuery.getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery("select o from Order o " + "join fetch o.member m " + " join fetch o.delivery d ", Order.class).getResultList();
    }

    public List<SimpleOrderDto> findOrderDtos() {
        return em.createQuery(
                "select new jpacore.jpashop.dto.SimpleOrderDto(o.id,m.name,o.orderStatus, d.address, o.orderDate)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleOrderDto.class
        ).getResultList();
    }

    public List<Order> findAllWithItem() {
        return em.createQuery(
                "select distinct o from Order o " +
                        " join fetch o.member m" +
                        " join fetch o.delivery d" +
                        " join fetch o.orderItems oi" +
                        " join fetch oi.item i",
                Order.class).getResultList();
    }


    public List<Order> findAllOrderDtos(int limit, int offset) {
        return em.createQuery(
                "select o from Order o " +
                        "join fetch o.member m " +
                        " join fetch o.delivery d ", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
