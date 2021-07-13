package springcore.fastcamp.fastcamp.domin;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    @Id
    @Column(name = "block_id")
    @GeneratedValue
    private Long id;

    private String name;

    private String reason;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToOne(mappedBy = "block",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Person person;
}
