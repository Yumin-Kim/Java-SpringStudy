package whiteship;

public interface Book {
    public void playClass();

    default void defaultPrintBookName() {
        System.out.println("defaultPrintBookName");
    }
}
