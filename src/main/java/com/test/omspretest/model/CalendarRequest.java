package com.test.omspretest.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter
public class CalendarRequest {
    private String locationID;
    private LocalDateTime requestDate;
    private String status;

    public CalendarRequest(String locationID, LocalDateTime requestDate, String status) {
        this.locationID = locationID;
        this.requestDate = requestDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CalendarRequest{" +
                "locationID='" + locationID + '\'' +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                '}';
    }
}
