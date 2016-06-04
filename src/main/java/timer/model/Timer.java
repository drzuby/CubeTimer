package timer.model;

/**
 * Created by Jakub Janusz on 2016-02-04.
 */
public class Timer {

    private double start;
    private double stop;

    public Timer() {

    }

    public void start() {
        this.start = System.currentTimeMillis();
    }

    public void stop() {
        this.stop = System.currentTimeMillis();
    }

    public String getTime() {
        return Double.toString((stop - start) / 1000);
    }

}
