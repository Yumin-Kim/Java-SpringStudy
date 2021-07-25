package jpacore.jpashop.repository.member;

import jpacore.jpashop.domain.*;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.member.old.Old_MemberRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DummyUserFectory {

    private final EntityManager em;
    private final Old_MemberRepository oldMemberRepository;


    public DummyUserFectory(EntityManager entityManager , Old_MemberRepository oldMemberRepository) {
        this.em = entityManager;
        this.oldMemberRepository = oldMemberRepository;
    }

    public void insertUsers(int countUser) {
        int[] dummyUsers = new int[countUser];
        Arrays.stream(dummyUsers).forEach((intValue) ->
                {
                    Member member = setMember("city1", Integer.toString(intValue), "citycode");
                    Coupon coupon = setCoupon("세일" + intValue, intValue * 10);
                    setCouponMember(member, coupon, intValue * 2, intValue * 100);
                }
        );
        Arrays.stream(dummyUsers).forEach((intValue) ->
                {
                    Member member = setMember("city2", "123", "citycode");
                    Coupon coupon = setCoupon("세일" + intValue, intValue * 10);
                    setCouponMember(member, coupon, intValue * 2, intValue * 100);
                }
        );
    }

    public Member setMember(String city, String street, String cityCode) {
        Address address = Address.createAddress(city, street, cityCode);
        MemberForm memberForm = new MemberForm("city", "street", "citycode1", "name", "nickname", "123", "dbald0@naer.com", false, 10, Arrays.asList("dev", "hello"));
        List<Job> jobs = memberForm.getJobs().stream()
                .map(Job::createJob).collect(Collectors.toList());
        Member member = Member.createMember(memberForm.getName(), memberForm.getNickname(), memberForm.getPassword(), memberForm.getEmail(), memberForm.getAge(), address,jobs);
        oldMemberRepository.save(member);
        return member;
    }

    public Coupon setCoupon(String name, int salePercent) {
//        Coupon coupone = Coupon.createCoupone(name, salePercent);
//        em.persist(coupone);
        return null;
    }

    public CouponMember setCouponMember(Member member, Coupon coupon, int count, int totalSalcePricce) {
        CouponMember couponeMember = CouponMember.createCouponeMember(count, totalSalcePricce, member, coupon);
        em.persist(couponeMember);
        CouponMember couponMember = em.find(CouponMember.class, couponeMember.getId());
        return couponMember;
    }
}
