package mockito_testcase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockStubbingTestCase {
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void create_1(){
        StudyService studyService = new StudyService(memberService, studyRepository);
        Optional<Member> findMember = memberService.findById(1L);
        memberService.vaildation(2L);
    }

    @Test
    @DisplayName("Stubbing to create Member Object")
    void start_2() throws Exception{
        //given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member();
        Study study = new Study(member, 1L);
        member.setEmail("dbals0@naver.com");
        member.setId(1L);
        //when
//        when(memberService.findById(1L))
//                .thenReturn(Optional.of(member));
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member));
        Optional<Member> byId = memberService.findById(1L);
        //then
        /////////////////////////////
        assertEquals("dbals0@naver.com",byId.get().getEmail());
        /////////////////////////////
        studyService.createNewStudy(1L, study);
        assertEquals("dbals0@naver.com",byId.get().getEmail());
    }

    @Test
    void start_3() throws Exception{
        //given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member();
        member.setId(1L);
        member.setEmail("dbals0@naver.com");
        Study study = new Study(1L);
        //when
        doThrow(new IllegalArgumentException()).when(memberService).vaildation(1L);
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());
        when(studyRepository.save(study)).thenReturn(study);
        //then
        assertThrows(IllegalArgumentException.class,()->{
             memberService.vaildation(1L);
        });
        Optional<Member> findMember1 = memberService.findById(1L);
        assertEquals(findMember1.get().getId(), 1L);
        assertThrows(RuntimeException.class,()-> memberService.findById(1L).get());
        assertTrue(memberService.findById(1L).isEmpty() == true );
        Study saveStudy = studyRepository.save(study);
        assertEquals(saveStudy,study);
    }

}
