package mockito_testcase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoFindObject {

    @Mock
    private MemberService memberService;

    @Mock
    private StudyRepository studyRepository;

    @Test
    @DisplayName("Mock 객체 확인 메소드 호출 여부 확인 -> (verify)메소드 호출 여부 파악 ")
    void start_1() throws Exception {
        //given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
        //when
        Member member = new Member();
        member.setEmail("dbals0@naver.com");
        member.setId(1L);

        Study study = new Study();
        study.setId(1L);
//        when(memberService.findById(any())).thenReturn(Optional.of(member));
//        when(studyRepository.save(study)).thenReturn(study);

        given(memberService.findById(any())).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        studyService.createNewStudy(1L, study);
        studyService.Print(study);
        //then
        verify(memberService, times(2)).notify(study);
//                () -> verifyNoMoreInteractions(memberService)
        then(memberService).should(times(2)).notify(study);
//                ()->then(memberService).should(times(2)).notify(study)
//                ()->then(memberService).shouldHaveNoMoreInteractions(),
//                ()->then(memberService).should(never()).vaildation(any())
    }
    @Test
    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    void openStudy() throws Exception{
        //given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study();
        study.setId(1L);
        given(studyRepository.save(study)).willReturn(study);
        //when
        studyService.openStudy(study);
        //then
        assertEquals(study.getStudyStatus(),StudyStatus.OPENED);
        then(memberService).should().notify(study);
    }

}
