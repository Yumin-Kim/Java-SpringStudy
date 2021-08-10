package kr.co.home.dashboard.dto;

import kr.co.home.dashboard.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class Res<T> {

    private String httpStatusCode;
    private T data;

    public static <T> Res<T> isOk(T data) {
        return (Res<T>) Res.builder()
                .httpStatusCode(HttpStatus.OK.toString())
                .data(data)
                .build();
    }

    @Getter
    @Setter
    public static class MemberDto {
        private String name;
        private int age;
        private String email;
        private Address address;



    }


}
