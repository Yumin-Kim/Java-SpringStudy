package tutorial.mvc.springMVC;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello?")
    public String hello1(){
        return "hello";
    }

    @GetMapping("/apple/**")
    public String returnApple() {
        return "apple";
    }

    @GetMapping("/regex/{name:[a-z]*}")
    public String returnRegex(@PathVariable("name") String name) {
        return "regex_test " + name;
    }
}
