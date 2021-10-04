package springException;

public enum ErrorEnum {
    ONE(1,"ONE Step"),
    TWO(2,"TWO Step"),
    THREE(3, "THREE Step");

    private Integer status;
    private String statusMessage;

    ErrorEnum(Integer status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
