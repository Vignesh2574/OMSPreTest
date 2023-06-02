package com.test.omspretest.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter@Setter
public class Calendar {
    private String locationID;
    private String Day;
    private LocalTime cutOffTime;

    public Calendar(String locationID, String day, LocalTime cutOffTime) {
        this.locationID = locationID;
        Day = day;
        this.cutOffTime = cutOffTime;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "locationID='" + locationID + '\'' +
                ", Day='" + Day + '\'' +
                ", cutOffTime=" + cutOffTime +
                '}';
    }
}
