package springdatajpa.querydsl.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import springdatajpa.querydsl.member.dto.MemberSearchCondition;
import springdatajpa.querydsl.member.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> searchCoditionMember(MemberSearchCondition memberSearchCodition);

    Page<MemberTeamDto> searchCoditionMemberPageCount(MemberSearchCondition memberSearchCodition, Pageable pageable);

    Page<MemberTeamDto> searchCoditionMemberPageCountCompare(MemberSearchCondition memberSearchCodition, Pageable pageable);

    Slice<MemberTeamDto> searchCoditionMemberPageCountSlice(MemberSearchCondition memberSearchCodition, Pageable pageable);

}
