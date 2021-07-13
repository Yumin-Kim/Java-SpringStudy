package springcore.fastcamp.fastcamp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Person;

@SpringBootTest
public class TestDB {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void crud(){
        Person person = new Person("name","A");

        personRepository.save(person);
    }
}
