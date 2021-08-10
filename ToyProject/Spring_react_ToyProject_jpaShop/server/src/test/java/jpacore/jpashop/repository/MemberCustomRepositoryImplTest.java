package jpacore.jpashop.repository;

import jpacore.jpashop.domain.FavoriteItem;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.repository.dto.MemberFullDto;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberCustomRepositoryImplTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("findMemberInfoFullJoinV1ById")
    void start_1() throws Exception{
        //given
        List<Member> singleResult = em.createQuery("select m from Member m " +
                " join fetch m.favoriteItem" +
                " join fetch m.moneyStorage" +
                " join fetch m.couponMembers" +
                " where m.id = :id", Member.class)
                .setParameter("id", 12L)
                .getResultList();
        List<Long> collect = singleResult.stream()
                .map(member ->
                        member.getFavoriteItem().stream()
                                .map(FavoriteItem::getItemId)
                                .collect(toList())
                )
                .collect(toList()).stream()
                .flatMap(data -> data.stream())
                .collect(Collectors.toSet())
                .stream().collect(Collectors.toList());
        System.out.println("singleResult = " + singleResult.size());

        System.out.println("collect = " + collect.size());
        collect.forEach(System.out::println);
        List<Item> itemIds = em.createQuery("select i from Item i" +
                " where i.id in :itemIds", Item.class)
                .setParameter("itemIds", collect)
                .getResultList();
        System.out.println("itemIds.size() = " + itemIds.size());

    }

    @Test
    @DisplayName("Repository_return Dto findMemberInfoFullJoinV1ById")
    void start_2() throws Exception{
        //given
        MemberFullDto memberFullDto = memberRepository.findMemberInfoFullJoinV1ById(24L).get();
        System.out.println("memberFullDto.getFavoriteItems().size() = " + memberFullDto.getFavoriteItems().size());
        System.out.println("memberFullDto.getMyCoupons().size() = " + memberFullDto.getMyCoupons().size());
        System.out.println("memberFullDto.getJobs().size() = " + memberFullDto.getJobs().size());
        em.clear();
        //when
        Member member = memberRepository.findMemberJobStorageMoneyListById(24L).get();
        System.out.println("member.getJobs().size() = " + member.getJobs().size());
        System.out.println("member.getCouponMembers().size() = " + member.getCouponMembers().size());
        System.out.println("member.getJobs().size() = " + member.getFavoriteItem().size());
        member.getFavoriteItem().stream()
                .forEach(favoriteItem -> System.out.println("favoriteItem.getItemId() = " + favoriteItem.getItemId()));
        //then
        member.getJobs().stream()
                .forEach(favoriteItem -> System.out.println(favoriteItem.getName()));
        em.clear();
        Member member1 = memberRepository.findMoneyAndJobById(24L).get();

        System.out.println("member.getJobs().size() = " + member1.getJobs().size());
//        System.out.println("member.getCouponMembers().size() = " + member1.getCouponMembers().size());
        System.out.println("member.getFavoriteItem().size() = " + member1.getFavoriteItem().size());
        em.clear();

        Member member2 = memberRepository.findFavoriteById(24L).get();
        System.out.println("member.getJobs().size() = " + member2.getJobs().size());
        System.out.println("member.getFavoriteItem().size() = " + member2.getFavoriteItem().size());



    }
}