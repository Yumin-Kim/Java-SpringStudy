package spring;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public void Print(Study study){
        Study save = studyRepository.save(study);
        memberService.notify(save);
    }
    public Study openStudy(Study study) {
        study.open();
        Study openedStudy = studyRepository.save(study);
        memberService.notify(openedStudy);
        return openedStudy;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member not found")));
        Study saveStudy = studyRepository.save(study);
        memberService.notify(saveStudy);
        return saveStudy;
    }
}
