package springcore.fastcamp.fastcamp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springcore.fastcamp.fastcamp.domin.Person;
import springcore.fastcamp.fastcamp.dto.PersonDto;
import springcore.fastcamp.fastcamp.repository.PersonRepository;
import springcore.fastcamp.fastcamp.service.PersonService;

@RestController
@RequestMapping(value = "/api/person")
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable(name = "id") Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person) {
        log.info("person -> {}", personRepository.findAll());
        personService.put(person);
    }

    @PutMapping
    public void modifyOfPerson(@RequestParam(name = "id") Long id, @RequestBody PersonDto personDto) {
        personService.modify(id, personDto);
    }

    @PatchMapping
    public void modifyUserName(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) {

    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }
}
