package springcore.fastcamp.fastcamp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Block;
import springcore.fastcamp.fastcamp.domin.Person;
import springcore.fastcamp.fastcamp.repository.PersonRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;


    @Test
    @Transactional
    void testService() throws Exception{
        //given
        List<Person> peopleExcludeBlocks = personService.getPeopleExcludeBlocks();
        Person person = personService.getPerson(4L);
        //when
        assertThat(peopleExcludeBlocks.size()).isEqualTo(2);
        //then
    }

    @Test
    @Transactional
    void getPeopleByName() throws Exception{
        //given
        givePeople();
        //when
        List<Person> result = personService.getPeopleByName("name1");
        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void getPersonTestCode() throws Exception{
        //given
        //when

        //then
    }

    private void givePeople() {

    }

}