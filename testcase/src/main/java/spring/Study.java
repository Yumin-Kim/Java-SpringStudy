package spring;


public class Study {

    public Study(Member owner, Long id) {
        this.owner = owner;
        this.id = id;
    }

    public Study() {
    }

    public Study(Long id) {
        this.id = id;
    }

    private Member owner;
    private Long id;
    private StudyStatus studyStatus;
    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getOwner() {
        return owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(StudyStatus studyStatus) {
        this.studyStatus = studyStatus;
    }

    public void open() {
        this.studyStatus = StudyStatus.OPENED;
        System.out.println("Open Study Class");
    }
}
