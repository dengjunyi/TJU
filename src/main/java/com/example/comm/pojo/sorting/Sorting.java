package com.example.comm.pojo.sorting;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * Sorting实体类
 */
@Data
public class Sorting {

    private long s_id;
    private String s_orderid;
    private String s_category;
    private String s_Item_info;
    private long s_count;
    private Double s_weight;
    private String s_barcode;
    private String customer_name;
    private long s_number;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String order_time;

    @Override
    public String toString() {
        return "Sorting{" +
                "s_id=" + s_id +
                ", s_orderid='" + s_orderid + '\'' +
                ", s_category='" + s_category + '\'' +
                ", s_Item_info='" + s_Item_info + '\'' +
                ", s_count=" + s_count +
                ", s_weight=" + s_weight +
                ", s_barcode='" + s_barcode + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", s_number=" + s_number +
                ", order_time='" + order_time + '\'' +
                '}';
    }
}
