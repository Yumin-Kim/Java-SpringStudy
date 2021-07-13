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
    private int mouthOfBirth;
    private int dayOfBirth;

    public Birth(LocalDate localDate) {
        this.yearOfBirth = localDate.getYear();
        this.dayOfBirth = localDate.getDayOfMonth();
        this.mouthOfBirth = localDate.getMonthValue();
    }



    public static Birth of(LocalDate birthday) {
        return new Birth(birthday);
    }

}
