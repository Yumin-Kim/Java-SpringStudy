package jpacore.jpashop.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "createJob")
@EntityListeners(AuditingEntityListener.class)
public class Job extends BaseEntity {
    private String name;
}
