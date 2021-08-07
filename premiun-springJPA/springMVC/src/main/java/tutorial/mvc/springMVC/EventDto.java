package tutorial.mvc.springMVC;

import lombok.*;
import tutorial.mvc.springMVC.form.Event;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDto {

    private String name;
    private String eventCode;
    private Integer age;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ConvertDto {
        private EventForm eventForm;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public ConvertDto (EventForm eventForm){
            this.eventForm = eventForm;
            this.createdAt = LocalDateTime.now();
            this.modifiedAt = null;
        }

        public ConvertDto(Event event) {
            this.eventForm = new EventForm(event);
            this.createdAt = LocalDateTime.now();
            this.modifiedAt = null;
        }

        public ConvertDto(EventFormV2 eventFormV2) {
            this.eventForm = new EventForm(eventFormV2.getUsername(), eventFormV2.getAge(), eventFormV2.getEventRegion());
            this.createdAt = LocalDateTime.now();
            this.modifiedAt = LocalDateTime.now();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    public static class EventForm {
        private String username;
        private Integer age;
        private String eventRegion;

        public EventForm(Event event) {
            if (event.getEventRegion() != null) {
                this.eventRegion = event.getEventRegion();
            }
            if (event.getAge() != null) {
                this.age = event.getAge();
            }
            if (event.getUsername() != null) {
                this.username = event.getUsername();
            }
        }

        public Event toEntity() {
            return Event.createEvent(this.eventRegion, this.age, this.username);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    public static class EventFormV2 {
        @NotBlank(message = "입력확인 username")
        private String username;
        @Min(value = 0,message = "0세 이상은 존재 X")
        @Max(value = 100,message = "100세 이상은 존재 X")
        @NotNull(message = "입력 확인 age")
        private Integer age;
        @NotNull(message = "입력 확인 eventRegion")
        private String eventRegion;

    }
}
