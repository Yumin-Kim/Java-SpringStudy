package springcore.fastcamp.fastcamp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Block;
import springcore.fastcamp.fastcamp.domin.Person;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("데이터 삽입")
    @Transactional
    @Rollback(value = false)
    public void givenPeople() {
        Person person1 = givenPersonToBlock();
        Optional<Person> person = personRepository.findById(person1.getId());
        person.ifPresent((data) -> {
            data.setBlock(null);
        });
    }

    @Test
    @DisplayName("query method test")
    void queryMethodTestCode() throws Exception{
        //given
        List<Person> aType = personRepository.findByBloodType("A");
        //when
        assertThat(aType.size()).isEqualTo(0);
        //then
    }

    Block givenBlockPerson(String name) {
        Block hello = Block.builder()
                .name("Hello")
                .build();
        Block save = blockRepository.save(hello);
        return save;
    }

    void givenPerson(String name, String bloodType) {
        personRepository.save(new Person(name, bloodType));
    }

    Person givenPersonToBlock() {
        Person person = new Person("name1", "b");
        Block hello = Block.builder()
                .name("Hello12")
                .build();
        person.setBlock(hello);
        Person save = personRepository.save(person);
        return save;
    }

    @Test
    @DisplayName("deleted false인 값만 가지고 오는지 확인")
    void  findAll_deletedFalse() throws Exception{
        //given
        List<Person> all = personRepository.findAll();
        List<Person> peopleDelete = personRepository.findPeopleDelete();
        //when
        all.forEach(data->data.getName());
        //then
        assertThat(all.size()).isEqualTo(11);
        assertThat(peopleDelete.size()).isEqualTo(1);
    }

}
