package tutorial.study.servlet_nolecture.basic.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Getter
@Setter
@NoArgsConstructor
public class Member {

    private Long id;
    private String username;
    private int age;

    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

}
