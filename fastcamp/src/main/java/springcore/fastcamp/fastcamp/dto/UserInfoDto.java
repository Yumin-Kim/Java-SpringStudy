package springcore.fastcamp.fastcamp.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springcore.fastcamp.fastcamp.domin.Birth;
import springcore.fastcamp.fastcamp.domin.House;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class UserInfoDto {
    private String name;
    private String cityCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updateBy;
    private Birth birth;
    private House house;
    private Boolean deleted;

}
