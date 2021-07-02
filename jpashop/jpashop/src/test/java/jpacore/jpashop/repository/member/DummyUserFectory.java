package jpacore.jpashop.repository.member;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Coupon;
import jpacore.jpashop.domain.CouponMember;
import jpacore.jpashop.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Arrays;
@Repository
public class DummyUserFectory {

    private final EntityManager em;
    private final MemberRepository memberRepository;


    public DummyUserFectory(EntityManager entityManager , MemberRepository memberRepository) {
        this.em = entityManager;
        this.memberRepository = memberRepository;
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
        Member member = Member.createMember(address);
        memberRepository.save(member);
        return member;
    }

    public Coupon setCoupon(String name, int salePercent) {
        Coupon coupone = Coupon.createCoupone(name, salePercent);
        em.persist(coupone);
        return coupone;
    }

    public CouponMember setCouponMember(Member member, Coupon coupon, int count, int totalSalcePricce) {
        CouponMember couponeMember = CouponMember.createCouponeMember(count, totalSalcePricce, member, coupon);
        em.persist(couponeMember);
        CouponMember couponMember = em.find(CouponMember.class, couponeMember.getId());
        return couponMember;
    }
}