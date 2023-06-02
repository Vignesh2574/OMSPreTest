package com.test.omspretest.controller;

import com.test.omspretest.model.Availablity;
import com.test.omspretest.model.Capacity;
import com.test.omspretest.service.AvailablityService;
import com.test.omspretest.service.CapacityService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@RestController
public class AvailablityController {


    private AvailablityService availablityService;
    private CapacityService capacityService;

    public AvailablityController(AvailablityService availablityService, CapacityService capacityService) {
        this.availablityService = availablityService;
        this.capacityService = capacityService;
    }



    @RequestMapping(value = "/getProdAvailability", method = RequestMethod.POST)
    public Availablity fetchAvailablityAndCapacity(@RequestBody Availablity availablity, ModelMap map) throws ExecutionException, InterruptedException {
        List finalList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future availDetails = executorService.submit(getAvailabilityDetails(availablity, availablityService));
        Future capacityDetails = executorService.submit(getCapacityDetails(availablity, capacityService));
        executorService.shutdown();
        Availablity fetchedAvailablity = (Availablity) availDetails.get();
        Capacity fetchedCapacity =(Capacity) capacityDetails.get();
        Double availQty = fetchedAvailablity.getAvailQty();
        Double availCapacity = fetchedCapacity.getNoOfOrdersAccepted();
        if (availQty>0){
            if (availCapacity>0){
                availablity.setStatus("Available");
            }else{
                availablity.setStatus("No Capacity");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.valueOf(204));
        }

        return availablity;
    }

    private Callable getAvailabilityDetails(Availablity availablity, AvailablityService availablityService) {
        return new Callable() {

            @Override
            public Object call() throws Exception {
                return availablityService.getAvailability(availablity);
            }
        };
    }

    private Callable getCapacityDetails(Availablity availablity, CapacityService capacityService) {
        return new Callable() {

            @Override
            public Object call() throws Exception {
                return capacityService.getCapacity(availablity);
            }
        };
    }
}
