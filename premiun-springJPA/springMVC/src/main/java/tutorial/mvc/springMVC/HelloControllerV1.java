package tutorial.mvc.springMVC;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerV1 {

    @GetMapping(
            value = "/getv1",
            consumes = MediaType.APPLICATION_JSON_VALUE
//    produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String returnJsonMediaType(){
        return "Hello";
    }

    @RequestMapping(value = "/head")
    public String headMethod(){
        return "Head Method";
    }

}
