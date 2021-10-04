package springException;

public class EnumTestMain {

    public static void main(String[] args) {
        System.out.println("ErrorEnum.ONE = " + ErrorEnum.ONE);
        System.out.println("ErrorEnum.ONE.getStatusMessage() = " + ErrorEnum.ONE.getStatusMessage());
        throw new OneStepException();
    }

}
