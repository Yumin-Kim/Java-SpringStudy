package springcore.fastcamp.fastcamp.domin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Birth {
    private int yearOfBirth;
    @Min(value = 1)
    @Max(value = 12)
    private int mouthOfBirth;
    @Min(value = 1)
    @Max(value = 30)
    private int dayOfBirth;

    public Birth(LocalDate localDate) {
        this.yearOfBirth = localDate.getYear();
        this.dayOfBirth = localDate.getDayOfMonth();
        this.mouthOfBirth = localDate.getMonthValue();
    }
}
