package com.test.omspretest.service;

import com.test.omspretest.model.Availablity;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
@Service
public class AvailablityService {
    @Getter
    private static List<Availablity> lsAvailItems = new ArrayList<>();

    static {
        lsAvailItems.add(new Availablity ("Store001", "Prod1", new Date(2021, 02, 19), 1.0D));
        lsAvailItems.add(new Availablity ("Store001", "Prod1", new Date(2021, 02, 20), 3.0D));
        lsAvailItems.add(new Availablity ("Store001", "Prod1", new Date(2021, 02, 21), 0.0D));
    }

    public Availablity getAvailability(Availablity availablity){
        Predicate<Availablity> getAvailablity = avail -> avail.getStoreNo().equalsIgnoreCase(availablity.getStoreNo());
               // && avail.getProductId().equalsIgnoreCase(availablity.getProductId()) && avail.getDate().equals(availablity.getDate());
        return lsAvailItems.stream().filter(getAvailablity).findFirst().orElseThrow(RuntimeException::new);
    }
}
