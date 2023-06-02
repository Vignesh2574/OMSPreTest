package com.test.omspretest.service;

import com.test.omspretest.model.Availablity;
import com.test.omspretest.model.Capacity;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
@Service
public class CapacityService {

    @Getter
    private static List<Capacity> lsCapacityItems = new ArrayList<>();

    static {
        lsCapacityItems.add(new Capacity ("Store001", "Prod1", new Date(2021, 02, 19), 0.0D));
        lsCapacityItems.add(new Capacity ("Store001", "Prod1", new Date(2021, 02, 19), 2.0D));
        lsCapacityItems.add(new Capacity ("Store001", "Prod1", new Date(2021, 02, 19), 2.0D));
    }

    public Capacity getCapacity(Availablity availablity){
        Predicate<Capacity> getCapacity = capacity -> capacity.getStoreNo().equalsIgnoreCase(availablity.getStoreNo());
//                && capacity.getDate().equals(availablity.getDate());
        return lsCapacityItems.stream().filter(getCapacity).findFirst().orElseThrow(RuntimeException::new);
    }

}
