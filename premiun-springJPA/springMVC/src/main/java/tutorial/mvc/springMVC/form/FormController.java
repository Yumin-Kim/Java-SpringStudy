package tutorial.mvc.springMVC.form;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tutorial.mvc.springMVC.EventDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/events/form")
@RequiredArgsConstructor
public class FormController {

    private  final EventService eventService;

    @GetMapping
    public String requestToformat(Model model) {
        model.addAttribute("event", new EventDto.EventForm());
        return "events/form";
    }

    @PostMapping("/result")
    @ResponseBody
    public EventDto.ConvertDto createV1(EventDto.EventForm eventForm) {
        return eventService.create(eventForm);
//         new EventDto.ConvertDto(eventForm);
    }

//    @ExceptionHandler({BindException.class})
//    @ResponseBody
//    public String bindExceptionHandler(BindException bindException) {
//        return bindException.getFieldError().getDefaultMessage();
//    }

    /**
     * Todo @Valid는 자바빈 스팩을 준수하는 객체만 검증이 가능합니다.
     *  그래서 @RequestBody를 사용한 객체가 그 스팩을 준수해야 하며,
     *  HttpEntity는 그런 용도가 아니기 때문에 @Valid가 지원하지 못합니다.
     * @param bindException
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String dtoException(BindException bindException){
        return bindException.getFieldError().getDefaultMessage();
    }


    @PostMapping("/get")
    @ResponseBody
    public Object createV2(@Valid @ModelAttribute EventDto.EventFormV2 eventFormV2) {
        return new EventDto.ConvertDto(eventFormV2);
    }
}
