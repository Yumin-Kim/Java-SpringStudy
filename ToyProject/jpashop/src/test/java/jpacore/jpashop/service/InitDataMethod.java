package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.CouponDto;
import jpacore.jpashop.dto.ItemForm;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.ItemDto;
import jpacore.jpashop.repository.item.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class InitDataMethod {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    CouponService couponService;

    @Autowired
    RemoteService remoteService;

    @Autowired
    ItemService itemService;

    @Test
    @DisplayName("데이터 넣어요!!")
    void create_1() throws Exception {
        //given
        createMember();
//        createItem();
//        createCoupon();
        //when

        //then
    }

    private List<CouponDto> createCouponForm() {
        PageRequest of = PageRequest.of(0, 100, Sort.by(Sort.Direction.ASC, "id"));
        Page<Item> items = itemRepository.findAll(of);
        List<ItemDto> collect = items.getContent().stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
        return collect.stream()
                .map(itemDto -> new CouponDto(itemDto, "쿠폰1", 100, LocalDateTime.now(), LocalDateTime.of(2021, 10, 20, 10, 20)))
                .collect(Collectors.toList());
    }

    private MemberForm makeMemberForm(String name, String nickname, String email, List<String> jobs1) {
        return new MemberForm("city", "street", "citycode1", name, nickname, "123", email, false, 10, jobs1, 1000);
    }

    private Member makeMemberInfo(MemberForm memberForm) {
        Address address = Address.of(memberForm.getCity(), memberForm.getStreet(), memberForm.getCitycode());
        List<Job> jobs = memberForm.getJobs().stream()
                .map(Job::createJob).collect(Collectors.toList());
        Member member = Member.createMember(
                memberForm.getName(),
                memberForm.getNickname(),
                memberForm.getPassword(),
                memberForm.getEmail(),
                memberForm.getAge(),
                address,
                jobs
        );
        return member;
    }

    public void createCoupon() {
        Map<Long, CouponDto> collect = createCouponForm().stream()
                .collect(Collectors.toMap(couponForm -> couponForm.getItemDto().getId(), couponForm -> couponForm));
        collect.entrySet().stream()
                .forEach(data ->
                        couponService.createv2(data.getValue(), data.getKey())
                );
//        List<Long> longs = couponForm.stream().map(couponForm1 -> couponForm1.getItemDto().getId()).collect(Collectors.toList());
    }

    public void createMember() {

        List<MemberForm> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MemberForm memberForm = makeMemberForm("name" + i, "nicknameV1" + i, "dbalsV1" + i + "@naver.com", Arrays.asList("dev" + i, "hello" + i,"dev1"+i));
            list.add(memberForm);
        }
        //when
        list.stream()
                .forEach(memberForm -> remoteService.signup(memberForm));
    }

    public void createItem() {
        List<ItemForm> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ItemForm book = ItemForm.builder()
                    .name("item1" + i)
                    .itemType("B")
                    .company("company1" + i)
                    .stockQutity(100)
                    .artist("artist" + i)
                    .etc("etc1" + i)
                    .build();

            ItemForm movie = ItemForm.builder()
                    .name("item2" + i)
                    .itemType("M")
                    .company("company2" + i)
                    .stockQutity(100)
                    .director("director1" + i)
                    .actor("actor1" + i)
                    .build();

            ItemForm album = ItemForm.builder()
                    .name("item3" + i)
                    .itemType("A" + i)
                    .company("company3" + i)
                    .stockQutity(100)
                    .isbn("isbn1" + i)
                    .author("author1" + i)
                    .build();

            list.add(book);
            list.add(movie);
            list.add(album);
        }

        //when
        list.stream()
                .forEach(itemForm -> itemService.create(itemForm));

    }


}
