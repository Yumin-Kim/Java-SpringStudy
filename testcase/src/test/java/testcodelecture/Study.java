package testcodelecture;

public class Study {
    private StudyStatus studyStatus;
    private int limit;

    public Study() {
    }

    public Study(int limit) {
        if(limit < 0 ){
            throw new IllegalArgumentException("limit수가 음수 입니다");
        }
        this.limit = limit;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(StudyStatus studyStatus) {
        this.studyStatus = studyStatus;
    }

}
