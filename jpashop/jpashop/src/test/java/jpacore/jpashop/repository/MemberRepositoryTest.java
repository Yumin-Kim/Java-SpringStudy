package jpacore.jpashop.repository;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.FavoriteItem;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.member.MemberRepository;
import org.apache.juli.logging.Log;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("회원 정보 CRUD")
    void membeRepo_1() throws Exception {
        //given
        Member member = memberRepository.findFullMemberById(1L).get();
        System.out.println("member = " + member.getJobs());        //when

        //then
    }

    @Test
    @DisplayName("회원 정보 수정 - 동적 쿼리 -> 회원이 수정 하고한 정보")
    void memberRepo_2() throws Exception {
        //given
//        Member member = memberRepository.findFullMemberById(6L).orElseThrow();

        //when

        //then
    }

    @Test
    @DisplayName("회원정보 수정")
    void memRepo_3() throws Exception {
        //given
//        Member member = memberRepository.findById(6L).orElseThrow();
//        Set<Item> favoriteItem = member.getFavoriteItem();
//        favoriteItem.add(itemRepository.findById(1L).get());
//        List resultList = em.createQuery("select m from Member m join fetch m.favoriteItem").getResultList();
//        int size = resultList.size();
//        Member member1 = memberRepository.findFullMemberById(8L).orElseThrow(()->new IllegalStateException("조회한 정보는 존재하지 않습니다"));
//        System.out.println("size = " + size);
//        System.out.println("member1 = " + member1);
        Long userId = 6L;
        String name = "kim";
        String nickname = "nick";
        String email = "dbalsdbals";
        List<String> jobs1 = Arrays.asList("test", "dev", "test1");
//        MemberForm memberForm = new MemberForm(null, "street", "111111citycode", name, nickname, "123", email, false, 10, jobs1, 1000);
        MemberForm memberForm = new MemberForm(null, null, null, null, null, null, null, true, 99, jobs1, null);
        Member member = memberRepository.findMoneyJobById(userId).orElseThrow(() -> new IllegalStateException("존재하지 않은 아이디입니다."));
        Address address = Address.createAddress(memberForm.getCity(), memberForm.getStreet(), memberForm.getCitycode());
        member.modifyMemberInfo(member, memberForm, address);

    }

    @Test
    @DisplayName("모든 회원 정보 >> 직업,장고는 default >> 쿠폰은 선택 사항")
    void start_4() throws Exception {
        //given
        Member member = memberRepository.findMemberFullCouponById(15L)
                .orElse(memberRepository.findMoneyJobById(15L)
                        .orElseThrow(() -> new IllegalStateException("회원의 정보가 존재하지 않습니다.")));
        //when
        System.out.println("member.toString() = " + member.toString());
        //then
    }

    @Test
    @DisplayName("선호 제품 추가")
    void start_5() throws Exception {
        //given
        //when
        List<Item> itemList = itemRepository.findItemsByIds(Arrays.asList(1L, 3L));
        List<FavoriteItem> fL = itemList.stream()
                .map(item -> FavoriteItem.createFavoriteItem(item.getId(), item.getName()))
                .collect(toList());
        Member member = memberRepository.findById(4L).get();
        member.getFavoriteItem().addAll(fL);
        //then
    }

    @Test
    @DisplayName("선호 제품 조회")
    void start_6() throws Exception {
        //given
        PageRequest of = PageRequest.of(0, 4);
        List<Item> all = itemRepository.findAll(of).getContent();
//        memberRepository.findByFavoriteItem(all);
        //when
        //then
    }

    @Test
    @DisplayName("사용자와 동일한 직업군이 선택한 제품 확인")
    void start_7() throws Exception {
        //given
        //사용자의 직업 확인
        //직업 확인후 사용자와 동일한 직업을 가지고 있는지 확인
        //동일한 직업군을 택한 사용자가 선택한 유사 제품 view로 전달
        Member findMember = memberRepository.findMemberJobById(6L).orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디이 입니다"));
        List<String> getJobsName = findMember.getJobs().stream()
                .map(Job::getName)
                .collect(toList());

        List<Member> selectJoinMemberJobs = memberRepository.findMemberJobByNameIn(getJobsName);
        System.out.println("selectJoinMemberJobs.size() = " + selectJoinMemberJobs.size());
        List<Long> userIds = selectJoinMemberJobs.stream()
                .map(Member::getId)
                .collect(Collectors.toSet())
                .stream().collect(Collectors.toList());
        List<Member> jobsFavoriteByIds = memberRepository.findJobsFavoriteByIds(userIds);
        if (jobsFavoriteByIds.size() > 0) {
            Set<Set<Long>> favoriteIds = jobsFavoriteByIds.stream()
                    .map(findMembers -> {
                        Set<FavoriteItem> favoriteItem = findMembers.getFavoriteItem();
                        ArrayList<FavoriteItem> favoriteItems = Lists.newArrayList(favoriteItem);
                        Set<Long> collect1 = favoriteItems.stream()
                                .map(FavoriteItem::getItemId)
                                .collect(toSet());
                        return collect1;
                    })
                    .collect(toSet());
            HashSet<Long> itemIds = new HashSet<Long>();
            Lists.newArrayList(favoriteIds).forEach(longset->{
                ArrayList<Long> longs1 = Lists.newArrayList(longset);
                longs1.stream().forEach(userId->{
                    itemIds.add(userId);
                });
            });
            List<Item> findFavoriteItem = itemRepository.findItemsByIds(Lists.newArrayList(itemIds));

        } else {
            throw new IllegalStateException("선택항목이 없습니다");
        }
        //when
        //then
    }

}
