package springcore.fastcamp.fastcamp.domin;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springcore.fastcamp.fastcamp.dto.UserInfoDto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Builder
@Accessors(chain = true)
@Where(clause ="is_deleted = false")
//@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cityCode;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updateBy;

    @OneToMany(mappedBy = "user")
    private List<Job> jobs = new ArrayList<>();

    @Embedded
    private Birth birth;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id")
    private House house;

    @ColumnDefault("0")
    private boolean isDeleted;

    public void updateUserEntity(UserInfoDto userInfoDto){
        if (userInfoDto.getName() != null){
            this.name = userInfoDto.getName();
        }
        if(userInfoDto.getDeleted()){
            this.isDeleted = userInfoDto.getDeleted();
        }

    }

}
