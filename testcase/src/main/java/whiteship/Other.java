package whiteship;
public class Other {
    public void pringHello(){
        System.out.println("Hello");
    }
    class AnotherTo{
        void printAnotherToHello(){
            Other.this.pringHello();
        }
    }
    static class Another{
        void printAnotherHello(){
            System.out.println("Hello _ antother");
        }
    }

}
