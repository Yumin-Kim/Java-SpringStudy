package thymeleaf.thymeleafdemo.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescapted(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {

        User username1 = new User("username1", 12);
        User username2 = new User("username2", 12);

        List<User> users = new ArrayList<>();
        users.add(username1);
        users.add(username2);

        Map<String,User> userMap = new HashMap<>();

        userMap.put(username1.getUsername(), username1);
        userMap.put(username2.getUsername(), username2);

        model.addAttribute("user", username1);
        model.addAttribute("users", users);
        model.addAttribute("userMap", userMap);
        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData","Hello Session");
        session.setAttribute("sessionV1","Hello sessionV1");
        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring !");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(Model model) {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("username", 12));
        addUsers(model);
        return "basic/javascript";
    }

    private void addUsers(Model model) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("username1", 10));
        users.add(new User("username2", 20));
        users.add(new User("username3", 30));
        model.addAttribute("users", users);
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "hello" + data;
        }
    }

    @Getter
    @Setter
    @ToString
    private static class User {

        private String username;
        private Integer age;

        public User(String username, Integer age) {
            this.username = username;
            this.age = age;
        }
    }
}
