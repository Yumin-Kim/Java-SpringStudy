package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.Team;
import jpastudy.stduy.domain.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("JpaRepository상속 활용하여 테스트")
    void start_1() throws Exception{
        //given
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass());
        Member member = new Member("username");
        Team teamName1 = new Team("teamName1");
        Team teamName2 = new Team("teamName1");
        //when
        Member saveMember = memberRepository.save(member);
        //then
        assertNotNull(saveMember.getUsername());
        assertNotNull(memberRepository);
    }

    @Test
    @DisplayName("JpaRepository의 페이징 메소드 활용")
    void start_2() throws Exception{

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,"username"));
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC,"username"));
        Page<Member> pageMembers = memberRepository.findByAge(10, pageRequest);
        //then
        List<Member> resultPagingMembers = pageMembers.getContent();
        pageMembers.getContent().stream()
                .forEach(member->{
                    System.out.println("member.toString() = " + member.toString());
                });
        System.out.println("pageMembers.getTotalElements() = " + pageMembers.getTotalElements());
        //        assertAll(
//                ()->assertEquals(resultPagingMembers.size(),3),
//                ()->assertTrue(pageMembers.getTotalPages() == 1)
//        );
    }

    @Test
    @DisplayName("Slice interface를 활용하여 Paging 구현\n List 반환하는 Paging 메소드는 limit가 정한 수 만큼만 query를 날리게 된다.")
    void start_3() throws Exception{
        //given
        PageRequest pageRequestInfo = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "username"));
        //when
        Slice<Member> members = memberRepository.findSliceInterfaceByAge(10, pageRequestInfo);
        List<Member> membersList = memberRepository.findListByAge(10, pageRequestInfo);
        //then
        List<Member> resultMembers = members.getContent();
        membersList.stream()
                .forEach(member->{
                    System.out.println("member.getAge() = " + member.getAge());
                    System.out.println("member.getUsername() = " + member.getUsername());
                });

        resultMembers.stream()
                .forEach(resultMember->{
                    System.out.println("member.toString() = " + resultMember.toString());
                });
        System.out.println("pageMembers.getTotalElements() = " + members.getNumberOfElements());
        System.out.println("pageMembers.getNumber() = " + members.getNumber());
        System.out.println("pageMembers.getPageable() = " + members.getPageable());
    }

    @Test
    @DisplayName("Paging시에 Count query 조작하는 방법")
    void start_4() throws Exception{
        //given
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "username"));
        Page<Member> pagingMember = memberRepository.findReturnPageByAge(10, pageRequest);
        //when

        long totalElements = pagingMember.getTotalElements();
        Pageable pageable = pagingMember.getPageable();
        System.out.println("pageable = " + pageable);
        System.out.println("totalElements = " + totalElements);

        pagingMember.getContent().stream()
                .forEach(member->{
                    String username = member.getUsername();
                    System.out.println("username = " + username);
                });

        //then
    }

    @Test
    @DisplayName("페이징 처리 후 dto 반환 하기! >> join fetch ")
    void start_5() throws Exception{
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "username"));
        Page<Member> returnPageByAge = memberRepository.findReturnPageByAge(10, pageRequest);
        //when
        Page<MemberDto> memberDtos = returnPageByAge.map(member -> new MemberDto(member.getId(), member.getUsername(), member.getTeam().getName()));
        //then
        long totalElements = memberDtos.getTotalElements();
        Pageable pageable = memberDtos.getPageable();
        int memberDtosNumberOfElements = memberDtos.getNumberOfElements();

        System.out.println("memberDtosNumberOfElements = " + memberDtosNumberOfElements);
        System.out.println("pageable = " + pageable);
        System.out.println("totalElements = " + totalElements);

    }

}
