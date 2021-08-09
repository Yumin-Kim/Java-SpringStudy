package jpacore.jpashop.repository.member;

import jpacore.jpashop.domain.FavoriteItem;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.repository.dto.MemberFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final EntityManager em;

    @Override
    public Optional<MemberFullDto> findMemberInfoFullJoinV1ById(Long userId) {

        List<Member> memberFullJoinResult = em.createQuery("select m from Member m " +
                " join fetch m.favoriteItem" +
                " join fetch m.moneyStorage" +
                " join fetch m.couponMembers" +
                " join m.jobs" +
                " where m.id = :id", Member.class)
                .setParameter("id", userId)
                .getResultList();
        if (memberFullJoinResult.size() == 0) Optional.empty();
        List<Long> itemsIds = memberFullJoinResult.stream()
                .map(member -> {
                            if (member.getFavoriteItem().size() == 0 ) Optional.empty();
                            return member.getFavoriteItem().stream()
                                    .map(FavoriteItem::getItemId)
                                    .collect(toList());
                        }
                )
                .collect(toList()).stream()
                .flatMap(data -> data.stream())
                .collect(Collectors.toSet())
                .stream().collect(Collectors.toList());
        if (itemsIds.size() > 0) {
            List<Item> memberSelectItems = em.createQuery("select i from Item i" +
                    " where i.id in :itemIds", Item.class)
                    .setParameter("itemIds", itemsIds)
                    .getResultList();
            return Optional.of(new MemberFullDto(memberFullJoinResult.get(0), memberSelectItems));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Member> findMemberInfoFullJoinV2ById(Long userId) {
        return Optional.empty();
    }
}
