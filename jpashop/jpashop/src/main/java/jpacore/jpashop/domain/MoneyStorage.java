package jpacore.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MoneyStorage extends  BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "money_storage_id")
    private Long id;

    private int storagePoint;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "moneyStorage")
    private Member member;

    public void modifyStoragePoint(int storagePoint){
        this.storagePoint = storagePoint;
    }

}
