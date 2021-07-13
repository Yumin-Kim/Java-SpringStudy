package springcore.fastcamp.fastcamp.domin;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Where(clause = "deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private String hobby;

    private String bloodType;

    private LocalDate birthday;

    private String job;

    @Embedded
    private Birth birth;

    @ToString.Exclude
    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;

    public Person(@NonNull String name, String bloodType) {
        this.name = name;
        this.bloodType = bloodType;
    }

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "block_id")
    private Block block;


    public int getAge(){
        return LocalDate.now().getYear() - this.birth.getYearOfBirth() + 1;
    }

    public boolean isBirthDayToday() {
        return LocalDate.now().equals(LocalDate.of(this.birth.getYearOfBirth(), this.birth.getMouthOfBirth(), this.birth.getDayOfBirth()));
    }

}
