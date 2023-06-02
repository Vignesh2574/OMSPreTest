package com.test.omspretest.controller;

import com.test.omspretest.model.Availablity;
import com.test.omspretest.model.Calendar;
import com.test.omspretest.model.CalendarRequest;
import com.test.omspretest.model.Capacity;
import com.test.omspretest.service.AvailablityService;
import com.test.omspretest.service.CalendarService;
import com.test.omspretest.service.CapacityService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@RestController
public class CalendarController {


    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @RequestMapping(value = "/findStoreAvailability", method = RequestMethod.POST)
    public CalendarRequest getStoreAvailablity(@RequestBody CalendarRequest calendarRequest, ModelMap map) throws ExecutionException, InterruptedException {
        LocalDateTime reqDate = calendarRequest.getRequestDate();
        String dayOfTheWeek = String.valueOf(reqDate.getDayOfWeek());
        int reqHour = (int) reqDate.getLong(ChronoField.HOUR_OF_DAY);
        int reqMin = (int) reqDate.getLong(ChronoField.MINUTE_OF_HOUR);
        LocalTime reqTime = LocalTime.of(reqHour,reqMin);

        Calendar calendar = calendarService.getCalendarForLocation(calendarRequest.getLocationID());
        String day = calendar.getDay();
        LocalTime cutOffTime = calendar.getCutOffTime();
        if(reqTime.isBefore(cutOffTime) & ("ALL".equals(dayOfTheWeek) || dayOfTheWeek.equalsIgnoreCase(day))){
            calendarRequest.setStatus("Available");
        }else{
            calendarRequest.setStatus("Not Available");
        }

        return calendarRequest;
    }

}
