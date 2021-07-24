package jpastudy.stduy.repository.dto;

import jpastudy.stduy.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectinosMemberDto {
    private final String username;
    private final int age;
    private final Team  team;

}
