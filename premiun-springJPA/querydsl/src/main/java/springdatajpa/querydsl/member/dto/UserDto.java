package springdatajpa.querydsl.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDto {

    private String name;

    @AllArgsConstructor
    @ToString
    public static class UserTeamInfo {

        private int age;
        private List<UserTeamDto> users;

        @ToString
        public static class UserTeamDto {
            private String username;
            private String teamname;

            public UserTeamDto(String key, String name) {
                this.username = key;
                this.teamname = name;
            }
        }
    }
}
