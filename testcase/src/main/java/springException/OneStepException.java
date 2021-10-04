package springException;

public class OneStepException extends StepException{
    public OneStepException(String message, ErrorEnum errorEnum) {
        super(message, errorEnum);
    }

    public OneStepException(String message) {
        super(message,ErrorEnum.ONE);
    }

    public OneStepException() {
        super(ErrorEnum.ONE);
    }


}
