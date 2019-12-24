package com.example.comm.pojo.customer;


import lombok.Data;

@Data
public class Customer {

    private Integer c_id;
    private String customer_name;
    private Integer c_complete;
    private Integer c_state;

    @Override
    public String toString() {
        return "Customer{" +
                "c_id=" + c_id +
                ", customer_name='" + customer_name + '\'' +
                ", c_complete=" + c_complete +
                ", c_state=" + c_state +
                '}';
    }
}
