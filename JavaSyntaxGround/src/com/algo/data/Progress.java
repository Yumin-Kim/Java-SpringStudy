package com.algo.data;

public class Progress {
    private int process;
    private String percent;

    public Progress(int process, String percent) {
        this.process = process;
        this.percent = percent;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
