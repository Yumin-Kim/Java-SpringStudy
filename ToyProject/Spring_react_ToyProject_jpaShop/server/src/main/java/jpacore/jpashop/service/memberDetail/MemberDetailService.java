package jpacore.jpashop.service.memberDetail;

import jpacore.jpashop.domain.FavoriteItem;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.repository.dto.ItemDto;
import jpacore.jpashop.repository.dto.MemberFullDto;
import jpacore.jpashop.repository.dto.SimilarJobItem;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Service
@RequiredArgsConstructor
public class MemberDetailService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 회원의 모든 정보를 제공하며 기본값은 직업 , 개인 창고 이며 선호 제품의 조건 조회후 다른 문장을 확인할 수 있다
     *
     * @param userId PathVariable
     * @return
     */
    public MemberFullDto selectFullEntity(Long userId) {
        return memberRepository.findMemberInfoFullJoinV1ById(userId).get();
//                .orElse(memberRepository.fin);
    }


    /**
     * TODO 유사제품을 제공하는 API의 난이도 너무 높아 일단 간략하게 마무리 - 유사제품으로 선택할 항목을 정하지 않아 기존 행값으로 구현 해야 하기 때문에 난해한 문제 발생
     * 이름 유사도
     * 다시 API 구현 필요
     *
     * @param userId
     * @return
     */
    public List<ItemDto> selectSimilarFavoriteItem(Long userId) {
        Member favoriteItemsById = memberRepository.findFavoriteItemsById(userId).orElseThrow(()->new IllegalStateException("선호하시는 제품이 없습니다."));
        List<Long> itemIds = favoriteItemsById.getFavoriteItem().stream()
                .map(member -> member.getItemId())
                .collect(toList());
        List<Item> findItems = itemRepository.findItemsByIds(itemIds);
        return findItems.stream().map(ItemDto::new).collect(toList());
    }

    /**
     * TODO 사용자가 지정한 직업 군에 따라 and link query가 동적으로 생성되길 원하지만 아직 까지는 방법을 모르니..
     *
     * @param userId
     * @return
     */
    public List<SimilarJobItem> selectSimilarJobItem(Long userId) {
        //사용자의 직업 확인
        //직업 확인후 사용자와 동일한 직업을 가지고 있는지 확인
        //동일한 직업군을 택한 사용자가 선택한 유사 제품 view로 전달
        Member findMember = memberRepository.findMemberJobById(userId).orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디이 입니다"));
        List<String> getJobsName = findMember.getJobs().stream()
                .map(Job::getName)
                .collect(toList());
        List<Member> selectJoinMemberJobs = memberRepository.findMemberJobByNameIn(getJobsName);
        List<Long> userIds = selectJoinMemberJobs.stream()
                .map(Member::getId)
                .collect(toSet())
                .stream().collect(toList());
        List<Member> jobsFavoriteByIds = memberRepository.findJobsFavoriteByIds(userIds);
        if (jobsFavoriteByIds.size() < 0) {
            throw new IllegalStateException("회원이 선호하는 상품이 없습니다.");
        }
        Set<Set<Long>> favoriteIds = jobsFavoriteByIds.stream()
                .map(findMembers -> {
                    Set<FavoriteItem> favoriteItem = findMembers.getFavoriteItem();
                    ArrayList<FavoriteItem> favoriteItems = newArrayList(favoriteItem);
                    Set<Long> collect1 = favoriteItems.stream()
                            .map(FavoriteItem::getItemId)
                            .collect(toSet());
                    return collect1;
                })
                .collect(toSet());
        HashSet<Long> itemIds = new HashSet<Long>();
        newArrayList(favoriteIds).forEach(longset -> {
            ArrayList<Long> longs1 = newArrayList(longset);
            longs1.stream().forEach(findUserId -> {
                itemIds.add(findUserId);
            });
        });
        List<Item> findfavoriteItemInfo = itemRepository.findItemsByIds(newArrayList(itemIds));
        return findfavoriteItemInfo.stream()
                .map(data -> new SimilarJobItem(data))
                .collect(Collectors.toList());
    }


}
