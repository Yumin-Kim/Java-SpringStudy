package tutorial.mvc.springMVC.form;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    private String username;

    private Integer age;

    private String eventRegion;

    protected Event(String username, Integer age, String eventRegion) {
        this.username = username;
        this.age = age;
        this.eventRegion = eventRegion;
    }

    public static Event createEvent(String eventRegion, Integer age, String username) {
        return new Event(username, age, eventRegion);
    }
}
