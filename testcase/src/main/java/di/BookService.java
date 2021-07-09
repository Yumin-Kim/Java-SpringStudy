package di;


public class BookService {
    @Inject
    private BookRepository bookRepository;

    public void login(){
        System.out.println("BookService");
        bookRepository.save("Hello");
    }

}
