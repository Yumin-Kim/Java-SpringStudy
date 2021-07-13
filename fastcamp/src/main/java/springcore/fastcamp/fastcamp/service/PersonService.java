package springcore.fastcamp.fastcamp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Person;
import springcore.fastcamp.fastcamp.dto.PersonDto;
import springcore.fastcamp.fastcamp.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public List<Person> getPeopleExcludeBlocks(){
//        List<Person> peopleFindAll = personRepository.findAll();
//        return peopleFindAll.stream()
//                .filter(person -> person.getBlock() == null)
//                .collect(toList());
        return personRepository.findByBlockIsNull();
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        Optional<Person> byId = personRepository.findById(id);
        Person person =  byId.orElse(null);
        log.info("person:{}" + person);
        return person;
    }

    public List<Person> getPeopleByName(String name) {
//        List<Person> resultPeople = personRepository.findAll();
//        return resultPeople.stream()
//                .filter(person -> person.getName().equals(name))
//                .collect(toList());
        List<Person> byName = personRepository.findByName(name);
        return byName;
    }

    @Transactional
    public void put(Person person) {
    }

    @Transactional
    public void modify(Long id, PersonDto person) {
        Person personFind = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디 존재 X"));
        if (!personFind.getName().equals(personFind.getName())) {
            throw new RuntimeException("이름이 다릅니다");
        }
        personFind.setPhoneNumber(person.getPhoneNumber());
    }

    @Transactional
    public void delete(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));
        person.setDeleted(true);
        personRepository.save(person);
        //        personRepository.delete(person);

    }
}
