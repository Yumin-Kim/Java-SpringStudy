package data;

import java.util.Optional;

public class Teacher {

    private String name;


    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
