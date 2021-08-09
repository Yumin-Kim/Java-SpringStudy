package jpacore.jpashop.test;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultBookService  implements BookService{

    @Autowired
    private BookRepository bookRepository;


    public Books save(Books books) {
        return bookRepository.save(books);
    }

    @Override
    public void rent() {
        System.out.println("BookService Default implements Service");
    }

    @Override
    public void rentToUser() {
        System.out.println("BookService Default Implements Service rentToUser Method");
    }
}
