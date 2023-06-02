package com.test.omspretest.service;

import com.test.omspretest.model.Availablity;
import com.test.omspretest.model.Calendar;
import com.test.omspretest.model.Capacity;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
public class CalendarService {

    @Getter
    private static List<Calendar> lsCalendar = new ArrayList<>();

    static {
        lsCalendar.add(new Calendar ("STORE001", "ALL", LocalTime.of(13, 30)));
        lsCalendar.add(new Calendar ("STORE002", "SUNDAY", LocalTime.of(13, 30)));
        lsCalendar.add(new Calendar ("STORE003", "MONDAY", LocalTime.of(13, 30)));
    }

    public Calendar getCalendarForLocation(String locationID){
        Predicate<Calendar> getCalendar = calendar -> calendar.getLocationID().equalsIgnoreCase(locationID);
        return lsCalendar.stream().filter(getCalendar).findFirst().orElseThrow(RuntimeException::new);
    }

}
