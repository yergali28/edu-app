package kz.iitu.dao;

import java.io.Serializable;

public class Average implements Serializable {

    private double mark;
    private double attendance;

    public Average() {
    }

    public Average(double mark, double attendance) {
        this.mark = mark;
        this.attendance = attendance;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }
}
