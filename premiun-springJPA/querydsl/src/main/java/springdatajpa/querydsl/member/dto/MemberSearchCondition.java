package springdatajpa.querydsl.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSearchCondition {

    private String username;
    private String teamname;
    private Integer ageGoe;
    private Integer ageLoe;

}
