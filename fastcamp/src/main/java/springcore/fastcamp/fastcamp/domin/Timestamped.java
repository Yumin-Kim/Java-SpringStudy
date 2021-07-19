package springcore.fastcamp.fastcamp.domin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass // 멤버 변수가 컬럼이 되도록한다.
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public abstract class Timestamped {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
