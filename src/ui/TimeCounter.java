package ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.property.SimpleStringProperty;

public class TimeCounter {

    private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:S");
    private String[] split;
    private SimpleStringProperty ShowTime;
    private long time;
    private Timer t = new Timer("Metronome", true);
    private TimerTask tt;
    boolean timing = false;

    public TimeCounter() {
        ShowTime = new SimpleStringProperty("00:00:00");
    }
    /**
     * start timer
     */
    public void startCount(final long time) {
        this.time = time;
        timing = true;
        tt = new TimerTask() {

            @Override
            public void run() {
                if (!timing) {
                    try {
                        tt.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    newTime();
                }
            }
        };
        t.scheduleAtFixedRate(tt, 10, 10);
    }
    /**
     * stop Timer
     */
    public synchronized void stopTimer() {
        timing = false;
    }
    /**
     * update timer
     */
    public synchronized void newTime() {
        this.time = this.time + 10;
        split = sdf.format(new Date(this.time)).split(":");
        ShowTime.set(split[0] + ":" + split[1] + ":" + (split[2].length() == 1 ? "0" + split[2] : split[2].substring(0, 2)));
    }


    /**
     * get time
     */
    public synchronized long getTime() {
        return time;
    }

}