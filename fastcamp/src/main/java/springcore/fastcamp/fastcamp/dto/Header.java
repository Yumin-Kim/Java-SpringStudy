package springcore.fastcamp.fastcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    @JsonProperty("transaction_time")
    private LocalDateTime transactionTime;
    private String resultCode;
    private T data;
    private String description;

    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .data(data)
                .build();
    }

}
