package springcore.fastcamp.fastcamp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import springcore.fastcamp.fastcamp.dto.Header;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class HelloController {
    //    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    @GetMapping("/getMethod")
    public String receiveQueryString(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "pwd") int pwd,
            @RequestBody String name
    ) {
        return id + Integer.toString(pwd) + "     " + name;
    }

    @PostMapping("/test")
    public String postMapping(){
        return "Hello";
    }

    @GetMapping("/header")
    public Header getHeader() {
        return Header.builder()
                .resultCode("ok")
                .description("Ok")
                .build();
    }

    @GetMapping("/header/{id}")
    public Header read(
            @PathVariable(name = "id") String name,@RequestBody Test test
    ) {
        Map<String ,Test> map = new HashMap();
        map.put(name, test);
        return Header.builder()
                .resultCode("ok")
                .description("ok")
                .data(map)
                .build();
    }

    @Data
    static class Test {
        private String name;
    }
}
