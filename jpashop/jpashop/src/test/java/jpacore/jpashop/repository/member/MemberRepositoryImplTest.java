package jpacore.jpashop.repository.member;

import jpacore.jpashop.dto.UpdateUserInfo;
import jpacore.jpashop.dto.user.DtoMemberInfo;
import jpacore.jpashop.dto.user.DtoUserSearchInfo;
import jpacore.jpashop.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository = new MemberRepositoryImpl();
    @Autowired
    EntityManager em;
    @Autowired
    DummyUserFectory dummyUserFectory = new DummyUserFectory(em,memberRepository);

    @Test
    @DisplayName("쿠폰 등록 하지 않고 조회시 모든 회원 조회")
    @Transactional
    void findAll() {

        Member member1 = dummyUserFectory.setMember("city1", "1", "citycode");
        Member member2 = dummyUserFectory.setMember("city1", "2", "citycode");
        Member member3 = dummyUserFectory.setMember("city1", "3", "citycode");
        Coupon coupon1 = dummyUserFectory.setCoupon("세일1", 20);
        Coupon coupon2 = dummyUserFectory.setCoupon("세일2", 20);
        Coupon coupon3 = dummyUserFectory.setCoupon("세일3", 20);

        CouponMember couponMember = dummyUserFectory.setCouponMember(member1, coupon1, 1, 20);
        //when
        List<Member> members = memberRepository.findAll(0, 10).get();
    }

    @Test
    @DisplayName("쿠폰 등록후 모든 회원조회")
    @Transactional
    @Rollback(value = false)
    public void registerCouponFindAll() throws Exception{
        //given
        Member member1 = dummyUserFectory.setMember("city1", "1", "citycode");
        Member member2 = dummyUserFectory.setMember("city1", "2", "citycode");
        Member member3 = dummyUserFectory.setMember("city1", "3", "citycode");
        Coupon coupon1 = dummyUserFectory.setCoupon("세일1", 20);
        Coupon coupon2 = dummyUserFectory.setCoupon("세일2", 20);
        Coupon coupon3 = dummyUserFectory.setCoupon("세일3", 20);

        CouponMember couponMember = dummyUserFectory.setCouponMember(member1, coupon1, 1, 20);
        //when
        memberRepository.findAll(0, 10);

        //then

    }

    @Test
    @DisplayName("원하는 회원 찾기")
    @Transactional
    @Rollback(value = false)
    void findOne() {
        Member member = dummyUserFectory.setMember("city", "11234", "cityCode");
        Long aLong = memberRepository.findOne(member).get();
        assertThat(aLong).isEqualTo(member.getId());
    }


    @Test
    @DisplayName("회원 정보 부분 수정")
    @Transactional
    @Rollback(value = false)
    void updateUserInfo() {
        Member member = dummyUserFectory.setMember("city1", "1", "citycode");
        UpdateUserInfo updateUserInfo = new UpdateUserInfo();

        updateUserInfo.setCity("city2");
        updateUserInfo.setStreet("street2");

        Member member1 = memberRepository.updateUserInfo(member.getId(), updateUserInfo).get();

        assertThat(member1.getId()).isEqualTo(member.getId());
        assertThat(member1.getAddress().getCity()).isEqualTo("city2");
    }

    @Test
    @DisplayName("여러 회원이 정보 수정시 문제 발생??")
    @Transactional
    @Rollback(value = false)
    public void updateUserAddresInfo() throws Exception{
        //given
        Member member1 = dummyUserFectory.setMember("ciry1","street1","citycode1");
        Member member2 = dummyUserFectory.setMember("ciry2","street2","citycode2");
        //when
        //자료 구조를 활용하여 중복을 입력가능하게
        member1.getFavoritefoods().add("짭뽕");
        member1.getFavoritefoods().add("짜장");
        member1.getFavoritefoods().add("짭뽕");

        Address arrlistAddress = Address.createAddress("Test", "street3", "city2");

        ArrayList<Address> addressList = new ArrayList<>();
        member1.getHistory().add(arrlistAddress);
        arrlistAddress.updateCity("OldCity");
        member2.getHistory().add(arrlistAddress);
//        member1.getAddress().updateCity("Test");
//        Address address = member1.getAddress();
//        address.updateCity("OldCity");
//        member2.updateCity(address.getCity());


        //then
//        assertThat(member1.getAddress().getCity()).isEqualTo("Test");
//        assertThat(member2.getAddress().getCity()).isEqualTo("OldCity");
    }

    @Test
    @DisplayName("회원 수정 전체 수정")
    @Transactional
    @Rollback(value = false)
    public void updateTotalUserInfo() throws Exception{
        //given
        Member member = dummyUserFectory.setMember("city1", "1", "citycode");
        UpdateUserInfo updateUserInfo = new UpdateUserInfo();

        updateUserInfo.setCity("city2");
        updateUserInfo.setStreet("street2");
        updateUserInfo.setCityCode("cityCode2");
        //when
        Member updatemember = memberRepository.updateUserInfo(member.getId(), updateUserInfo).get();
        //then
        assertThat(updatemember.getClass()).isEqualTo(member.getClass());
        assertThat(updatemember.getId()).isEqualTo(member.getId());
        assertThat(updatemember.getAddress().getStreet()).isEqualTo(member.getAddress().getStreet());
    }

    @Test
    @DisplayName("회원 검색기능 >> 동적 쿼리 사용으로 다시 공부해서 입력하기")
    @Transactional
    @Rollback(value = false)
    void searchUserInfo() {
        dummyUserFectory.insertUsers(3);
        DtoUserSearchInfo dtoUserSearchInfo = new DtoUserSearchInfo();
        DtoMemberInfo dtoMemberInfo = new DtoMemberInfo();
        Address city2 = Address.createAddress("city2", "123", "citycode");
        dtoMemberInfo.setAddress(city2);
        dtoUserSearchInfo.setDtoMemberInfo(dtoMemberInfo);
        List<Member> members = memberRepository.searchUserInfo(dtoUserSearchInfo).get();
        assertThat(members.size()).isEqualTo(3);
        members.stream().forEach((v) ->
                assertThat(v.getAddress().getCity()).isEqualTo(city2.getCity())
        );
        System.out.println("members.size() = " + members.size());
    }
}