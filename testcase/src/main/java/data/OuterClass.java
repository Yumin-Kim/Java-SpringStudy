package data;


import lombok.AllArgsConstructor;
import lombok.Data;

public class OuterClass {
    private String name;

    public OuterClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
