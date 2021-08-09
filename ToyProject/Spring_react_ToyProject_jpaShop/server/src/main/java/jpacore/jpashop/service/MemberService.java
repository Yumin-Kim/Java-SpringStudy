package jpacore.jpashop.service;

import jpacore.jpashop.domain.*;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.ItemDto;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

//TODO 개발 비즈니스 요구 사항
// 1. 회원 정보 수정
// 2. 회원 정보 삭제
// 3. 선호 제품 선정
// 4. 선호 제품과 관계되는 상품 정보 전송
// 5. 조회 대상의 직업과 동일한 직업군이 구매한 상품 정보 전송

@Service
@RequiredArgsConstructor
public class MemberService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * @param userId PathVariable
     * @param point  RequestPath
     * @return
     */
    public MemberDto chargeStorage(Long userId, int point) {
        Member member = memberRepository.findStorageById(userId).orElseThrow(getIllegalStateExceptionSupplier("존재하지 않는 아이디 입니다"));
        MoneyStorage moneyStorage = member.getMoneyStorage();
        moneyStorage.modifyStoragePoint(moneyStorage.getStoragePoint() + point);
        return new MemberDto(member, moneyStorage);
    }

    /**
     * @param userId     PathVariable
     * @param memberForm
     * @return
     */
    public MemberDto modify(Long userId, MemberForm memberForm) {
        //멤버 정보 >> job , 장고 , 선호 제품
        Member member = memberRepository.findMoneyAndJobById(userId).orElseThrow(getIllegalStateExceptionSupplier("존재하지 않은 아이디입니다."));
        Address address = Address.createAddress(memberForm.getCity(), memberForm.getStreet(), memberForm.getCityCode());
        member.modifyMemberInfo(member, memberForm, address);
        return new MemberDto(null);
    }

    /**
     * 삭제 보다는 isDeleted를 속성을 활용하여 삭제 처리한다.
     *
     * @param userId 시큐리티를 통해 얻어진 id값
     */
    public void delete(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(getIllegalStateExceptionSupplier("존재하지 않는 아이디입니다"));
        member.updateIsDeleted(true);
    }

    //MemberDto favorite정보 추가
    public MemberDto createFavoriteItem(Long userId, ItemDto... itemDtos) {
        Member member = memberRepository.findById(userId).orElseThrow(getIllegalStateExceptionSupplier("존재하지 않는 아이디입니다"));
        List<Long> itemIdList = Arrays.stream(itemDtos)
                .map(ItemDto::getId)
                .collect(toList());
        List<Item> itemList = itemRepository.findItemsByIds(itemIdList);
        List<FavoriteItem> createFavoriteItems = itemList.stream()
                .map(item -> FavoriteItem.createFavoriteItem(item.getId(), item.getName()))
                .collect(toList());
        member.updateFavoriteItemList(createFavoriteItems);
        return new MemberDto(member);
    }

    private Supplier<IllegalStateException> getIllegalStateExceptionSupplier(String s) {
        return () -> new IllegalStateException(s);
    }

}
