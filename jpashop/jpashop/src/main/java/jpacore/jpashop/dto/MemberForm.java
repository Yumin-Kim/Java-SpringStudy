package jpacore.jpashop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {
    private String city;
    private String street;
    private String citycode;
    private String name;
    private String nickname;
    private String password;
    private String email;
    private boolean privacy;
    private int age;
    private List<String> jobs;
//    private
}
