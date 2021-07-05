package com.algo.data.abstractdata;

public interface CarInterface<RESULT , PARMA> {
    RESULT racing(PARMA parma);

    RESULT drift(PARMA parma);
}
