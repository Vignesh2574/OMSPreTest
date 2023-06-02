package com.test.omspretest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class Availablity {
    private String storeNo;
    private String productId;
    private Date date;
    private Double availQty;
    private String status;

    public Availablity(String storeNo, String productId, Date date, Double availQty) {
        this.storeNo = storeNo;
        this.productId = productId;
        this.date = date;
        this.availQty = availQty;
    }

    @Override
    public String toString() {
        return "Availablity{" +
                "storeNo='" + storeNo + '\'' +
                ", productId='" + productId + '\'' +
                ", reqDate=" + date +
                ", availQty=" + availQty +
                '}';
    }
}
