package app.giacomo.lavermicocca.termostato.Bean;

/**
 * Created by Giacomo on 27/04/2015.
 */
public class ScheduleItemBean {
    private String startTime;
    private String endTIme;
    private String temperature;

    public ScheduleItemBean(String startTime, String endTIme, String temperature) {
        this.startTime = startTime;
        this.endTIme = endTIme;
        this.temperature = temperature;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTIme() {
        return endTIme;
    }

    public void setEndTIme(String endTIme) {
        this.endTIme = endTIme;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
