package tutorial.study.servlet_nolecture.mvc2.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PersonController {

//    private final PersonValidator personValidator;
    private final ModelMapper modelMapper;
    private final PersonVaildator personVaildator;


    @GetMapping("/person")
    public boolean directlyVaildatePerson(Person person, BindingResult bindingResult) {
        log.info("validate directly {}", person);
//        personValidator.validate(person, bindingResult);
        return !bindingResult.hasErrors();
    }

    @PostMapping("/person/vaild")
    public ResponseEntity crateUser(
            @RequestBody @Valid PersonDto personDto,
            Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        personVaildator.validator(personDto, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Person map = modelMapper.map(personDto, Person.class);
        return null;

    }
}
