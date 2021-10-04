package springException;

public class StepException extends RuntimeException{

    private ErrorEnum errorEnum;

    public StepException(String message, ErrorEnum errorEnum) {
        super(message);
        this.errorEnum = errorEnum;
    }

    public StepException(ErrorEnum errorEnum) {
        super(errorEnum.getStatusMessage());
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }
}
