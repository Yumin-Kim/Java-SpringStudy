package mockito_testcase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.MemberService;
import spring.StudyRepository;
import spring.StudyService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

//Mock 진짜 객체와 비슷하게 동작하지만 프로그래머가 직접 그 객체의 행동을 관리하는 객체
//Mockito : Mock 객체를 쉽게 만들고 관리하고 검증할 수 있는 방법 제공 라이브러리
//협업하는 클래스의 완성 여부에 상관없이 내가 만든 클래스르 테스트 할 수 있고 , 내가 만든 클래스가 연관된 클래스와
//올바르게 협업하는지 확인할 수 있기 떼문이므로
//studbbing : Mock 객체를 통해서 만들어진 가짜 객체를 어떤 행동을 취하도록 조작하는 것을 말한다.
@ExtendWith(MockitoExtension.class)
public class StartMockito {

    @Mock
    StudyRepository studyRepository;

    @Mock
    MemberService memberService;

    @Test
    @DisplayName("스프링을 기반으로 Mock 시작!")
    void start_1(@Mock StudyRepository paramRepo,@Mock MemberService paramSer) throws Exception{
        //given
        StudyRepository mock = mock(StudyRepository.class);
        MemberService mock1 = mock(MemberService.class);
        //when
        StudyService studyService = new StudyService(mock1, mock);
        StudyService annotaionStudyService = new StudyService(memberService,studyRepository);
        StudyService methodParamStudyService = new StudyService(paramSer,paramRepo);
        //then
        assertNotNull(studyService);
        assertNotNull(annotaionStudyService);
        assertNotNull(methodParamStudyService);
    }



}
