package jpastudy.stduy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString(of = {"username","teamName"})
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

}
