package junit_testcase;

public class Study {
    private StudyStatus studyStatus;
    private int limit;
    private String name;

    public Study() {
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public Study(int limit) {
        if(limit < 0 ){
            throw new IllegalArgumentException("limit수가 음수 입니다");
        }
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Study{" +
                "limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(StudyStatus studyStatus) {
        this.studyStatus = studyStatus;
    }

}
