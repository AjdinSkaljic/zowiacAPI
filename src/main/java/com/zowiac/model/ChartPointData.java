package com.zowiac.model;

public class ChartPointData {

    private String period;

    private int count;

    private String color;

    public ChartPointData() {
    }

    public ChartPointData(String period, int count) {
        this.period = period;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


}
