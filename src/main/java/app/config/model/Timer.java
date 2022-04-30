package app.config.model;

public class Timer {

    private Long nanoTime = System.nanoTime();


    public Long getTime() {
        return nanoTime;
    }
}
