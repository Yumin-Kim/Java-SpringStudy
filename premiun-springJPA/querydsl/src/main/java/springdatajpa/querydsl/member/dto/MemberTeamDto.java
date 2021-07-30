package springdatajpa.querydsl.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {

    private Long userId;
    private Long teamId;
    private String username;
    private String teamname;
    private Integer age;

    @QueryProjection
    public MemberTeamDto(Long userId, Long teamId, String username, String teamname,Integer age) {
        this.userId = userId;
        this.teamId = teamId;
        this.username = username;
        this.teamname = teamname;
        this.age = age;
    }
}
