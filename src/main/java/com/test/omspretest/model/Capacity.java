package com.test.omspretest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class Capacity {
    private String storeNo;
    private String productId;
    private Date date;
    private Double noOfOrdersAccepted;

    public Capacity(String storeNo, String productId, Date date, Double availQty) {
        this.storeNo = storeNo;
        this.productId = productId;
        this.date = date;
        this.noOfOrdersAccepted = availQty;
    }

    @Override
    public String toString() {
        return "Availablity{" +
                "storeNo='" + storeNo + '\'' +
                ", productId='" + productId + '\'' +
                ", reqDate=" + date +
                ", availQty=" + noOfOrdersAccepted +
                '}';
    }
}
