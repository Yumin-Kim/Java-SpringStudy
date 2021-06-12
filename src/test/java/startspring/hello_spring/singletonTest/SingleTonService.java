package startspring.hello_spring.singletonTest;

public class SingleTonService {

    private static final SingleTonService instance = new SingleTonService();

    private SingleTonService(){}


    static SingleTonService getInstance(){
        return instance;
    }

}
