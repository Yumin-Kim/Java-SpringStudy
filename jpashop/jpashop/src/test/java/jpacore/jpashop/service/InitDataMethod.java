package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.CouponForm;
import jpacore.jpashop.dto.ItemForm;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.CouponDto;
import jpacore.jpashop.repository.dto.ItemDto;
import jpacore.jpashop.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
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

    private CouponForm createCouponForm() {
        Book saveBook = itemRepository.save(Book.createBook("book1", 100, "company1", "artist1", "etc1"));
        ItemDto itemDto = new ItemDto(saveBook);
        return new CouponForm(itemDto, "쿠폰1", 100, LocalDateTime.now(), LocalDateTime.of(2021, 10, 20, 10, 20));
    }

    private MemberForm makeMemberForm(String name, String nickname, String email, List<String> jobs1){
        return new MemberForm("city", "street", "citycode1", name, nickname, "123", email, false, 10, jobs1);
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

    public void createCoupon(){
        CouponForm couponForm = createCouponForm();
        Item item = itemRepository.findByArtistAndEtc("artist1", "etc1", "book1");
        CouponDto couponDto = couponService.createv2(couponForm, item.getId());
    }

    public void createMember(){
        MemberForm memberForm = makeMemberForm("name", "nickname1", "dbals1@naver.com", Arrays.asList("dev", "hello"));
        MemberForm memberForm2 = makeMemberForm("name", "nickname2", "dbals2@naver.com", Arrays.asList("dev", "hello"));
        MemberForm memberForm3 = makeMemberForm("name", "nickname3", "dbals3@naver.com", Arrays.asList("dev", "hello"));
        //when
        remoteService.signup(memberForm);
        remoteService.signup(memberForm2);
        remoteService.signup(memberForm3);
    }

    public void createItem(){
        ItemForm book = ItemForm.builder()
                .name("item1")
                .itemType("B")
                .company("company1")
                .stockQutity(100)
                .artist("artist")
                .etc("etc1")
                .build();

        ItemForm movie = ItemForm.builder()
                .name("item2")
                .itemType("M")
                .company("company2")
                .stockQutity(100)
                .director("director1")
                .actor("actor1")
                .build();

        ItemForm album = ItemForm.builder()
                .name("item2")
                .itemType("A")
                .company("company2")
                .stockQutity(100)
                .isbn("isbn1")
                .author("author1")
                .build();

        //when
        itemService.create(book);
        itemService.create(movie);
        itemService.create(album);
    }


}
