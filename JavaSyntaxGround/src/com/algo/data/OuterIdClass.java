package com.algo.data;

import lombok.Data;

@Data
public class OuterIdClass {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuterIdClass(Long id) {
        this.id = id;
    }
}
