package com.example.comm.pojo.sorting;

import java.sql.Date;

/**
 * Sorting实体类
 */
public class Sorting {

    private long order_id;
    private String order_number;
    private String order_Barcode;
    private String order_instructions;
    private String order_customername;
    private long order_quantlty;
    private Date order_time;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getOrder_Barcode() {
        return order_Barcode;
    }

    public void setOrder_Barcode(String order_Barcode) {
        this.order_Barcode = order_Barcode;
    }

    public String getOrder_instructions() {
        return order_instructions;
    }

    public void setOrder_instructions(String order_instructions) {
        this.order_instructions = order_instructions;
    }

    public String getOrder_customername() {
        return order_customername;
    }

    public void setOrder_customername(String order_customername) {
        this.order_customername = order_customername;
    }

    public long getOrder_quantlty() {
        return order_quantlty;
    }

    public void setOrder_quantlty(long order_quantlty) {
        this.order_quantlty = order_quantlty;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    @Override
    public String toString() {
        return "Sorting{" +
                "order_id=" + order_id +
                ", order_number='" + order_number + '\'' +
                ", order_Barcode='" + order_Barcode + '\'' +
                ", order_instructions='" + order_instructions + '\'' +
                ", order_customername='" + order_customername + '\'' +
                ", order_time=" + order_time +
                '}';
    }
}
