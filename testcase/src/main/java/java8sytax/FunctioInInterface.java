package java8sytax;

public interface FunctioInInterface {
    void hello();

    default void printShellScript(String shell) {
        System.out.println(shell);
    }

    static void hell() {
        System.out.println("Hello");
    }
}
