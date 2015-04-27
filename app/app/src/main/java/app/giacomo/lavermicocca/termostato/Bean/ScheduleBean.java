package app.giacomo.lavermicocca.termostato.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giacomo on 27/04/2015.
 */
public class ScheduleBean {
    private List<ScheduleItemBean> monday = new ArrayList<>();
    private List<ScheduleItemBean> tuesday = new ArrayList<>();
    private List<ScheduleItemBean> wednesday = new ArrayList<>();
    private List<ScheduleItemBean> thursday = new ArrayList<>();
    private List<ScheduleItemBean> friday = new ArrayList<>();
    private List<ScheduleItemBean> saturday = new ArrayList<>();
    private List<ScheduleItemBean> sunday = new ArrayList<>();

    public List<ScheduleItemBean> getMonday() {
        return monday;
    }

    public void setMonday(List<ScheduleItemBean> monday) {
        this.monday = monday;
    }

    public List<ScheduleItemBean> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<ScheduleItemBean> tuesday) {
        this.tuesday = tuesday;
    }

    public List<ScheduleItemBean> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<ScheduleItemBean> wednesday) {
        this.wednesday = wednesday;
    }

    public List<ScheduleItemBean> getThursday() {
        return thursday;
    }

    public void setThursday(List<ScheduleItemBean> thursday) {
        this.thursday = thursday;
    }

    public List<ScheduleItemBean> getFriday() {
        return friday;
    }

    public void setFriday(List<ScheduleItemBean> friday) {
        this.friday = friday;
    }

    public List<ScheduleItemBean> getSaturday() {
        return saturday;
    }

    public void setSaturday(List<ScheduleItemBean> saturday) {
        this.saturday = saturday;
    }

    public List<ScheduleItemBean> getSunday() {
        return sunday;
    }

    public void setSunday(List<ScheduleItemBean> sunday) {
        this.sunday = sunday;
    }
}
